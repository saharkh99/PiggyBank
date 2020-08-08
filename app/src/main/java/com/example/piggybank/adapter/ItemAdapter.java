package com.example.piggybank.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.model.Transaction;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private List<Transaction> transactions;
    private String title;

    public ItemAdapter(Context context, List<Transaction> transactions, String title ) {
        this.context = context;
        this.transactions = transactions;
        this.title=title;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.last_item, parent, false);
        return new ItemViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
      // Expense expense= (Expense) transactions.get(position);
      // holder.img.setImageDrawable(expense.getType());
     //  holder.amount.setText(String.valueOf(expense.getAmount()));
       holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title;
        TextView amount;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.last_icon);
            title=itemView.findViewById(R.id.last_type);
            amount=itemView.findViewById(R.id.last_amount);
        }
    }
}
