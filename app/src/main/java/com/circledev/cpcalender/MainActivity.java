package com.circledev.cpcalender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.models.CalenderAdapter;
import com.circledev.cpcalender.models.PagerAdapter;
import com.circledev.cpcalender.networking.VolleyRequest;
import com.circledev.cpcalender.networking.VolleySingleton;
import com.circledev.cpcalender.utils.StringToDate;
import com.circledev.cpcalender.viewmodels.MainViewModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager2);

        viewPager2.setAdapter(new PagerAdapter(getSupportFragmentManager(), getLifecycle()));

        tabLayout = findViewById(R.id.tablayout);

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(""+position + 1))).attach();
    }

}