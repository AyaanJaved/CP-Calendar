package com.ayaanjaved.cpcalendar.viewmodels;

import android.content.Context;

import com.ayaanjaved.cpcalendar.networking.VolleyRequest;

public class Repository {
    Context context;
    public void getResponse() {
        VolleyRequest.setRequestQueue(context);
    }
}
