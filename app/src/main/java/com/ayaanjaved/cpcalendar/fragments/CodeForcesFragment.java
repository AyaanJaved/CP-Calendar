package com.ayaanjaved.cpcalendar.fragments;

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

import com.ayaanjaved.cpcalendar.R;
import com.ayaanjaved.cpcalendar.adapters.CalenderAdapter;
import com.ayaanjaved.cpcalendar.models.AllContestsItem;
import com.ayaanjaved.cpcalendar.utils.ContestFilter;
import com.ayaanjaved.cpcalendar.viewmodels.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CodeForcesFragment extends Fragment{
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

        RecyclerView recyclerView = view.findViewById(R.id.codeforces_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        CalenderAdapter calenderAdapter = mainViewModel.getCodeForcesAdapter();
        recyclerView.setAdapter(calenderAdapter);

        mainViewModel.getAllContestItems().observe(getViewLifecycleOwner(), new Observer<List<AllContestsItem>>() {
            @Override
            public void onChanged(List<AllContestsItem> allContestsItemList) {
                calenderAdapter.updateCalender(ContestFilter.contestsFilter(allContestsItemList, ContestFilter.CODE_FORCES_FILTER));
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

}
