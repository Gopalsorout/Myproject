package com.aptron.internalst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText2;
    Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);

        editText2 = findViewById(R.id.editText1);
        btn=findViewById(R.id.btn);
        btn1=findViewById(R.id.btn1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText.getText().toString();
                String pass = editText2.getText().toString();
                FileOutputStream stream = null;

                email = email+",";

                try {
                    stream =openFileOutput("abhi.txt",MODE_PRIVATE);
                    stream.write(email.getBytes());
                    stream.write(pass.getBytes());

                    Toast.makeText(MainActivity.this, "Data Save SuccessFully", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Exception !!!", Toast.LENGTH_SHORT).show();
                }
                finally {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,Main2Activity.class));

            }
        });
    }
}
