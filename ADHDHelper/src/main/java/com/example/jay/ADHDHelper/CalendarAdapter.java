package com.example.jay.ADHDHelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {

    private Context mContext;
    int n;

    public CalendarAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mDateIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.getFirstDayOfWeek();
        n = calendar.get(Calendar.DAY_OF_WEEK);
        if (convertView == null){
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(125, 125));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        }
        else {
            imageView = (ImageView) convertView;
        }
        //TODO create switch for values of n, so that if the day of week starts on X it will choose the desired array
        //TODO calendar arrows need feedback of some kind
        imageView.setImageResource(mDateIds[position]);
        return imageView;
    }

    //references to calendar images
    //TODO change photo file type to speed up loading?
      private Integer[] mDateIds = {
            R.drawable.calendar_s, R.drawable.calendar_m,
            R.drawable.calendar_t, R.drawable.calendar_w,
            R.drawable.calendar_t, R.drawable.calendar_f,
            R.drawable.calendar_s, R.drawable.calendar_one,
            R.drawable.calendar_two, R.drawable.calendar_three,
            R.drawable.calendar_four, R.drawable.calendar_five,
            R.drawable.calendar_six, R.drawable.calendar_seven,
            R.drawable.calendar_eight, R.drawable.calendar_nine,
            R.drawable.calendar_ten, R.drawable.calendar_eleven,
            R.drawable.calendar_twelve, R.drawable.calendar_thirteen,
            R.drawable.calendar_fourteen, R.drawable.calendar_fifteen,
            R.drawable.calendar_sixteen, R.drawable.calendar_seventeen,
            R.drawable.calendar_eighteen, R.drawable.calendar_nineteen,
            R.drawable.calendar_twenty, R.drawable.calendar_twentyone,
            R.drawable.calendar_twentytwo, R.drawable.calendar_twentythree,
            R.drawable.calendar_twentyfour, R.drawable.calendar_twentyfive,
            R.drawable.calendar_twentysix, R.drawable.calendar_twentyseven,
            R.drawable.calendar_twentyeight, R.drawable.calendar_twentynine,
            R.drawable.calendar_thirty, R.drawable.calendar_thirtyone,
    };
}
