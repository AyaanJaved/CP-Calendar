package com.circledev.cpcalender.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.circledev.cpcalender.R;
import com.circledev.cpcalender.utils.CircularOutlineProvider;
import com.circledev.cpcalender.utils.Constants;
import com.circledev.cpcalender.utils.StringToDate;

import java.util.ArrayList;
import java.util.List;


public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>{
    private ArrayList<AllContestsItem> contestsItemArrayList = new ArrayList<AllContestsItem>();
    private OnClickListener onClickListener;

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
        holder.startTimeTextView.setText(contestsItem.getStart_time().toString());
        holder.endTimeTextView.setText(contestsItem.getEnd_time().toString());
        holder.durationTextView.setText(contestsItem.getDuration());
        holder.switchCompat.setChecked(contestsItem.isSubscribed());

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

        if(position == 0) {
            holder.smallLine.setVisibility(View.INVISIBLE);
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



    static class CalenderViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView startTimeTextView;
        TextView endTimeTextView;
        TextView contestNameTextView;
        TextView durationTextView;
        ImageView imageView;
        View smallLine;
        SwitchCompat switchCompat;
        CalenderAdapter.OnClickListener onClickListener;

        public CalenderViewHolder(View itemView, CalenderAdapter.OnClickListener adapterOnClickListener) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.start_time);
            endTimeTextView = itemView.findViewById(R.id.end_time);
            contestNameTextView = itemView.findViewById(R.id.contest_name);
            durationTextView = itemView.findViewById(R.id.duration_text_view);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOutlineProvider(new CircularOutlineProvider());
            smallLine = itemView.findViewById(R.id.small_line);
            switchCompat = itemView.findViewById(R.id.notification_switch);
            onClickListener = adapterOnClickListener;

            switchCompat.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                onClickListener.onItemChecked(getAdapterPosition());
            } else {
                onClickListener.onItemUnchecked(getAdapterPosition());
            }
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
        void onItemChecked(int position);
        void onItemUnchecked(int position);
    }

}
