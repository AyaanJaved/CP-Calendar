package com.ayaanjaved.cpcalendar.utils;

import com.ayaanjaved.cpcalendar.models.AllContestsItem;

import java.util.ArrayList;
import java.util.List;

public class ContestsToUrlList {
    public static List<String> getUrlList(List<AllContestsItem> allContestsItemList) {
        List<String> stringList = new ArrayList<>();
        if(allContestsItemList == null)
            return stringList;
        for(AllContestsItem allContestsItem: allContestsItemList) {
            stringList.add(allContestsItem.getUrl());
        }
        return stringList;
    }
}
