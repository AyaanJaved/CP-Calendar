package com.circledev.cpcalender.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.utils.CircularOutlineProvider;
import com.circledev.cpcalender.utils.Constants;
import com.circledev.cpcalender.utils.StringToDate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;


public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>{
    private final ArrayList<AllContestsItem> contestsItemArrayList = new ArrayList<AllContestsItem>();
    private final OnClickListener onClickListener;

    public CalenderAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_card, parent, false);
        return new CalenderViewHolder(view, onClickListener);
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
        holder.startTimeTextView.setText(StringToDate.timeFormat(contestsItem.getStart_time()) );
        holder.endTimeTextView.setText(StringToDate.timeFormat(contestsItem.getEnd_time()));
        holder.startDateTextView.setText(StringToDate.dateFormat(contestsItem.getStart_time()));
        holder.endDateTextView.setText(StringToDate.dateFormat(contestsItem.getEnd_time()));
//        holder.durationTextView.setText(contestsItem.getDuration());
        holder.durationTextView.setText(StringToDate.stringToHours(contestsItem.getDuration()));

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
            case "CS Academy":
                holder.imageView.setImageResource(R.drawable.csacademy);
                break;
            case "HackerEarth":
                holder.imageView.setImageResource(R.drawable.hackerearth);
                break;
            case "Kick Start":
                holder.imageView.setImageResource(R.drawable.kickstart);
                break;
            case "LeetCode":
                holder.imageView.setImageResource(R.drawable.leetcode);
                break;
            case "Toph":
                holder.imageView.setImageResource(R.drawable.toph);
                break;
        }

        if(position == 0) {
            holder.smallLine.setVisibility(View.INVISIBLE);
        }
        if(position == getItemCount() - 1) {
            holder.bigLine.setVisibility(View.INVISIBLE);
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



    class CalenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView startTimeTextView;
        TextView endTimeTextView;
        TextView contestNameTextView;
        TextView durationTextView;
        TextView startDateTextView;
        TextView endDateTextView;
        ImageView imageView;
        View smallLine;
        View bigLine;
        ImageButton calenderButton;
        CardView cardView;
        CalenderAdapter.OnClickListener onClickListener;

        public CalenderViewHolder(View itemView, CalenderAdapter.OnClickListener adapterOnClickListener) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.start_time);
            endTimeTextView = itemView.findViewById(R.id.end_time);
            contestNameTextView = itemView.findViewById(R.id.contest_name);
            durationTextView = itemView.findViewById(R.id.duration_text_view);
            startDateTextView = itemView.findViewById(R.id.start_date);
            endDateTextView = itemView.findViewById(R.id.end_date);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOutlineProvider(new CircularOutlineProvider());
            smallLine = itemView.findViewById(R.id.small_line);
            calenderButton = itemView.findViewById(R.id.calender_button);
            bigLine = itemView.findViewById(R.id.big_line);
            cardView = itemView.findViewById(R.id.cardView);
            onClickListener = adapterOnClickListener;

            calenderButton.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick (contestsItemArrayList.get(getAdapterPosition()));
        }
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        public DateViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }
    }

    public interface OnClickListener{
       void onClick(AllContestsItem item);
    }

}
