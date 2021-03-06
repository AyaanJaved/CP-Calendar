package com.ayaanjaved.cpcalendar.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ayaanjaved.cpcalendar.fragments.AtCoderFragment;
import com.ayaanjaved.cpcalendar.fragments.CodeChefFragment;
import com.ayaanjaved.cpcalendar.fragments.CodeForcesFragment;
import com.ayaanjaved.cpcalendar.fragments.CsAcademyFragment;
import com.ayaanjaved.cpcalendar.fragments.HackerEarthFragment;
import com.ayaanjaved.cpcalendar.fragments.HackerRankFragment;
import com.ayaanjaved.cpcalendar.fragments.KickstartFragment;
import com.ayaanjaved.cpcalendar.fragments.LeetCodeFragment;
import com.ayaanjaved.cpcalendar.fragments.MainFragment;
import com.ayaanjaved.cpcalendar.fragments.TopCoderFragment;
import com.ayaanjaved.cpcalendar.fragments.TophFragment;

import org.jetbrains.annotations.NotNull;


public class PagerAdapter extends FragmentStateAdapter {
    CalenderAdapter calenderAdapter;

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
            case 2:
                return new CodeForcesFragment();
            case 3:
                return new AtCoderFragment();
            case 4:
                return new TopCoderFragment();
            case 5:
                return new HackerEarthFragment();
            case 6:
                return new LeetCodeFragment();
            case 7:
                return new KickstartFragment();
            case 8:
                return new HackerRankFragment();
            case 9:
                return new TophFragment();
            case 10:
                return new CsAcademyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 11;
    }
}