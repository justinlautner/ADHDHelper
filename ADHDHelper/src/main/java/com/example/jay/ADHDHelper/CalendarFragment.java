package com.example.jay.ADHDHelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//TODO calendar_fragment XML: center left and right arrow buttons and edit spacing between images in grid
public class CalendarFragment extends Fragment {

    TextView dateTextView;
    GridView calendarGridView;
    ImageButton leftArrowButton, rightArrowButton;
    String dateString, dateMonthString;
    int intCalendarYear;
    ArrayList<String> dates = new ArrayList<>();
    final String January = "January", February = "February", March = "March", April = "April", May = "May", June = "June",
    July = "July", August = "August", September = "September", October = "October", November = "November", December = "December";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);

        //Initialize XML content
        calendarGridView = view.findViewById(R.id.calendarGrid);
        dateTextView = view.findViewById(R.id.dateTextView);
        leftArrowButton = view.findViewById(R.id.leftArrowButton);
        rightArrowButton = view.findViewById(R.id.rightArrowButton);

        //Initialize dates for changing month text
        DateFormat formatMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        Date date = new Date();
        dateMonthString = formatMonth.format(date);
        intCalendarYear = Calendar.getInstance().get(Calendar.YEAR);
        dateTextView.setText(dateMonthString + " " + intCalendarYear);

        //Left and right arrow OnClickListeners for changing months
        leftArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dateMonthString){
                    case January:
                        dateMonthString = December;
                        intCalendarYear--;
                        break;
                    case February:
                        dateMonthString = January;
                        break;
                    case March:
                        dateMonthString = February;
                        break;
                    case April:
                        dateMonthString = March;
                        break;
                    case May:
                        dateMonthString = April;
                        break;
                    case June:
                        dateMonthString = May;
                        break;
                    case July:
                        dateMonthString = June;
                        break;
                    case August:
                        dateMonthString = July;
                        break;
                    case September:
                        dateMonthString = August;
                        break;
                    case October:
                        dateMonthString = September;
                        break;
                    case November:
                        dateMonthString = October;
                        break;
                    case December:
                        dateMonthString = November;
                        break;
                }
                dateTextView.setText(dateMonthString + " " + intCalendarYear);
            }
        });
        rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dateMonthString){
                    case January:
                        dateMonthString = February;
                        break;
                    case February:
                        dateMonthString = March;
                        break;
                    case March:
                        dateMonthString = April;
                        break;
                    case April:
                        dateMonthString = May;
                        break;
                    case May:
                        dateMonthString = June;
                        break;
                    case June:
                        dateMonthString = July;
                        break;
                    case July:
                        dateMonthString = August;
                        break;
                    case August:
                        dateMonthString = September;
                        break;
                    case September:
                        dateMonthString = October;
                        break;
                    case October:
                        dateMonthString = November;
                        break;
                    case November:
                        dateMonthString = December;
                        break;
                    case December:
                        dateMonthString = January;
                        intCalendarYear++;
                        break;
                }
                dateTextView.setText(dateMonthString + " " + intCalendarYear);
            }
        });


        //TODO sync calendar highlight color with duty priority
        //Load dates to highlight
        SharedPreferences sharedPref = getContext().getSharedPreferences("duty_info", 0);
        int n = 0;
        if (!sharedPref.getString("nameString" + n, "Empty").equals("Empty")) {
            do {
                dateString = sharedPref.getString("dateString" + n, "Empty");
                dates.add(dateString);
                n++;
            } while (!sharedPref.getString("nameString" + n, "Empty").equals("Empty"));
        }

        view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        //progressBar = view.findViewById(R.id.loadingPanel);
        //progressBar.setVisibility(View.GONE);

        //TODO create calendar using GridView adapter
        //Create calendar using GridView
        calendarGridView.setAdapter(new CalendarAdapter(getContext()));
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(calendarGridView.getContext(), " " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
