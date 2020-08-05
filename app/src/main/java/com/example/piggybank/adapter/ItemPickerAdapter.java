package com.example.piggybank.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;

import java.util.List;

public class ItemPickerAdapter extends RecyclerView.Adapter<ItemPickerAdapter.ItemViewHolder> {

    int colors[];
    Context context;
    private onItemClickListener mlistener;
    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(onItemClickListener listener){
        mlistener=listener;
    }
    public ItemPickerAdapter(int[] colors, Context context) {
        this.colors = colors;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picker, parent, false);
        return new ItemViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d("color", colors[position]+"");
        holder.img.setImageResource(colors[position]);
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public ItemViewHolder(@NonNull View itemView,final onItemClickListener listener) {
            super(itemView);
            img=itemView.findViewById(R.id.img_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listener!=null){
                        int position=getAdapterPosition();
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
