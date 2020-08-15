package com.example.piggybank.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.model.Transaction;
import com.example.piggybank.model.Types;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private  List<Transaction> transactions=new ArrayList<>();

    public ItemAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;

    }
    public void addItem( Transaction transaction) {
        transactions.add(0, transaction);
        transactions.remove(transactions.size()-1);
        notifyDataSetChanged();
        Log.d("xx", transactions.size()+"");
        this.notifyItemChanged(transactions.size());

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=DataBindingUtil.inflate(LayoutInflater.from(context),
//                R.layout.last_item, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.last_item, parent, false);
        return new ItemViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Transaction transaction=transactions.get(position);
        holder.cardView.setBackgroundColor(transaction.getColor());
        holder.img.setImageResource(Types.getRes(transaction.getType()));
        holder.amount.setText(String.valueOf(transaction.getAmount()));
        holder.title.setText(transaction.getItemType());
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

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.last_icon);
            title=itemView.findViewById(R.id.last_type);
            amount=itemView.findViewById(R.id.last_amount);
            cardView=itemView.findViewById(R.id.card_item);
        }
    }
}