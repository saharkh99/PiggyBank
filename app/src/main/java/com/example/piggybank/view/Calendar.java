package com.example.piggybank.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.Util.Utilities;

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
        showTaks();
        return view;
    }

    private void showTaks() {
        RecyclerView recyclerView = view.findViewById(R.id.list_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(ReminderFragment.itemAdapter);


    }

    private void showDate() {
        date = view.findViewById(R.id.date_show);
        dateString = Utilities.getDate(true);
        date.setText(dateString);
    }

}
