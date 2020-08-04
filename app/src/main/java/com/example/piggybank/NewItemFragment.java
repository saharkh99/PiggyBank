package com.example.piggybank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NewItemFragment extends BottomSheetDialogFragment {
    private RadioButton incomeRadio,expenseRadio;
    private boolean isCost=true;
    private View view;

    public NewItemFragment(boolean isCost) {
        this.isCost=isCost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.new_item_fragment, container, false);
        findView();
        setRadio();
        return view;
    }

    private void setRadio() {
        if(isCost){
            expenseRadio.setChecked(true);
        }
        else
            incomeRadio.setChecked(true);
        expenseRadio.setEnabled(false);
        incomeRadio.setEnabled(false);
    }

    private void findView() {
        incomeRadio=view.findViewById(R.id.radio_income);
        expenseRadio=view.findViewById(R.id.radio_expense);
    }
}
