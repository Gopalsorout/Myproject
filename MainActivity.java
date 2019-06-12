package com.example.vaibhav.multithreading;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
 TextView text1,text2;
 Button btn1;
 ProgressBar progressBar;
 ListView list1;
 LinearLayout mylayout;

    private static final String TAG = "abhi";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        btn1=findViewById(R.id.btn1);
        list1=findViewById(R.id.list1);
        mylayout = findViewById(R.id.linear);
       final String[] img_url = getResources().getStringArray(R.array.image_url);

        list1.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.image)));

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {

                text1.setText(img_url[position]);
            }
        });

        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
          Thread thread =new Thread(new MyThread());
          thread.start();

            }
        });

    }

    public void downloadImage(String url)
    {
        HttpURLConnection connection= null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            URL myurl = new URL(url);
          connection = (HttpURLConnection) myurl.openConnection();
      inputStream = connection.getInputStream();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
           Uri uri= Uri.parse(url);
            File myFile = new File(folder,uri.getLastPathSegment());
           outputStream = new FileOutputStream(myFile);
            int read ;
           byte[] arr = new byte[1024];
            while ((read=inputStream.read(arr))!=-1)
            {
                outputStream.write(arr,0,read);
            }

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mylayout.setVisibility(View.GONE);
                }
            });
            //Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
          connection.disconnect();
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    class MyThread implements Runnable
    {
        @Override
        public void run() {

         MainActivity.this.runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 mylayout.setVisibility(View.VISIBLE);
             }
         });
            downloadImage(text1.getText().toString());
        }
    }

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted1");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted1");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted2");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted2");
            return true;
        }
    }
}
