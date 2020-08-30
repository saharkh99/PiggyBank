package com.example.piggybank.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.Util.Types;
import com.example.piggybank.adapter.TaskAdapter;
import com.example.piggybank.databinding.ReminderFragmentBinding;
import com.example.piggybank.model.Task;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.ReminderViewModel;
import com.google.android.material.snackbar.Snackbar;

public class ReminderFragment extends DialogFragment {
    private static ReminderViewModel viewModel;
    private ReminderFragmentBinding binding;
    private EditText title, amount;
    static TaskAdapter itemAdapter;
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


    private void setRecyclerView() {
        recyclerView = binding.recycle;
        if (viewModel.getLastTask() != null)
            viewModel.getLastTask().observe(getActivity(), tasks -> {
                itemAdapter = new TaskAdapter(getActivity(), tasks);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                recyclerView.setAdapter(itemAdapter);
            });
    }

    public class AddAndEditActivityClickHandlers {
        private View context;
        private Snackbar snackbar;


        /**
         * @param context
         */
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
                String result = year + " " + Types.getMonth(month - 1) + " " + day;
                viewModel.saveTask(Double.parseDouble(amount.getText().toString()), title.getText().toString(), result)
                        .observe(getActivity(), aBoolean -> {
                            if (aBoolean) {
                                Task task = new Task();
                                task.setDatesTask(result);
                                task.setAmountTask((int) Double.parseDouble(amount.getText().toString()));
                                task.setTitleTask(title.getText().toString());
                                itemAdapter.addItem(task);
                                snackbar = Snackbar.make(view, "با موفقیت ذخیره شد", Snackbar.LENGTH_INDEFINITE);
                                snackbar.show();
                            } else {
                                snackbar = Snackbar.make(view, "دوباره امتحان کنید", Snackbar.LENGTH_INDEFINITE);
                                snackbar.show();
                            }
                        });

            } catch (Exception e) {
                snackbar = Snackbar.make(view, "مقادیر را به درستی وارد کنید", Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
                e.printStackTrace();
            }


        }
    }
}

