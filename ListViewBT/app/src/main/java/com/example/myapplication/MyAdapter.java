package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Model> itemList;

    public MyAdapter(Context context, List<Model> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model item = itemList.get(position);
        holder.itemImage.setImageResource(item.getImageResource());
        holder.itemArrow.setImageResource(item.getImageArrow());
        holder.itemMeal.setImageResource(item.getImageMeal());
        holder.itemSale.setImageResource(item.getImageSale());
        holder.itemText.setText(item.getText());
        holder.itemText1.setText(item.getText1());
        holder.itemText2.setText(item.getText2());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        ImageView itemArrow;
        ImageView itemMeal;
        ImageView itemSale;
        TextView itemText;
        TextView itemText1;
        TextView itemText2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.IV);
            itemText = itemView.findViewById(R.id.TV);
            itemMeal = itemView.findViewById(R.id.IVicon1);
            itemSale = itemView.findViewById(R.id.IVicon2);
            itemArrow = itemView.findViewById(R.id.IconRightArrow);
            itemText1 = itemView.findViewById(R.id.product);
            itemText2 = itemView.findViewById(R.id.sales);
        }
    }
}
