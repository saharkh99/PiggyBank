package com.example.piggybank.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.piggybank.R;
import com.example.piggybank.Util.Progress;
import com.example.piggybank.databinding.NewItemFragmentBinding;
import com.example.piggybank.model.Transaction;
import com.example.piggybank.Util.Types;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.NewItemFragmentViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


import java.util.Arrays;
import java.util.List;

public class NewItemFragment extends BottomSheetDialogFragment {
    NewItemFragmentBinding binding;
    NewItemFragmentViewModel viewModel;
    private RadioButton incomeRadio, expenseRadio;
    private boolean isCost = true;
    private Button colorPicker;
    private ImageView iconPicker;
    private EditText amount;
    private double amountDouble;
    private Progress progress;
    private boolean resultCost, resultIncome;
    private int resultColor, resultIcon;
    IconPickerFragment iconPickerFragment;
    ColorPickerFragment colorPickerFragment;
    List<String> types;

    public NewItemFragment(boolean isCost) {
        this.isCost = isCost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        types = Arrays.asList(new String[]{"hospital", "beauty", "bill", "exchange", "check", "clothes", "foods", "gas", "present", "income", "internet", "transport"});
        binding = DataBindingUtil.inflate(inflater
                , R.layout.new_item_fragment, container, false);
        viewModel = ViewModelProviders.of(this,
                new FragmentFactory(getActivity().getApplication()))
                .get(NewItemFragmentViewModel.class);
        setRadio();
        chooseColor();
        chooseIcon();
        binding.setClickHandler(new AddAndEditActivityClickHandlers(this.getView()));
        return binding.getRoot();
    }

    private boolean checkCorrectly() {
        amount = binding.amountItem;
        Log.d("amount", amount.toString());
        if (amount != null) {
            try {
                amountDouble = Double.parseDouble(amount.getText().toString().trim());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else
            return false;
    }

    private void chooseColor() {
        colorPicker = binding.colorPicker;
        colorPicker.setOnClickListener(view -> {
            colorPickerFragment = new ColorPickerFragment();
            colorPickerFragment.setTargetFragment(NewItemFragment.this, 1);
            colorPickerFragment.show(getFragmentManager(), "color_show");

        });
    }

    private void chooseIcon() {
        iconPicker = binding.iconPicker;
        iconPicker.setOnClickListener(view -> {
            iconPickerFragment = new IconPickerFragment();
            iconPickerFragment.setTargetFragment(NewItemFragment.this, 2);
            iconPickerFragment.show(getFragmentManager(), "icon_show");

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                resultColor = bundle.getInt("selectedColor", 0);
                colorPicker.setBackgroundColor(resultColor);
                colorPickerFragment.dismiss();
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                resultIcon = bundle.getInt("selectedDate", 0);
                iconPicker.setImageResource(Types.getRes(types.get(resultIcon)));
                iconPickerFragment.dismiss();
            }
        }
    }

    private void setRadio() {
        expenseRadio = binding.radioExpense;
        incomeRadio = binding.radioIncome;
        if (isCost) {
            expenseRadio.setChecked(true);
        } else
            incomeRadio.setChecked(true);
        expenseRadio.setEnabled(false);
        incomeRadio.setEnabled(false);
    }

    public void setChanges() {
        if (viewModel.getReportMonthly(Types.getDate(false)) != null) {
            viewModel.getReportMonthly(Types.getDate(false)).observe(getActivity(), monthlyReport -> {
                HomeFragment.month.setText("ماه :" + Types.getDate(false));
                HomeFragment.balance.setText("مانده :" + (int) monthlyReport.getBalance());
                HomeFragment.expense.setText("هزینه کل :" + (int) monthlyReport.getTotalExpense());
                HomeFragment.income.setText("درامد :" + (int) monthlyReport.getTotalIncome());
            });
        }
    }

    public class AddAndEditActivityClickHandlers {
        View context;

        public AddAndEditActivityClickHandlers(View context) {
            Log.d("0", "0");
            this.context = context;
            binding.setClickHandler(this);
        }


        public void onAddButtonClicked(View view) {
            if (checkCorrectly()) {
                if (expenseRadio.isChecked()) {
                    MutableLiveData<Boolean> result = (viewModel.saveCost(amountDouble, resultColor, Types.getRes(types.get(resultIcon)), "u1", Types.getDate(true)));
                    if (result != null) {
                        result.observe(getActivity(), aBoolean -> {
                            if (aBoolean) {
                                Transaction transaction = new Transaction();
                                Log.d("3", "3");
                                transaction.setAmount(amountDouble);
                                transaction.setColor(resultColor);
                                transaction.setItemType("هزینه");
                                transaction.setType(Types.getRes(types.get(resultIcon)));
                                HomeFragment.itemAdapter.addItem(transaction);
                                HomeFragment.itemAdapter.notifyItemChanged(HomeFragment.itemAdapter.getItemCount());
                                setChanges();
                            }
                        });
                    }
                } else {
                    MutableLiveData<Boolean> result = (viewModel.saveIncome(amountDouble, resultColor, Types.getRes(types.get(resultIcon)), "u1", Types.getDate(true)));
                    if (result != null) {
                        result.observe(getActivity(), aBoolean -> {
                            if (aBoolean) {
                                Transaction transaction = new Transaction();
                                Log.d("3", "3");
                                transaction.setAmount(amountDouble);
                                transaction.setColor(resultColor);
                                transaction.setItemType("درامد");
                                transaction.setType(Types.getRes(types.get(resultIcon)));
                                HomeFragment.itemAdapter.addItem(transaction);
                                HomeFragment.itemAdapter.notifyItemChanged(HomeFragment.itemAdapter.getItemCount());
                                setChanges();
                            }
                        });
                    }
                }


            }
        }
    }
}

