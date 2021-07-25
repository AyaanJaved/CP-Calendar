package com.ayaanjaved.cpcalendar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

        mCalenderAdapter = mainViewModel.getCalenderAdapter();
        recyclerView.setAdapter(mCalenderAdapter);

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        mainViewModel.getAllContestItems().observe(getViewLifecycleOwner(), new Observer<List<AllContestsItem>>() {
            @Override
            public void onChanged(List<AllContestsItem> contestsItemList) {
                mainViewModel.getCalenderAdapter().updateCalender(ContestFilter.mainFragmentContestFilter(contestsItemList));
                view.findViewById(R.id.loading_anim).setVisibility(View.GONE);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}