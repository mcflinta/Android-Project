package com.example.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {

    private Context context;
    private List<Model2> itemList;

    public MyAdapter2(Context context, List<Model2> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_2, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        Model2 item = itemList.get(position);
        holder.itemImage.setImageResource(item.getImageResource());
        holder.itemArrow.setImageResource(item.getImageArrow());
        holder.itemText.setText(item.getText());
        holder.itemText1.setText(item.getText1());
        holder.itemText2.setText(item.getText2());
        holder.itemStar.setImageResource(item.getImageStar());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView itemImage;
        ImageView itemArrow;
        ImageView itemStar;
        TextView itemText;
        TextView itemText1;
        TextView itemText2;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.IV);
            itemText = itemView.findViewById(R.id.TV);
            itemText1 = itemView.findViewById(R.id.TVPrice);
            itemText2 = itemView.findViewById(R.id.TVPrice2);
            itemArrow = itemView.findViewById(R.id.IconRightArrow);
            itemStar = itemView.findViewById(R.id.IVStar);
            itemText2.setPaintFlags(itemText2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

}

