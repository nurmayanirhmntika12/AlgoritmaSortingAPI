package com.example.algoritmasortingapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {
    ArrayList<DataSorting> dataSortings = new ArrayList();
    LayoutInflater layoutInflater;
    Context context;

    public Adapter(Context context, ArrayList<DataSorting> data) {
        super(context, R.layout.row_activity, data);
        this.dataSortings = data;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.row_activity, parent, false);
        DataSorting data = this.dataSortings.get(position);

        TextView titleListview = convertView.findViewById(R.id.titleList);
        ImageView imageView = convertView.findViewById(R.id.imgList);

        Glide
                .with(context)
                .asGif()
                .load(data.getImage())
                .into(imageView);

        titleListview.setText(data.getName());
        return convertView;
    }
}
