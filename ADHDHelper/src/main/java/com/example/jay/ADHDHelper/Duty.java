package com.example.jay.ADHDHelper;


public class Duty {
    private String name;
    private String type;
    private String time;
    private String date;
    private String priority;
    private String notification;
    private int image;

    public Duty(String name, String type, String time, String date, String priority, String notification, int image) {
        this.name = name;
        this.type = type;
        this.time = time;
        this.date = date;
        this.priority = priority;
        this.notification = notification;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getPriority() {
        return priority;
    }

    public String getNotification() {
        return notification;
    }

    public int getImage() {
        //TODO refrain from having duty object call its own class
        switch (getType()){
            case "Appointment":
                image = R.drawable.ic_event_available_black_24dp;
                break;
            case "Due Date":
                image = R.drawable.ic_announcement_black_24dp;
                break;
            case "Leisure Activity":
                image = R.drawable.ic_airline_seat_recline_extra_black_24dp;
                break;
            case "Social Event":
                image = R.drawable.ic_cake_black_24dp;
                break;

        }
        //image = R.drawable.ic_event_available_black_24dp;
        return image;
    }
}
