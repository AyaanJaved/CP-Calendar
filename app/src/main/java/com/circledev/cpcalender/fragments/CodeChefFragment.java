package com.circledev.cpcalender.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.models.CalenderAdapter;

import org.jetbrains.annotations.NotNull;

public class CodeChefFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_code_chef, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.codechef_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        CalenderAdapter calenderAdapter = new CalenderAdapter();
        recyclerView.setAdapter(calenderAdapter);

        super.onViewCreated(view, savedInstanceState);
    }
}