package com.example.piggybank;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.example.piggybank.Network.SaveItems;
import com.example.piggybank.ui.ColorPickerFragment;
import com.example.piggybank.ui.IconPickerFragment;
import com.example.piggybank.ui.Progress;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class NewItemFragment extends BottomSheetDialogFragment {
    private RadioButton incomeRadio,expenseRadio;
    private boolean isCost=true;
    private Button colorPicker;
    private ImageView iconPicker;
    private View view;
    private ImageButton save;
    private EditText amount;
    private double amountDouble;
    private Progress progress;
    private int resultDate;

    public NewItemFragment(boolean isCost) {
        this.isCost=isCost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.new_item_fragment, container, false);
        findView();
        setRadio();
        chooseColor();
        chooseIcon();
        saveItem();
        return view;
    }
    private void saveItem() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCorrectly()) {
                    if (expenseRadio.isChecked()) {
                        SaveItems.saveCost(amountDouble, resultDate, "shopping", "u1", progress);
                        //seekbar
                    } else
                        SaveItems.saveIncome(amountDouble,resultDate,"shopping","u1", progress);
                       //seakbar
                }
            }
        });
    }

    private boolean checkCorrectly() {
        if(amount!=null ){
            try{
               amountDouble= Double.parseDouble(amount.getText().toString().trim());
               return true;
            }catch(Exception e){
                return false;
            }
        }
        else
            return false;
    }

    private void chooseColor() {
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerFragment colorPickerFragment=new ColorPickerFragment();
                colorPickerFragment.setTargetFragment(NewItemFragment.this, 1);
                colorPickerFragment.show(getFragmentManager(), "color_show");

            }
        });
    }

    private void chooseIcon() {
        iconPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IconPickerFragment iconPickerFragment=new IconPickerFragment();
                iconPickerFragment.setTargetFragment(NewItemFragment.this, 2);
                iconPickerFragment.show(getFragmentManager(), "icon_show");

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(requestCode==1) {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    resultDate = bundle.getInt("selectedColor", 0);
                    colorPicker.setBackgroundColor(resultDate);
                }
            }
            else if(requestCode==2) {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    int resultDate = bundle.getInt("selectedDate", 0);
                    iconPicker.setImageResource(resultDate);
                }
            }
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
        iconPicker=view.findViewById(R.id.icon_picker);
        incomeRadio=view.findViewById(R.id.radio_income);
        expenseRadio=view.findViewById(R.id.radio_expense);
        colorPicker=view.findViewById(R.id.color_picker);
        save=view.findViewById(R.id.save_item);
        amount=view.findViewById(R.id.amount_item);
        progress=view.findViewById(R.id.progress);
    }
}

