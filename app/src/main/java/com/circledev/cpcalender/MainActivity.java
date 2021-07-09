package com.circledev.cpcalender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.models.CalenderAdapter;
import com.circledev.cpcalender.networking.VolleyRequest;
import com.circledev.cpcalender.networking.VolleySingleton;
import com.circledev.cpcalender.viewmodels.MainViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    CalenderAdapter mCalenderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.calender_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCalenderAdapter = new CalenderAdapter();
        recyclerView.setAdapter(mCalenderAdapter);

        fetchRequest();
    }

    private void fetchRequest() {
        String url = "https://kontests.net/api/v1/all";
        Gson gson;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<AllContestsItem> allContestsItems = Arrays.asList(gson.fromJson(response, AllContestsItem[].class));
                        Log.i("PostActivity", allContestsItems.size() + " posts loaded.");
                        Log.i("PostActivity", "onResponse: " + allContestsItems.get(0).getUrl());
                        mCalenderAdapter.updateCalender(allContestsItems);
                    }
                }
                , error -> Log.d("response", "onErrorResponse: " + error.getMessage()));


        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}