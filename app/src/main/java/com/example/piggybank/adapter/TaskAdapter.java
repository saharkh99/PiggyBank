package com.example.piggybank.adapter;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.databinding.LastReminderBinding;
import com.example.piggybank.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ItemViewHolder> {
    private Context context;
    private  List<Task> tasks;
    LastReminderBinding binding;


    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }
    public void addItem( Task task) {
        tasks.add(0, task);
        if(tasks.size()>4)
            tasks.remove(tasks.size()-1);
        notifyDataSetChanged();
        this.notifyItemChanged(tasks.size());

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.last_reminder, parent, false);
        binding= DataBindingUtil.bind(view);
        return new ItemViewHolder(binding);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Task task=tasks.get(position);
        holder.amount.setText(String.valueOf((int) task.getAmountTask()));
        holder.title.setText(task.getTitleTask());
        holder.date.setText(task.getDatesTask());

    }


    @Override
    public int getItemCount() {
        if (tasks != null) {
            return tasks.size();
        } else {
            return 0;
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView amount;
        TextView date;

        public ItemViewHolder(@NonNull LastReminderBinding itemView) {
            super(itemView.getRoot());
            amount = binding.taskAmount;
            title = binding.taskTitle;
            date = binding.taskDate;
        }
    }
}
