package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter2 adapter;
    private List<Model2> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = findViewById(R.id.RCV1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int getPosition = getIntent().getIntExtra("positionItem", 0);
        if (getPosition == 0)
        {
            itemList = new ArrayList<>();
            itemList.add(new Model2(R.drawable.suonnuong, R.drawable.iconarrowrignt, R.drawable.onestar, "Sườn nướng", "12000₫", "15000₫"));
            itemList.add(new Model2(R.drawable.gakho, R.drawable.iconarrowrignt, R.drawable.fivestar, "Gà kho", "15000₫", "15000₫"));
            itemList.add(new Model2(R.drawable.thitkhotrung, R.drawable.iconarrowrignt, R.drawable.threestar, "Thịt kho trứng", "12000₫", "12000₫"));
            itemList.add(new Model2(R.drawable.nemnuong, R.drawable.iconarrowrignt, R.drawable.fivestar, "Nem nướng", "13000₫", "16000₫"));
            adapter = new MyAdapter2(this, itemList);
            recyclerView.setAdapter(adapter);
        }
        else if(getPosition == 1)
        {
            itemList = new ArrayList<>();
            itemList.add(new Model2(R.drawable.canhchua, R.drawable.iconarrowrignt, R.drawable.onestar, "Canh chua", "8000₫", "15000₫"));
            itemList.add(new Model2(R.drawable.canhraumuong, R.drawable.iconarrowrignt, R.drawable.fivestar, "Canh rau muống", "10000₫", "12000₫"));
            itemList.add(new Model2(R.drawable.canhtrung, R.drawable.iconarrowrignt, R.drawable.onestar, "Canh trứng", "9000₫", "13000₫"));
            itemList.add(new Model2(R.drawable.canhraucai, R.drawable.iconarrowrignt, R.drawable.fivestar, "Canh rau cải", "10000₫", "14000₫"));
            adapter = new MyAdapter2(this, itemList);
            recyclerView.setAdapter(adapter);
        }
    }
}