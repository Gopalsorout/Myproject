package com.aptron.internalst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    EditText editText3,editText4;
    Button btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        btn3.setOnClickListener(new View.OnClickListener() {

            FileInputStream inputStream = null;

            @Override
            public void onClick(View v) {
                try {
                    inputStream =openFileInput("abhi.txt");
                    int read=0;
                    StringBuffer stringBuffer = new StringBuffer();
                    while (( read=inputStream.read())!=-1)
                    {
                        stringBuffer.append((char)read);

                    }

                    String name=stringBuffer.substring(0,stringBuffer.indexOf(","));

                    String pass=stringBuffer.substring(stringBuffer.indexOf(",")+1);

                    editText3.setText(name);
                    editText4.setText(pass);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                finally {

                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
