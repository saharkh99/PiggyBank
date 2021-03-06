package com.example.piggybank.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.piggybank.R;
import com.example.piggybank.adapter.ColorPickerAdapter;
import com.example.piggybank.databinding.FeaturePickerFragmentBinding;

public class ColorPickerFragment extends DialogFragment {
    private FeaturePickerFragmentBinding colorPickerFragmentBinding;
    int[] colors={R.color.color1,R.color.color2,R.color.color3,R.color.color4,R.color.color5,R.color.color6,
            R.color.color7,R.color.color8,R.color.color9,R.color.color10,R.color.color11,R.color.color12};
    RecyclerView recyclerView;
    ColorPickerAdapter adapter;
    private static int index;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        colorPickerFragmentBinding= DataBindingUtil.inflate(inflater
                ,R.layout.feature_picker_fragment,container,false);
        binding();
        setRecyclerView();
        getDialog().setContentView(R.layout.feature_picker_fragment);
        return  colorPickerFragmentBinding.getRoot();
    }

    private void setRecyclerView() {
        adapter=new ColorPickerAdapter(colors,this.getContext());
        adapter.setOnItemClickListener(position -> {
                index=position;
                Intent intent = new Intent();
                intent.putExtra("selectedColor", getResources().getColor(colors[index]));
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                getFragmentManager().popBackStackImmediate();

        });
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void binding() {
        recyclerView= colorPickerFragmentBinding.recycle;
    }
}
