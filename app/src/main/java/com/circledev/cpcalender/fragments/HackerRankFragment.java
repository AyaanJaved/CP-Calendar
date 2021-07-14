package com.circledev.cpcalender.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.models.CalenderAdapter;
import com.circledev.cpcalender.utils.ContestFilter;
import com.circledev.cpcalender.viewmodels.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HackerRankFragment extends Fragment{
    MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_code_forces, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.hackerank_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        CalenderAdapter calenderAdapter = mainViewModel.getHackerankAdapter();
        recyclerView.setAdapter(calenderAdapter);

        mainViewModel.getAllContestItems().observe(getViewLifecycleOwner(), new Observer<List<AllContestsItem>>() {
            @Override
            public void onChanged(List<AllContestsItem> allContestsItemList) {
                calenderAdapter.updateCalender(ContestFilter.contestsFilter(allContestsItemList, ContestFilter.HACKERRANK_FILTER));
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

}
