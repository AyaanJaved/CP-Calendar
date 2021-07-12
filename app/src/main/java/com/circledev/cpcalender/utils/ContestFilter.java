package com.circledev.cpcalender.utils;

import com.circledev.cpcalender.models.AllContestsItem;

import java.util.ArrayList;
import java.util.List;

public class ContestFilter {
    static public ArrayList<AllContestsItem> codeChefFilter(ArrayList<AllContestsItem> allContestsItemList) {
        ArrayList<AllContestsItem> contestsItems = new ArrayList<>();

       for(AllContestsItem item: allContestsItemList) {

          if(item.getSite().equals("CodeChef") ) {
              contestsItems.add(item);
          }
       }
       return contestsItems;
    }

    static public ArrayList<AllContestsItem> codeForcesFilter(ArrayList<AllContestsItem> allContestsItemList) {
        ArrayList<AllContestsItem> contestsItems = new ArrayList<>();

        for(AllContestsItem item: allContestsItemList) {

            if(item.getSite().equals("CodeForces") ) {
                contestsItems.add(item);
            }
        }
        return contestsItems;
    }
}