package com.evergreen.eve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ApplyAdvanceMainF extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apply_advance_mainf, container, false);
    }

    ApplyAdvanceLoadPageF applyAdvanceLoadPageF = new ApplyAdvanceLoadPageF();
    Button applyButton = getView().findViewById(R.id.btnApplyAdvance);




}