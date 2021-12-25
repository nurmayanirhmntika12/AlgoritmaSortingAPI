package com.example.algoritmasortingapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    final String linkAPI = "https://ewinsutriandi.github.io/mockapi/algoritma_pengurutan.json";

    ArrayList<DataSorting> dataSortings = new ArrayList();
    JSONObject dataAlgoAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataAlgo();
    }

    void setupListview () {
        ListView listview = findViewById(R.id.listVIew);
        Adapter adapter = new Adapter(this, dataSortings);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataSorting fSELECTED = dataSortings.get(position);
            Intent intent = new Intent(MainActivity.this, DetailContent.class);
            intent.putExtra("DATASELECTED", fSELECTED);
            startActivity(intent);
        }
    };

    void getDataAlgo() {
        dataSortings.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, linkAPI, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dataAlgoAPI = response;
                        refreshView();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", error.toString());
                        Toast.makeText(MainActivity.this, "Erorr: Gagal Mengambil Data", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        setupListview();
    }

    void refreshView() {
        Iterator<String> key = dataAlgoAPI.keys();
        int i = 0;
        while(key.hasNext()) {
            String nameFramework = key.next();
            try {
                JSONObject data = dataAlgoAPI.getJSONObject(nameFramework);
                String des = data.getString("deskripsi");
                String official_web = data.getString("baca_lebih_lanjut");
                String img = data.getString("gambar");

                if (i == 0) {
                    dataSortings.add(new DataSorting(nameFramework, official_web, des, "https://upload.wikimedia.org/wikipedia/commons/c/c8/Bubble-sort-example-300px.gif"));
                }else {
                    dataSortings.add(new DataSorting(nameFramework, official_web, des, img));
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

            i++;
        }
        setupListview();
    }
}