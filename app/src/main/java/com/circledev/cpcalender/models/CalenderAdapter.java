package com.circledev.cpcalender.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.circledev.cpcalender.R;

import java.util.ArrayList;
import java.util.List;


public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>{
    ArrayList<AllContestsItem> contestsItemArrayList = new ArrayList<AllContestsItem>();

//    public CalenderAdapter(List<AllContestsItem> contestsItemList) {
//        contestsItemArrayList = (ArrayList<AllContestsItem>) contestsItemList;
//    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_card, parent, false);
        return new CalenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalenderAdapter.CalenderViewHolder holder, int position) {
        AllContestsItem contestsItem =  contestsItemArrayList.get(position);
        holder.siteNameTextView.setText( contestsItem.getSite());
        holder.startTimeTextView.setText(contestsItem.getStart_time());
        holder.endTimeTextView.setText(contestsItem.getEnd_time());
    }

    @Override
    public int getItemCount() {
        return contestsItemArrayList.size();
    }

    public void updateCalender(List<AllContestsItem> updatedContestItems) {
       contestsItemArrayList.clear();
       contestsItemArrayList.addAll(updatedContestItems);

       notifyDataSetChanged();
    }

    static class CalenderViewHolder extends RecyclerView.ViewHolder {
        TextView startTimeTextView;
        TextView endTimeTextView;
        TextView siteNameTextView;

        public CalenderViewHolder(View itemView) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.start_time);
            endTimeTextView = itemView.findViewById(R.id.end_time);
            siteNameTextView = itemView.findViewById(R.id.site_name);
        }
    }

}
