package com.example.piggybank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.piggybank.Network.SaveItems;
import com.example.piggybank.adapter.ItemAdapter;
import com.example.piggybank.model.Transaction;
import com.example.piggybank.model.Types;
import com.example.piggybank.ui.ColorPickerFragment;
import com.example.piggybank.ui.IconPickerFragment;
import com.example.piggybank.ui.Progress;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class NewItemFragment extends BottomSheetDialogFragment {
    private RadioButton incomeRadio, expenseRadio;
    private boolean isCost = true;
    private Button colorPicker;
    private ImageView iconPicker;
    private View view;
    private ImageButton save;
    private EditText amount;
    private double amountDouble;
    private Progress progress;
    private boolean resultCost, resultIncome;
    private int resultColor,resultIcon;
    IconPickerFragment iconPickerFragment;
    ColorPickerFragment colorPickerFragment;
    // List <String> months;{"far","ord","kho","tir","mor","shah","meh","aba","aza","dey","bah","esf"};
    List<String>types;
    public NewItemFragment(boolean isCost) {
        this.isCost = isCost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        types= Arrays.asList(new String[]{"hospital", "beauty", "bill", "exchange", "check", "clothes", "foods", "gas", "present", "income", "internet","transport"});
        view = inflater.inflate(R.layout.new_item_fragment, container, false);
        findView();
        setRadio();
        chooseColor();
        chooseIcon();
        saveItem();
        return view;
    }

    private void saveItem() {
        save.setOnClickListener(view -> {
            if (checkCorrectly()) {
                if (expenseRadio.isChecked()) {
                    SaveItems.saveCost(amountDouble, resultColor, types.get(resultIcon), "u1", "3,esf,1399", progress, result -> {
                        resultCost = result;
                        if (result) {
                            Transaction transaction=new Transaction();
                            transaction.setAmount(amountDouble);
                            transaction.setColor(resultColor);
                            transaction.setItemType("هزینه");
                            transaction.setType(types.get(resultIcon));
                            HomeFragment.itemAdapter.addItem( transaction);
                            HomeFragment.itemAdapter.notifyItemChanged(HomeFragment.itemAdapter.getItemCount());
                        }
                    });
                } else
                    SaveItems.saveIncome(amountDouble, resultColor, types.get(resultIcon), "u1", "3,esf,1399", progress, result -> {
                        resultIncome = result;
                        if (resultIncome) {
                            Transaction transaction=new Transaction();
                            transaction.setAmount(amountDouble);
                            transaction.setColor(resultColor);
                            transaction.setItemType("درامد");
                            transaction.setType(types.get(resultIcon));
                            HomeFragment.itemAdapter1.addItem( transaction);
                            HomeFragment.itemAdapter1.notifyItemChanged(HomeFragment.itemAdapter1.getItemCount());


                        }
                    });
            }
        });

    }

    private boolean checkCorrectly() {
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
        colorPicker.setOnClickListener(view -> {
            colorPickerFragment = new ColorPickerFragment();
            colorPickerFragment.setTargetFragment(NewItemFragment.this, 1);
            colorPickerFragment.show(getFragmentManager(), "color_show");

        });
    }

    private void chooseIcon() {
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
        if (isCost) {
            expenseRadio.setChecked(true);
        } else
            incomeRadio.setChecked(true);
        expenseRadio.setEnabled(false);
        incomeRadio.setEnabled(false);
    }

    private void findView() {
        iconPicker = view.findViewById(R.id.icon_picker);
        incomeRadio = view.findViewById(R.id.radio_income);
        expenseRadio = view.findViewById(R.id.radio_expense);
        colorPicker = view.findViewById(R.id.color_picker);
        save = view.findViewById(R.id.save_item);
        amount = view.findViewById(R.id.amount_item);
        progress = view.findViewById(R.id.progress);
    }

}