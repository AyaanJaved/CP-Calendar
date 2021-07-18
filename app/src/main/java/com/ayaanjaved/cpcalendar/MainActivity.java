package com.ayaanjaved.cpcalendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.ayaanjaved.cpcalendar.adapters.PagerAdapter;
import com.ayaanjaved.cpcalendar.models.AllContestsItem;
import com.ayaanjaved.cpcalendar.viewmodels.MainViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MainViewModel mainViewModel;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.night_mode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText(myToolbar.getTitle());
        TextView date = findViewById(R.id.toolbar_date);
        String dateString = new SimpleDateFormat("MMMM dd", Locale.getDefault()).format(new Date());
        date.setText(dateString);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        viewPager2 = findViewById(R.id.viewPager2);

        viewPager2.setAdapter(new PagerAdapter(getSupportFragmentManager(), getLifecycle()));

        tabLayout = findViewById(R.id.tablayout);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("All");
                        break;
                    case 1:
                        tab.setText("CodeChef");
                        break;
                    case 2:
                        tab.setText("CodeForces");
                        break;
                    case 3:
                        tab.setText("AtCoder");
                        break;
                    case 4:
                        tab.setText("Topcoder");
                        break;
                    case 5:
                        tab.setText("HackerEarth");
                        break;
                    case 6:
                        tab.setText("LeetCode");
                        break;
                    case 7:
                        tab.setText("Kickstart");
                        break;
                    case 8:
                        tab.setText("HackerRank");
                        break;
                    case 9:
                        tab.setText("Toph");
                        break;
                    case 10:
                        tab.setText("CsAcademy");
                        break;
                    default:
                        //
                }
            }
        }).attach();

        mainViewModel.getOnCardItemClick().observe(this, new Observer<AllContestsItem>() {
            @Override
            public void onChanged(AllContestsItem item) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, item.getName());
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, item.getStart_time().getTime());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, item.getEnd_time().getTime());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, item.getUrl());
                startActivity(intent);
            }
        });

        mainViewModel.getOnSiteImageClick().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No Browser Found" + s, Toast.LENGTH_LONG).show();
                }

            }
        });

        mainViewModel.getResponse().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if( !response ) {
                    findViewById(R.id.viewPager2).setVisibility(View.GONE);
                    findViewById(R.id.no_internet_imageview).setVisibility(View.VISIBLE);
                    findViewById(R.id.no_internet_tv1).setVisibility(View.VISIBLE);
                    findViewById(R.id.no_internet_tv2).setVisibility(View.VISIBLE);
                }
            }
        });

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}