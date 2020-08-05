package com.example.piggybank.ui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.R;
import com.example.piggybank.adapter.ItemPickerAdapter;
public class ColorPickerFragment extends DialogFragment {
    View view;
    static Bundle args=new Bundle();
    static ColorPickerFragment fragment = new ColorPickerFragment();

    int[] colors={R.color.color1,R.color.color2,R.color.color3,R.color.color4,R.color.color5,R.color.color6,
            R.color.color7,R.color.color8,R.color.color9,R.color.color10,R.color.color11,R.color.color12};
    RecyclerView recyclerView;
    ItemPickerAdapter adapter;
    private static int index;
    public static ColorPickerFragment newInstance() {
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.color_picker_fragment, container, false);
        recyclerView= view.findViewById(R.id.recycle);
        recyclerView = view.findViewById(R.id.recycle);
        adapter=new ItemPickerAdapter(colors,this.getContext());
        adapter.setOnItemClickListener(new ItemPickerAdapter.onItemClickListener(){
            @Override
            public void onItemClick(int position) {
                index=position;
                Intent intent = new Intent();
                intent.putExtra("selectedDate",getResources().getColor(colors[index]));
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                getFragmentManager().popBackStackImmediate();
            }
        });
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        getDialog().setTitle("انتخاب رنگ");
        getDialog().setContentView(R.layout.color_picker_fragment);
        return  view;
    }
}
