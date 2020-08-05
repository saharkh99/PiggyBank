package com.example.piggybank;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.example.piggybank.ui.ColorPickerFragment;
import com.example.piggybank.ui.IconPickerFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class NewItemFragment extends BottomSheetDialogFragment {
    private RadioButton incomeRadio,expenseRadio;
    private boolean isCost=true;
    private Button colorPicker;
    private ImageView iconPicker;
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
        chooseColor();
        chooseIcon();
        return view;
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
                    int resultDate = bundle.getInt("selectedColor", 0);
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
    }
}

