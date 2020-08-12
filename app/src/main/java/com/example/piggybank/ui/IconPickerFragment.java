package com.example.piggybank.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.piggybank.R;
import com.example.piggybank.adapter.ColorPickerAdapter;
import com.example.piggybank.adapter.IconPickerAdapter;

public class IconPickerFragment extends DialogFragment {
    View view;
    int[] icons={R.drawable.ambulance,R.drawable.barbershop,R.drawable.bill,R.drawable.cardexchange,R.drawable.check,R.drawable.clothes
            , R.drawable.food,R.drawable.gasstation,R.drawable.gift,R.drawable.income,R.drawable.internet,R.drawable.transport};
    RecyclerView recyclerView;
    IconPickerAdapter adapter;
    private TextView title;
    private static int index;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.color_picker_fragment, container, false);
        findView();
        title.setText("نوع هزینه");
        setRecyclerView();
        getDialog().setContentView(R.layout.color_picker_fragment);
        return  view;
    }
    private void setRecyclerView() {
        adapter=new IconPickerAdapter(icons,getActivity());
        adapter.setOnItemClickListener(new IconPickerAdapter.onItemClickListener(){
            @Override
            public void onItemClick(int position) {
                    index = position;
                    Intent intent = new Intent();
                    intent.putExtra("selectedDate", index);
                    Log.d("index", index+"");
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                    getFragmentManager().popBackStackImmediate();
            }
        });
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void findView() {
        recyclerView= view.findViewById(R.id.recycle);
        title=view.findViewById(R.id.title_dialog);

    }
}
