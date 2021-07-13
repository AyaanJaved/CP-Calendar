package com.circledev.cpcalender.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.ArraySet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.circledev.cpcalender.database.ContestDao;
import com.circledev.cpcalender.database.ContestDatabase;
import com.circledev.cpcalender.database.ContestSubs;
import com.circledev.cpcalender.models.AllContestsItem;
import com.circledev.cpcalender.models.CalenderAdapter;
import com.circledev.cpcalender.networking.VolleySingleton;
import com.circledev.cpcalender.utils.ContestFilter;
import com.circledev.cpcalender.utils.ContestsToUrlList;
import com.circledev.cpcalender.utils.StringToDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.security.auth.login.LoginException;

public class MainViewModel extends AndroidViewModel implements CalenderAdapter.OnClickListener {
    private MutableLiveData<List<AllContestsItem>> mAllContestItems;
    private List<AllContestsItem> codeChefContestItems;
    private MutableLiveData<List<AllContestsItem>> codeForcesContestItems;

    private CalenderAdapter calenderAdapter ;
    private CalenderAdapter codeChefAdapter;
    private CalenderAdapter codeForcesAdapter;

//    private ContestDao contestDao;
//    protected List<AllContestsItem> subsContests;

    public MutableLiveData<List<AllContestsItem>> getmAllContestItems() {
        if(mAllContestItems == null) {
            mAllContestItems = new MutableLiveData<>();
        }

        return  mAllContestItems;
    }

    public List<AllContestsItem> getCodeChefContestItems() {
        if(codeChefContestItems == null) {
            codeChefContestItems = new ArrayList<>();
        }

        return  codeChefContestItems;
    }

    public MutableLiveData<List<AllContestsItem>> getCodeForcesContestItems() {
        if(codeForcesContestItems == null) {
            codeForcesContestItems = new MutableLiveData<>();
        }

        return  codeForcesContestItems;
    }

    public CalenderAdapter getCalenderAdapter() {
        if(calenderAdapter == null) {
            calenderAdapter = new CalenderAdapter(this);
        }

        return calenderAdapter;
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

    public MainViewModel(Application application) {
        super(application);
        mAllContestItems = new MutableLiveData<>();
        codeForcesContestItems = new MutableLiveData<>();
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

                    for(AllContestsItem item: initialList) {
                        item.setDuration(StringToDate.stringToHours(item.getDuration()));
//                        Log.i("viewmodel", ContestsToUrlList.getUrlList(subsContests).toString());
//                        if( Objects.requireNonNull(ContestsToUrlList.getUrlList(subsContests)).contains(item.getUrl())) {
//                            item.setSubscribed(true);
//                            Log.i("viewmodel", "matched");
//                        }
                    }
                    mAllContestItems.postValue(initialList);

                    Log.i("PostActivity", "posts loaded.");
                    Log.i("PostActivity", "onResponse: ");
//                        mCalenderAdapter.updateCalender(allContestsItems);
                }
                , error -> Log.d("response", "onErrorResponse: " + error.getMessage()));


        VolleySingleton.getInstance(getApplication()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemChecked(int position) {
        Log.i("viewmodel", "onItemChecked: " + position);
        mAllContestItems.getValue().get(position).setSubscribed(true);
//        contestDao.insert();
//        new InsertContestAsyncTask(contestDao).execute(mAllContestItems.getValue().get(position));
        List<AllContestsItem> allContestsItems = mAllContestItems.getValue();
        mAllContestItems.postValue(allContestsItems);
    }

    @Override
    public void onItemUnchecked(int position) {
        Log.i("viewmodel", "onItemUnchecked: " + position);
        AllContestsItem item = mAllContestItems.getValue().get(position);
        item.setSubscribed(false);
//        contestDao.delete(item);

        List<AllContestsItem> allContestsItems = mAllContestItems.getValue();
        mAllContestItems.postValue(allContestsItems);
    }

    private static class InsertContestAsyncTask extends AsyncTask<AllContestsItem, Void, Void> {
        private ContestDao contestDao;

        private InsertContestAsyncTask(ContestDao contestDao){
            this.contestDao = contestDao;
        }

        @Override
        protected Void doInBackground(AllContestsItem... allContestsItems) {
            contestDao.insert(allContestsItems[0]);
            return null;
        }
    }

    private static class GetContestAsyncTask extends AsyncTask<Void, Void, List<AllContestsItem>> {
        private ContestDao contestDao;
        private List<AllContestsItem> subsContests;

        private GetContestAsyncTask(ContestDao contestDao, List<AllContestsItem> subsContests) {
            this.contestDao = contestDao;
            this.subsContests = subsContests;
        }

        @Override
        protected List<AllContestsItem> doInBackground(Void... voids) {
            return contestDao.getAllSubsContests();
        }

        @Override
        protected void onPostExecute(List<AllContestsItem> allContestsItemList) {
            super.onPostExecute(allContestsItemList);
            subsContests = allContestsItemList;
        }
    }


}
