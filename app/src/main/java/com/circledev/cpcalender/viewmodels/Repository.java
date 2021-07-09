package com.circledev.cpcalender.viewmodels;

import android.content.Context;

import com.circledev.cpcalender.networking.VolleyRequest;

public class Repository {
    Context context;
    public void getResponse() {
        VolleyRequest.setRequestQueue(context);
    }
}
