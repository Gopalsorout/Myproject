package com.aptron.recyclerviewinandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView  recyclerView;
    int[] img = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.alok);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
     String[] title = getResources().getStringArray(R.array.title);
     MyAdapter myAdapter = new MyAdapter(this,title,img);
     recyclerView.setAdapter(myAdapter);

    }
}
