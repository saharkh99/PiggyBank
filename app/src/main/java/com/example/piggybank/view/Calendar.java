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
import com.example.piggybank.Util.Types;

public class Calendar extends DialogFragment {
    private View view;
    private TextView date;
    private String dateString;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_fragment, container, false);
        getDialog().setContentView(R.layout.calendar_fragment);
        showDate();
        return  view;
    }


    private void showDate() {
        date=view.findViewById(R.id.date_show);
        dateString=Types.getDate(true);
        date.setText(dateString);
    }

}
