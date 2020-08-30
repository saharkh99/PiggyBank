package com.example.piggybank.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piggybank.Util.GridRecyclerView;
import com.example.piggybank.R;
import com.example.piggybank.adapter.ItemAdapter;
import com.example.piggybank.databinding.ListItemsFragmentBinding;
import com.example.piggybank.Util.Utilities;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.ListItemViewModel;
import com.google.android.material.snackbar.Snackbar;

/**
 * The main idea of this class is showing all the transactions and tasks for edit and delete
 */
public class ListItems extends DialogFragment {
    static ListItemViewModel viewModel;
    ListItemsFragmentBinding binding;
    GridRecyclerView gridRecyclerView;
    ItemAdapter itemAdapter;
    private boolean result = false;
    private int index;
    private ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater
                , R.layout.list_items_fragment, container, false);
        viewModel = ViewModelProviders.of(this,
                new FragmentFactory(getActivity().getApplication()))
                .get(ListItemViewModel.class);
        gridRecyclerView = binding.recyclerView;
        imageView = binding.remove;
        setRecyclerView();
        removeItem();
        binding.setClickHandler(new AddAndEditTransactionClickHandlers(this.getView()));
        getDialog().setContentView(R.layout.list_items_fragment);
        return binding.getRoot();
    }

    private void removeItem() {


    }

    private void setRecyclerView() {
        if (viewModel.getItemsMonthly(Utilities.getDate(false)) != null)

            viewModel.getItemsMonthly(Utilities.getDate(false))
                    .observe(getActivity(), transactions -> {
                        itemAdapter = new ItemAdapter(getActivity(), transactions, true);
                        itemAdapter.setOnItemClickListener((position, v) -> {
                                    index = position;
                                    result = v;
                                }
                        );
                        gridRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4, RecyclerView.HORIZONTAL, false));
                        gridRecyclerView.setAdapter(itemAdapter);

                    });
    }

    public class AddAndEditTransactionClickHandlers {
        private View context;
        private Snackbar snackbar;


        public AddAndEditTransactionClickHandlers(View context) {
            this.context = context;
            binding.setClickHandler(this);
        }


        /**
         * click for deleting items
         *
         * @param view
         */
        public void onButtonClicked(View view) {


        }
    }
}




