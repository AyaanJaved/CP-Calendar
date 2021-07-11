package com.circledev.cpcalender.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.utils.StringToDate;

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
    public int getItemViewType(int position) {
        if(position%5 == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(CalenderAdapter.CalenderViewHolder holder, int position) {
        AllContestsItem contestsItem =  contestsItemArrayList.get(position);
        holder.contestNameTextView.setText( contestsItem.getName());
        holder.startTimeTextView.setText(contestsItem.getStart_time().toString());
        holder.endTimeTextView.setText(contestsItem.getEnd_time().toString());
        holder.durationTextView.setText(contestsItem.getDuration());
    }

    @Override
    public int getItemCount() {
        return contestsItemArrayList.size();
    }

    public void updateCalender(List<AllContestsItem> updatedContestItems) {
       contestsItemArrayList.clear();
       for(AllContestsItem item: updatedContestItems) {
//          item.setStart_time(StringToDate.stringToDate(item.getStart_time()));
//          item.setEnd_time(StringToDate.stringToDate(item.getEnd_time()));
          item.setDuration(StringToDate.stringToHours(item.getDuration()));
       }
       contestsItemArrayList.addAll(updatedContestItems);

       notifyDataSetChanged();
    }

    static class CalenderViewHolder extends RecyclerView.ViewHolder {
        TextView startTimeTextView;
        TextView endTimeTextView;
        TextView contestNameTextView;
        TextView durationTextView;

        public CalenderViewHolder(View itemView) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.start_time);
            endTimeTextView = itemView.findViewById(R.id.end_time);
            contestNameTextView = itemView.findViewById(R.id.contest_name);
            durationTextView = itemView.findViewById(R.id.duration_text_view);
        }
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        public DateViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }
    }

}