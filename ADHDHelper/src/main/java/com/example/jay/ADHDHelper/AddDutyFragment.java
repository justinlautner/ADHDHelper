package com.example.jay.ADHDHelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AddDutyFragment extends Fragment {

    Spinner hourSpinner;
    ArrayAdapter<CharSequence> adapterHour;
    ArrayAdapter<CharSequence> adapterMinutes;
    ArrayAdapter<CharSequence> adapterAMPM;
    ArrayAdapter<CharSequence> adapterPriority;
    ArrayAdapter<CharSequence> adapterNotifications;
    ArrayAdapter<CharSequence> adapterType;
    Spinner minuteSpinner;
    Spinner amPmSpinner;
    Spinner typeSpinner;
    String item;
    Spinner prioritySpinner;
    Spinner notificationSpinner;
    TextView notificationText;
    EditText nameSubmission;
    EditText dateSubmission;
    Button enterButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_duty_fragment, container, false);

        //Initialize XML buttons
        hourSpinner = view.findViewById(R.id.hourSpinner);
        minuteSpinner = view.findViewById(R.id.minuteSpinner);
        amPmSpinner = view.findViewById(R.id.amPmSpinner);
        prioritySpinner = view.findViewById(R.id.prioritySpinner);
        notificationSpinner = view.findViewById(R.id.notificationSpinner);
        typeSpinner = view.findViewById(R.id.typeSpinner);
        notificationText = view.findViewById(R.id.notifications);
        nameSubmission = view.findViewById(R.id.nameSubmission);
        dateSubmission = view.findViewById(R.id.dateSubmission);
        enterButton = view.findViewById(R.id.enterButton);

        //Initialize adapters for spinner content [bind arrays to spinner]
        adapterHour = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.hour, android.R.layout.simple_spinner_item);
        adapterMinutes = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.minutes, android.R.layout.simple_spinner_item);
        adapterAMPM = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.AMPM, android.R.layout.simple_spinner_item);
        adapterPriority = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.priority, android.R.layout.simple_spinner_item);
        adapterNotifications = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.notifications, android.R.layout.simple_spinner_item);
        adapterType = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.type, android.R.layout.simple_spinner_item);

        //Bind adapter to layout
        adapterHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMinutes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterAMPM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterNotifications.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Bind spinner to adapter
        hourSpinner.setAdapter(adapterHour);
        minuteSpinner.setAdapter(adapterMinutes);
        amPmSpinner.setAdapter(adapterAMPM);
        prioritySpinner.setAdapter(adapterPriority);
        notificationSpinner.setAdapter(adapterNotifications);
        typeSpinner.setAdapter(adapterType);

        //Onitemselected listener for spinners
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                switch(spinner.getId()){
                    case R.id.hourSpinner:
                        item = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.minuteSpinner:
                        item = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.amPmSpinner:
                        item = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.prioritySpinner:
                        item = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.notificationSpinner:
                        item = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.typeSpinner:
                        item = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            //Upon user hitting enter save chosen data to file
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int hourInt = Integer.parseInt(hourSpinner.getSelectedItem().toString());
                        int minuteInt = Integer.parseInt(minuteSpinner.getSelectedItem().toString());
                        String priorityString = prioritySpinner.getSelectedItem().toString();
                        String notificationString = notificationSpinner.getSelectedItem().toString();
                        String amPmString = amPmSpinner.getSelectedItem().toString();
                        String nameString = nameSubmission.getText().toString();
                        String dateString = dateSubmission.getText().toString();
                        String typeString = typeSpinner.getSelectedItem().toString();

                        String empty = new String();
                        String temp = dateSubmission.getText().toString();
                        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
                        Date date = new Date();
                        String timeString;
                        if (minuteInt < 10) {
                            timeString = (hourInt + ":0" + minuteInt + " " + amPmString);
                        }
                        else {
                            timeString = (hourInt + ":" + minuteInt + " " + amPmString);
                        }
                        Date dateTemp = format.parse(temp + " " + timeString);

                        if (dateSubmission.getText().toString().matches("\\d{2}/\\d{2}/\\d{4}") && dateTemp.after(date) && !temp.equals(empty)) {

                            SharedPreferences sharedPref = getContext().getSharedPreferences("duty_info", 0);
                            SharedPreferences.Editor editor = sharedPref.edit();

                            int n = 0;
                            while (!sharedPref.getString("nameString" + n, "Empty").equals("Empty")) {
                                n++;
                            }
                            editor.putString("nameString" + n, nameString);
                            editor.putString("typeString" + n, typeString);
                            editor.putString("timeString" + n, timeString);
                            editor.putString("dateString" + n, dateString);
                            editor.putInt("hourInt", hourInt);
                            editor.putInt("minuteInt", minuteInt);
                            editor.putString("priorityString" + n, priorityString);
                            editor.putString("notificationString" + n, notificationString);
                            editor.commit();

                            getFragmentManager().beginTransaction().replace(R.id.content_frame,
                                    new DutiesFragment()).commit();
                        } else {
                            Toast.makeText(getContext(), "Please fill out all fields, in the requested format, with a future date", Toast.LENGTH_LONG).show();
                        }

                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Please enter a date, in the proper format", Toast.LENGTH_LONG).show();
                    }

                }
            });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getView() == null) {
            return;
        }

        //Return to previous fragment upon back button press
        //TODO ensure return to previous fragment not solely duty fragment
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame,
                            new DutiesFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}
