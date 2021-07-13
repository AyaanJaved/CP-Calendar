package com.circledev.cpcalender.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.models.CalenderAdapter;
import com.circledev.cpcalender.networking.VolleySingleton;
import com.circledev.cpcalender.utils.ContestFilter;
import com.circledev.cpcalender.utils.StringToDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.security.auth.login.LoginException;

public class MainViewModel extends AndroidViewModel implements CalenderAdapter.OnClickListener {
    private MutableLiveData<List<AllContestsItem>> mAllContestItems;
    private MutableLiveData<List<AllContestsItem>> codeChefContestItems;
    private MutableLiveData<List<AllContestsItem>> codeForcesContestItems;

    private CalenderAdapter calenderAdapter ;
    private CalenderAdapter codeChefAdapter;
    private CalenderAdapter codeForcesAdapter;

    private SharedPreferences sharedPreferences;

    public MutableLiveData<List<AllContestsItem>> getmAllContestItems() {
        if(mAllContestItems == null) {
            mAllContestItems = new MutableLiveData<>();
        }

        return  mAllContestItems;
    }

    public MutableLiveData<List<AllContestsItem>> getCodeChefContestItems() {
        if(codeChefContestItems == null) {
            codeChefContestItems = new MutableLiveData<>();
        }

        return  codeChefContestItems;
    }

    public MutableLiveData<List<AllContestsItem>> getCodeForcesContestItems() {
        if(codeForcesContestItems == null) {
            codeForcesContestItems = new MutableLiveData<>();
        }

        return  codeForcesContestItems;
    }

    public CalenderAdapter getCalenderAdapter() {
        if(calenderAdapter == null) {
            calenderAdapter = new CalenderAdapter(this);
        }

        return calenderAdapter;
    }

    public CalenderAdapter getCodeChefAdapter() {
        if(codeChefAdapter == null) {
            codeChefAdapter = new CalenderAdapter(this);
        }
        return codeChefAdapter;
    }

    public CalenderAdapter getCodeForcesAdapter() {
        if(codeForcesAdapter == null) {
            codeForcesAdapter = new CalenderAdapter(this);
        }
        return codeForcesAdapter;
    }

    public MainViewModel(Application application) {
        super(application);
        mAllContestItems = new MutableLiveData<>();
        codeChefContestItems = new MutableLiveData<>();
        codeForcesContestItems = new MutableLiveData<>();
        fetchRequest();

        sharedPreferences = getApplication().getSharedPreferences("subs", Context.MODE_PRIVATE);
    }

    public void fetchRequest() {
        String url = "https://kontests.net/api/v1/all";
        Gson gson;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        gson = gsonBuilder.create();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    ArrayList<AllContestsItem> initialList = new ArrayList<>(Arrays.asList(gson.fromJson(response, AllContestsItem[].class)) );

                    for(AllContestsItem item: initialList) {
                        item.setDuration(StringToDate.stringToHours(item.getDuration()));
                    }
                    mAllContestItems.postValue(initialList);
                    codeChefContestItems.postValue(ContestFilter.codeChefFilter(initialList));
                    codeForcesContestItems.postValue(ContestFilter.codeForcesFilter(initialList));

                    Log.i("PostActivity", "posts loaded.");
                    Log.i("PostActivity", "onResponse: ");
//                        mCalenderAdapter.updateCalender(allContestsItems);
                }
                , error -> Log.d("response", "onErrorResponse: " + error.getMessage()));


        VolleySingleton.getInstance(getApplication()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemChecked(int position) {
        Log.i("viewmodel", "onItemChecked: " + position);
    }

    @Override
    public void onItemUnchecked(int position) {
        Log.i("viewmodel", "onItemUnchecked: " + position);
    }
}
