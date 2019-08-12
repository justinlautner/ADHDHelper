package com.example.jay.ADHDHelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;



public class DutiesFragment extends Fragment {

    String priorityString;
    String notificationString;
    String nameString;
    String dateString;
    String timeString;
    String typeString;
    int minuteInt;
    int hourInt;
    int photoInt;
    RecyclerView recyclerView;
    RelativeLayout dutyRelativeLayout;
    FrameLayout frameLayout;
    DutyAdapter adapter;
    //TODO try stack or alternative list for better efficiency
    private List<Duty> dutyList = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.duties_fragment, container, false);
        setHasOptionsMenu(true);

        //Initialize recyclerview content variables
        dutyRelativeLayout = view.findViewById(R.id.dutyRelativeLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        frameLayout = view.findViewById(R.id.frameLayout);

        //Initialize recyclerview layout
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dutyRelativeLayout.setVisibility(View.GONE);

        //Load data to be displayed on duty cards
        //TODO create save file instead of sharedpreferences for server use
        SharedPreferences sharedPref = getContext().getSharedPreferences("duty_info", 0);
        int n = 0;
        if (!sharedPref.getString("nameString" + n, "Empty").equals("Empty")) {
            do {
                nameString = sharedPref.getString("nameString" + n, "Empty");
                typeString = sharedPref.getString("typeString" + n, "Empty");
                timeString = sharedPref.getString("timeString" + n, "Empty");
                dateString = sharedPref.getString("dateString" + n, "Empty");
                priorityString = ("Priority: " + sharedPref.getString("priorityString" + n, "Empty"));
                notificationString = ("Reminder: " + sharedPref.getString("notificationString" + n, "Empty"));
                dutyList.add(new Duty(nameString, typeString, timeString, dateString, priorityString, notificationString, photoInt));
                n++;
            } while (!sharedPref.getString("nameString" + n, "Empty").equals("Empty"));
        }
        sharedPref.getInt("hourInt", 00);
        sharedPref.getInt("minuteInt", 00);

        //Initialize duty adapter to bind to recyclerview
        adapter = new DutyAdapter(dutyList, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.duties_menu, menu);
        MenuItem dutiesFilter = menu.findItem(R.id.duties_filter);
        inflater.inflate(R.menu.duties_filter_sub_menu, dutiesFilter.getSubMenu());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.duties_filter:
                // Do Fragment menu item stuff here

                return true;
            case R.id.duties_search:

                return true;
            default:
                break;
        }

        return false;
    }

}
