package com.example.piggybank.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.databinding.LastItemBinding;
import com.example.piggybank.model.Transaction;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context context;
    private  List<Transaction> transactions;
    LastItemBinding binding;
    private ItemAdapter.onItemClickListener mlistener;
    boolean v;


    public interface onItemClickListener{
        void onItemClick(int position,boolean checked);
    }
    public  void setOnItemClickListener(ItemAdapter.onItemClickListener listener){
        mlistener=listener;
    }


    public ItemAdapter(Context context, List<Transaction> transactions,boolean isVisible) {
        this.context = context;
        this.transactions = transactions;
        v=isVisible;
    }
    public void addItem( Transaction transaction) {
        transactions.add(0, transaction);
        if(transactions.size()>4)
        transactions.remove(transactions.size()-1);
        notifyDataSetChanged();
        Log.d("xx", transactions.size()+"");
        this.notifyItemChanged(transactions.size());

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.last_item, parent, false);
        binding= DataBindingUtil.bind(view);
        return new ItemViewHolder(binding,mlistener);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Transaction transaction=transactions.get(position);
        Log.d("amount", transaction.getItemType()+"");
        holder.cardView.setBackgroundColor(transaction.getColor());
        holder.img.setImageResource(transaction.getType());
        holder.amount.setText(String.valueOf((int) transaction.getAmount()));
        holder.title.setText(transaction.getItemType());
        if (v)
        holder.checkBox.setVisibility(View.VISIBLE);
    }


    @Override
    public int getItemCount() {
        if (transactions != null) {
            return transactions.size();
        } else {
            return 0;
        }
    }



    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title;
        TextView amount;
        CardView cardView;
        CheckBox checkBox;

        public ItemViewHolder(@NonNull LastItemBinding itemView,final ItemAdapter.onItemClickListener listener) {
            super(itemView.getRoot());
            cardView=binding.cardItem;
            amount=binding.lastAmount;
            title=binding.lastType;
            img=binding.lastIcon;
            checkBox=binding.checkbox;
            itemView.getRoot().setOnClickListener(view -> {
                if(listener!=null){
                    int position1 = getAdapterPosition();
                    view.setTag("item");
                    listener.onItemClick(position1,checkBox.isChecked());
                }
            });

        }

    }

}