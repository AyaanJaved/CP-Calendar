package com.circledev.cpcalender.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.circledev.cpcalender.fragments.CodeChefFragment;
import com.circledev.cpcalender.fragments.MainFragment;

import org.jetbrains.annotations.NotNull;


public class PagerAdapter extends FragmentStateAdapter {


    public PagerAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                Log.i("viewpageradapter", "createFragment: ");
                return new MainFragment();
            case 1:
                return new CodeChefFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}