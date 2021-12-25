package com.example.algoritmasortingapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailContent extends AppCompatActivity {
    DataSorting dataSortings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_content);

        Intent intent = getIntent();
        dataSortings = (DataSorting) intent.getSerializableExtra("DATASELECTED");

        inisialisasi();
    }

    void inisialisasi() {
        ImageView img = findViewById(R.id.imgDetail);
        TextView title = findViewById(R.id.titleDetail),
                link = findViewById(R.id.linkDetail),
                desc = findViewById(R.id.descDetail);

        title.setText(dataSortings.getName());
        link.setText(dataSortings.getOfficial_web());
        desc.setText(dataSortings.getDescription());
        Glide
                .with(this)
                .asGif()
                .load(dataSortings.getImage())
                .into(img);
    }

    public void toLink(View v) {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(dataSortings.getOfficial_web()));
        startActivity(intent);
    }
}
