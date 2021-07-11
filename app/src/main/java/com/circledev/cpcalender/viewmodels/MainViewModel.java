package com.circledev.cpcalender.viewmodels;

import android.app.Application;
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
import com.circledev.cpcalender.networking.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainViewModel extends AndroidViewModel {
//    List<AllContestsItem> mAllContestItems;
    private MutableLiveData<List<AllContestsItem>> mAllContestItems;

    public MutableLiveData<List<AllContestsItem>> getmAllContestItems() {
        if(mAllContestItems == null) {
            mAllContestItems = new MutableLiveData<>();
        }

        return  mAllContestItems;
    }

    public MainViewModel(Application application) {
        super(application);
    }

    public void fetchRequest() {
        String url = "https://kontests.net/api/v1/all";
        Gson gson;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        gson = gsonBuilder.create();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
//                    mAllContestItems = Arrays.asList(gson.fromJson(response, AllContestsItem[].class));
                    mAllContestItems.postValue(Arrays.asList(gson.fromJson(response, AllContestsItem[].class)));
                    Log.i("PostActivity", "posts loaded.");
                    Log.i("PostActivity", "onResponse: ");
//                        mCalenderAdapter.updateCalender(allContestsItems);
                }
                , error -> Log.d("response", "onErrorResponse: " + error.getMessage()));


        VolleySingleton.getInstance(getApplication()).addToRequestQueue(stringRequest);
    }
//    SavedStateHandle savedStateHandle;
//    public MainViewModel(SavedStateHandle savedStateHandle) {
//        this.savedStateHandle = savedStateHandle;
//    }
}
