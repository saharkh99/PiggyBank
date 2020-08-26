package com.example.piggybank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.databinding.ItemPickerBinding;

public class ColorPickerAdapter extends RecyclerView.Adapter<ColorPickerAdapter.ItemViewHolder> {

    int colors[];
    Context context;
    private onItemClickListener mlistener;
    ItemPickerBinding binding;
    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(onItemClickListener listener){
        mlistener=listener;
    }
    public ColorPickerAdapter(int[] colors, Context context) {
        this.colors = colors;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picker, parent, false);
        binding= DataBindingUtil.bind(view);
        return new ItemViewHolder(binding,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.img.setImageResource(colors[position]);
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public ItemViewHolder(@NonNull ItemPickerBinding itemView,final onItemClickListener listener) {
            super(itemView.getRoot());
            img=binding.imgItem;
            itemView.getRoot().setOnClickListener(view -> {
                        int position1 = getAdapterPosition();
                        listener.onItemClick(position1);

                });
        }
    }
}
