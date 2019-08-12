package com.example.jay.ADHDHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class DutyAdapter extends RecyclerView.Adapter<DutyAdapter.DutyViewHolder> {

    private List<Duty> dutyList;
    public Context context;

    DutyAdapter(List<Duty> dutyList, Context context) {
        this.dutyList = dutyList;
        this.context = context;
    }

    public DutyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.duties_fragment, null);
        return new DutyViewHolder(view);
    }

    @SuppressLint("RestrictedApi")
    public void onBindViewHolder(DutyViewHolder viewHolder, final int position) {

        Duty duty = dutyList.get(position);

        //Initialize viewholder data
        viewHolder.photo.setImageResource(duty.getImage());
        viewHolder.name.setText(duty.getName());
        viewHolder.time.setText(duty.getTime());
        viewHolder.date.setText(duty.getDate());
        viewHolder.priority.setText(duty.getPriority());
        viewHolder.notification.setText(duty.getNotification());
        viewHolder.floatingActionButton.setVisibility(View.GONE);

        //Change priority color based on duty selection
        switch (duty.getPriority()){
            case "Priority: High":
                viewHolder.priority.setBackgroundColor(ContextCompat.getColor(context, R.color.priorityHigh));
                break;
            case "Priority: Medium":
                viewHolder.priority.setBackgroundColor(ContextCompat.getColor(context, R.color.priorityMedium));
                break;
            case "Priority: Low":
                viewHolder.priority.setBackgroundColor(ContextCompat.getColor(context, R.color.priorityLow));
                break;

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public int getItemCount() {
        return dutyList.size();
    }

    class DutyViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView name;
        TextView time;
        TextView date;
        TextView priority;
        TextView notification;
        FloatingActionButton floatingActionButton;
        View divider;
        ImageButton imageButton;

        DutyViewHolder(View view) {
            super(view);

            //Initialize duty adapter contents
            photo = view.findViewById(R.id.imageView);
            name = view.findViewById(R.id.textViewName);
            time = view.findViewById(R.id.textViewTime);
            date = view.findViewById(R.id.textViewDate);
            priority = view.findViewById(R.id.textViewPriority);
            notification = view.findViewById(R.id.textViewNotification);
            floatingActionButton = view.findViewById(R.id.floatingActionButton);
            divider = view.findViewById(R.id.divider);
            imageButton = view.findViewById(R.id.imageButton);

            //Upon delete button click remove duty information from storage
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPref = context.getSharedPreferences("duty_info", 0);
                    int n = getAdapterPosition();
                    sharedPref.edit().remove("hourInt" + n).apply();
                    sharedPref.edit().remove("minuteInt" + n).apply();
                    sharedPref.edit().remove("priorityString" + n).apply();
                    sharedPref.edit().remove("notificationString" + n).apply();
                    sharedPref.edit().remove("amPmString" + n).apply();
                    sharedPref.edit().remove("nameString" + n).apply();
                    sharedPref.edit().remove("dateString" + n).apply();

                    dutyList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

        }

    }
}