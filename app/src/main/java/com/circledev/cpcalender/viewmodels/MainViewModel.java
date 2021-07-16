package com.circledev.cpcalender.viewmodels;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.circledev.cpcalender.adapters.CalenderAdapter;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.networking.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements CalenderAdapter.OnClickListener{
    private MutableLiveData<List<AllContestsItem>> mAllContestItems;

    private MutableLiveData<AllContestsItem> onCardItemClick;

    public MutableLiveData<String> getOnSiteImageClick() {
        if(onSiteImageClick == null) {
            onSiteImageClick = new MutableLiveData<>();
        }
        return onSiteImageClick;
    }

    private MutableLiveData<String> onSiteImageClick;

    private CalenderAdapter calenderAdapter ;
    private CalenderAdapter codeChefAdapter;
    private CalenderAdapter codeForcesAdapter;
    private CalenderAdapter hackerrankAdapter;

//    private ContestDao contestDao;
//    protected List<AllContestsItem> subsContests;

    public MutableLiveData<List<AllContestsItem>> getAllContestItems() {
        if(mAllContestItems == null) {
            mAllContestItems = new MutableLiveData<>();
        }

        return  mAllContestItems;
    }

    public CalenderAdapter getCalenderAdapter() {
        if(calenderAdapter == null) {
            calenderAdapter = new CalenderAdapter(this);
        }

        return calenderAdapter;
    }

    public MutableLiveData<AllContestsItem> getOnCardItemClick() {
        if( onCardItemClick == null) {
            onCardItemClick = new MutableLiveData<>();
        }
        return onCardItemClick;
    }

    public CalenderAdapter getCodeChefAdapter() {
        if(codeChefAdapter == null) {
            codeChefAdapter = new CalenderAdapter(this);
        }
        return codeChefAdapter;
    }

    public CalenderAdapter getCodeForcesAdapter() {
        if(codeForcesAdapter == null) {
            codeForcesAdapter = new CalenderAdapter(this);
        }
        return codeForcesAdapter;
    }

    public CalenderAdapter getHackerankAdapter() {
        if(hackerrankAdapter == null) {
            hackerrankAdapter = new CalenderAdapter(this);
        }
        return hackerrankAdapter;
    }

    public MainViewModel(Application application) {
        super(application);
        mAllContestItems = new MutableLiveData<>();
        fetchRequest();

//        ContestDatabase contestDatabase = ContestDatabase.getInstance(application);
//        contestDao = contestDatabase.contestDao();
//        new GetContestAsyncTask(contestDao, subsContests).execute();
    }


    public void fetchRequest() {
        String url = "https://kontests.net/api/v1/all";
        Gson gson;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        gson = gsonBuilder.create();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    ArrayList<AllContestsItem> initialList = new ArrayList<>(Arrays.asList(gson.fromJson(response, AllContestsItem[].class)) );

//                    for(AllContestsItem item: initialList) {
//                        item.setDuration(StringToDate.stringToHours(item.getDuration()));
//                    }
                    Log.i("viewmodel", "fetchRequest: " + initialList.get(0).getStart_time());
                    mAllContestItems.postValue(initialList);

                    Log.i("PostActivity", "posts loaded.");
                    Log.i("PostActivity", "onResponse: ");
//                        mCalenderAdapter.updateCalender(allContestsItems);
                }
                , error -> Log.d("response", "onErrorResponse: " + error.getMessage()));


        VolleySingleton.getInstance(getApplication()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(AllContestsItem item, View view) {
        Log.i("viewmodel", "onClick: " + item.getName());
        if(view.getTag().equals(1)) {
            onSiteImageClick.postValue(item.getUrl());
        } else {
            onCardItemClick.postValue(item);
        }
    }

//    @Override
//    public void onItemChecked(int position) {
//        Log.i("viewmodel", "onItemChecked: " + position);
//        mAllContestItems.getValue().get(position).setSubscribed(true);
////        contestDao.insert();
////        new InsertContestAsyncTask(contestDao).execute(mAllContestItems.getValue().get(position));
//        List<AllContestsItem> allContestsItems = mAllContestItems.getValue();
//        mAllContestItems.postValue(allContestsItems);
//    }



//    @Override
//    public void onItemUnchecked(int position) {
//        Log.i("viewmodel", "onItemUnchecked: " + position);
//        AllContestsItem item = mAllContestItems.getValue().get(position);
//        item.setSubscribed(false);
////        contestDao.delete(item);
//
//        List<AllContestsItem> allContestsItems = mAllContestItems.getValue();
//        mAllContestItems.postValue(allContestsItems);
//    }

//
//    private static class InsertContestAsyncTask extends AsyncTask<AllContestsItem, Void, Void> {
//        private final ContestDao contestDao;
//
//        private InsertContestAsyncTask(ContestDao contestDao){
//            this.contestDao = contestDao;
//        }
//
//        @Override
//        protected Void doInBackground(AllContestsItem... allContestsItems) {
//            contestDao.insert(allContestsItems[0]);
//            return null;
//        }
//    }
//
//    private static class GetContestAsyncTask extends AsyncTask<Void, Void, List<AllContestsItem>> {
//        private final ContestDao contestDao;
//        private List<AllContestsItem> subsContests;
//
//        private GetContestAsyncTask(ContestDao contestDao, List<AllContestsItem> subsContests) {
//            this.contestDao = contestDao;
//            this.subsContests = subsContests;
//        }
//
//        @Override
//        protected List<AllContestsItem> doInBackground(Void... voids) {
//            return contestDao.getAllSubsContests();
//        }
//
//        @Override
//        protected void onPostExecute(List<AllContestsItem> allContestsItemList) {
//            super.onPostExecute(allContestsItemList);
//            subsContests = allContestsItemList;
//        }
//    }


}
