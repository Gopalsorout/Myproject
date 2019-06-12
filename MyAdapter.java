package com.aptron.recyclerviewinandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    String[] title;
    int img[];

    public MyAdapter(Context context, String[] title, int[] img) {
        this.context = context;
        this.title = title;
        this.img = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View myView= inflater.inflate(R.layout.myxml,parent,false);

        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.imageView.setImageResource(img[position]);
       holder.textView.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
