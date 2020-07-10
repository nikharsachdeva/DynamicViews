package com.nikharsachdeva.dynamicviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Cricketers extends AppCompatActivity {
    RecyclerView  recyclerCricketers;
    ArrayList<CricketerModel> cricketerModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricketers);

        recyclerCricketers = findViewById(R.id.recycler_cricketers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerCricketers.setLayoutManager(layoutManager);

        cricketerModelArrayList = (ArrayList<CricketerModel>) getIntent().getExtras().getSerializable("list");

        recyclerCricketers.setAdapter(new CricketerAdapter(cricketerModelArrayList));
    }
}