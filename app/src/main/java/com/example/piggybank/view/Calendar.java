package com.example.piggybank.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.piggybank.R;
import com.example.piggybank.model.Types;

public class Calendar extends DialogFragment {
    View view;
    TextView date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_fragment, container, false);
        getDialog().setContentView(R.layout.calendar_fragment);
        findView();
        date.setText(Types.getDate(true));
        return  view;
    }


    private void findView() {

        date=view.findViewById(R.id.date_show);
    }
}
