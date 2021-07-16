package com.circledev.cpcalender.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.models.CalenderAdapter;
import com.circledev.cpcalender.utils.ContestFilter;
import com.circledev.cpcalender.viewmodels.MainViewModel;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class MainFragment extends Fragment{

    MainViewModel mainViewModel;
    CalenderAdapter mCalenderAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.calender_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.scheduleLayoutAnimation();

        mCalenderAdapter = mainViewModel.getCalenderAdapter();
        recyclerView.setAdapter(mCalenderAdapter);

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        mainViewModel.getAllContestItems().observe(getViewLifecycleOwner(), new Observer<List<AllContestsItem>>() {
            @Override
            public void onChanged(List<AllContestsItem> contestsItemList) {
                mainViewModel.getCalenderAdapter().updateCalender(ContestFilter.mainFragmentContestFilter(contestsItemList));
                view.findViewById(R.id.loading_anim).setVisibility(View.GONE);
                Log.i("mainfragment", "onChanged: update calender");
                recyclerView.scheduleLayoutAnimation();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}