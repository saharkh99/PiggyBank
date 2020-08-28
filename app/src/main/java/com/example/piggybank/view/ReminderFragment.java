package com.example.piggybank.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.adapter.ItemAdapter;
import com.example.piggybank.adapter.TaskAdapter;
import com.example.piggybank.databinding.ReminderFragmentBinding;
import com.example.piggybank.databinding.ReportFragmentBinding;
import com.example.piggybank.model.Task;
import com.example.piggybank.model.Types;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.ReminderViewModel;
import com.example.piggybank.viewmodel.ReportFragmentViewModel;

import java.util.List;

public class ReminderFragment extends DialogFragment {
    static ReminderViewModel viewModel;
    ReminderFragmentBinding binding;
    private EditText title, amount;
    private TaskAdapter itemAdapter;
    private RecyclerView recyclerView;
    private EditText dates;
    private String[] months = {"اسفند", "بهمن", "دی", "اذر", "ابان", "مهر", "شهریور", "مرداد", "تیر", "خرداد", "اردیبهشت", "فروردین"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater
                , R.layout.reminder_fragment, container, false);
        viewModel = ViewModelProviders.of(this,
                new FragmentFactory(getActivity().getApplication()))
                .get(ReminderViewModel.class);
        binding.setClickHandler(new ReminderFragment.AddAndEditActivityClickHandlers(this.getView()));
        setRecyclerView();
        dates = binding.date;
        return binding.getRoot();
    }

    /**
     *
     */
    private void setRecyclerView() {
        recyclerView = binding.recycle;
        if (viewModel.getTask() != null)
            viewModel.getTask().observe(getActivity(), tasks -> {
                itemAdapter = new TaskAdapter(getActivity(), tasks);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                recyclerView.setAdapter(itemAdapter);
            });
    }

    public class AddAndEditActivityClickHandlers {
        View context;


        public AddAndEditActivityClickHandlers(View context) {
            this.context = context;
            binding.setClickHandler(this);
        }

        public int safeParseInt(String number) throws Exception {
            if (number != null) {
                return Integer.parseInt(number.trim());
            } else {
                throw new NullPointerException("Date string is invalid");
            }
        }

        public void onAddButtonClicked(View view) {

            amount = binding.amountItem;
            title = binding.titleItem;
            String date = dates.getText().toString();
            String[] dateParts = date.split("/");

            try {
                int year = safeParseInt(dateParts[2]);
                int month = safeParseInt(dateParts[1]);
                int day = safeParseInt(dateParts[0]);
                String result = year + " " + months[month] + " " + day;
                Log.d("result", result);
                viewModel.saveTask(Double.parseDouble(amount.getText().toString()), title.getText().toString(), result)
                        .observe(getActivity(), aBoolean -> {
                            if(aBoolean){
                                Task task=new Task();
                                task.setDatesTask(result);
                                task.setAmountTask((int)Double.parseDouble(amount.getText().toString()));
                                task.setTitleTask(title.getText().toString());
                                itemAdapter.addItem(task);
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}

