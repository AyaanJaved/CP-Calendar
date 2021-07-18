package com.ayaanjaved.cpcalendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ayaanjaved.cpcalendar.R;
import com.ayaanjaved.cpcalendar.models.AllContestsItem;
import com.ayaanjaved.cpcalendar.utils.CircularOutlineProvider;
import com.ayaanjaved.cpcalendar.utils.StringToDate;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;


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
    public void onBindViewHolder(CalenderAdapter.CalenderViewHolder holder, int position) {
        AllContestsItem contestsItem =  contestsItemArrayList.get(position);
        holder.contestNameTextView.setText( contestsItem.getName());
        holder.startTimeTextView.setText(StringToDate.timeFormat(contestsItem.getStart_time()) );
        holder.endTimeTextView.setText(StringToDate.timeFormat(contestsItem.getEnd_time()));
        holder.startDateTextView.setText(StringToDate.dateFormat(contestsItem.getStart_time()));
        holder.endDateTextView.setText(StringToDate.dateFormat(contestsItem.getEnd_time()));
        holder.durationTextView.setText(StringToDate.stringToHours(contestsItem.getDuration()));

        switch (contestsItem.getSite()) {
            case "CodeChef":
                holder.siteImageView.setImageResource(R.drawable.codechef);
                break;
            case "CodeForces":
                holder.siteImageView.setImageResource(R.drawable.codeforeces);
                break;
            case "TopCoder":
                holder.siteImageView.setImageResource(R.drawable.topcoder);
                break;
            case "AtCoder":
                holder.siteImageView.setImageResource(R.drawable.atcoder);
                break;
            case "HackerRank":
                holder.siteImageView.setImageResource(R.drawable.hackerrank);
                break;
            case "CS Academy":
                holder.siteImageView.setImageResource(R.drawable.csacademy);
                break;
            case "HackerEarth":
                holder.siteImageView.setImageResource(R.drawable.hackerearth);
                break;
            case "Kick Start":
                holder.siteImageView.setImageResource(R.drawable.kickstart);
                break;
            case "LeetCode":
                holder.siteImageView.setImageResource(R.drawable.leetcode);
                break;
            case "Toph":
                holder.siteImageView.setImageResource(R.drawable.toph);
                break;
        }

        if(position == 0) {
            holder.smallLine.setVisibility(View.INVISIBLE);
        }
        if(position == getItemCount() - 1) {
            holder.bigLine.setVisibility(View.INVISIBLE);
        }
        if(contestsItem.getStatus().equals("CODING")) {
            holder.liveAnimation.setVisibility(View.VISIBLE);
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
        CircleImageView siteImageView;
        View smallLine;
        View bigLine;
        ImageButton calenderButton;
        CardView cardView;
        GifImageView liveAnimation;
        CalenderAdapter.OnClickListener onClickListener;

        public CalenderViewHolder(View itemView, CalenderAdapter.OnClickListener adapterOnClickListener) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.start_time);
            endTimeTextView = itemView.findViewById(R.id.end_time);
            contestNameTextView = itemView.findViewById(R.id.contest_name);
            durationTextView = itemView.findViewById(R.id.duration_text_view);
            startDateTextView = itemView.findViewById(R.id.start_date);
            endDateTextView = itemView.findViewById(R.id.end_date);
            siteImageView = itemView.findViewById(R.id.site_image_view);
            siteImageView.setOutlineProvider(new CircularOutlineProvider());
            smallLine = itemView.findViewById(R.id.small_line);
            calenderButton = itemView.findViewById(R.id.calender_button);
            bigLine = itemView.findViewById(R.id.big_line);
            cardView = itemView.findViewById(R.id.cardView);
            liveAnimation = itemView.findViewById(R.id.gifImageView);
            onClickListener = adapterOnClickListener;

            calenderButton.setOnClickListener(this);
            calenderButton.setTag(3);
            cardView.setOnClickListener(this);
            cardView.setTag(2);
            siteImageView.setOnClickListener(this);
            siteImageView.setTag(1);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick (contestsItemArrayList.get(getAdapterPosition()), v);
        }
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        public DateViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.day_text_view);
        }
    }

    public interface OnClickListener{
       void onClick(AllContestsItem item, View view);
    }

}
