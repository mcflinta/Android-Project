package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Model> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RCV1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new Model(R.drawable.image3, R.drawable.iconarrowrignt, R.drawable.icondinner, R.drawable.iconsale, "Món mặn", "5 sản phẩm", "5 đang giảm giá"));
        itemList.add(new Model(R.drawable.image4, R.drawable.iconarrowrignt, R.drawable.icondinner, R.drawable.iconsale, "Món canh", "10 sản phẩm", "10 đang giảm giá"));
        itemList.add(new Model(R.drawable.image5, R.drawable.iconarrowrignt, R.drawable.icondinner, R.drawable.iconsale, "Món xào", "5 sản phẩm", "5 đang giảm giá"));


        adapter = new MyAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new clickItemListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("positionItem", position);
                startActivity(intent);
            }
        });
    }
}