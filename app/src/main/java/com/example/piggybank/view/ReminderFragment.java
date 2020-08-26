package com.example.piggybank.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.piggybank.R;
import com.example.piggybank.databinding.ReminderFragmentBinding;
import com.example.piggybank.databinding.ReportFragmentBinding;
import com.example.piggybank.model.Types;
import com.example.piggybank.viewmodel.FragmentFactory;
import com.example.piggybank.viewmodel.ReminderViewModel;
import com.example.piggybank.viewmodel.ReportFragmentViewModel;

public class ReminderFragment extends DialogFragment {
    static ReminderViewModel viewModel;
    ReminderFragmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater
                , R.layout.report_fragment, container, false);
        viewModel = ViewModelProviders.of(this,
                new FragmentFactory(getActivity().getApplication()))
                .get(ReminderViewModel.class);
        return binding.getRoot();
    }
}
