package com.circledev.cpcalender.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.utils.CircularOutlineProvider;
import com.circledev.cpcalender.utils.Constants;
import com.circledev.cpcalender.utils.StringToDate;

import java.util.ArrayList;
import java.util.List;


public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>{
    private ArrayList<AllContestsItem> contestsItemArrayList = new ArrayList<AllContestsItem>();

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

        switch (contestsItem.getSite()) {
            case "CodeChef":
                holder.imageView.setImageResource(R.drawable.codechef);
                break;
            case "CodeForces":
                holder.imageView.setImageResource(R.drawable.codeforeces);
                break;
            case "TopCoder":
                holder.imageView.setImageResource(R.drawable.topcoder);
                break;
            case "AtCoder":
                holder.imageView.setImageResource(R.drawable.atcoder);
                break;
            case "HackerRank":
                holder.imageView.setImageResource(R.drawable.hackerrank);
                break;
        }
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
        TextView contestNameTextView;
        TextView durationTextView;
        ImageView imageView;

        public CalenderViewHolder(View itemView) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.start_time);
            endTimeTextView = itemView.findViewById(R.id.end_time);
            contestNameTextView = itemView.findViewById(R.id.contest_name);
            durationTextView = itemView.findViewById(R.id.duration_text_view);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOutlineProvider(new CircularOutlineProvider());
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
