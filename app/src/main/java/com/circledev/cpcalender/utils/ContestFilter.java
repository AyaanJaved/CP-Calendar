package com.circledev.cpcalender.utils;

import com.circledev.cpcalender.models.AllContestsItem;

import java.util.ArrayList;
import java.util.List;

public class ContestFilter {
    static public final String MAIN_FRAGMENT_FILTER = "main";
    static public final String CODE_CHEF_FILTER = "CodeChef";
    static public final String CODE_FORCES_FILTER = "CodeForces";
    static public final String TOP_CODER_FILTER = "TopCoder";
    static public final String ATCODER_FILTER = "AtCoder";
    static public final String HACKERRANK_FILTER = "HackerRank";
    static public final String CS_ACADEMY_FILTER = "CS Academy";
    static public final String HACKER_EARTH_FILTER = "HackerEarth";
    static public final String KICKSTART_FILTER = "Kick Start";
    static public final String LEETCODE_FILTER = "LeetCode";
    static public final String TOPH_FILTER = "Toph";

    static public ArrayList<AllContestsItem> mainFragmentContestFilter(List<AllContestsItem> allContestsItemList) {
        ArrayList<AllContestsItem> contestsItems = new ArrayList<>();

        for(AllContestsItem item: allContestsItemList) {
            int durationInt = (int) Double.parseDouble(item.getDuration());
            if(durationInt < 172900) {
                contestsItems.add(item);
            }
        }
        return contestsItems;
    }

    static public ArrayList<AllContestsItem> contestsFilter(List<AllContestsItem> allContestsItemList, String filterType) {
        ArrayList<AllContestsItem> contestsItems = new ArrayList<>();

        for(AllContestsItem item: allContestsItemList) {

            if(item.getSite().equals(filterType) ) {
                contestsItems.add(item);
            }
        }
        return contestsItems;
    }
}
