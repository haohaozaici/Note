package com.example.hao.Note;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by hao on 2016-08-07.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String ARG_DATE = "date";
    public static final String ARG_TIME = "time";
    public static final String EXTRA_DATE = "date";

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private String flag;
    private Calendar calendar = Calendar.getInstance();
    private Date date;
    private View view;

    public static DatePickerFragment newInstance(String flag, Date date) {
        Bundle arg = new Bundle();
        arg.putString("flag", flag);
        if (flag == "date") {
            arg.putSerializable(ARG_DATE, date);
        } else {
            arg.putSerializable(ARG_TIME, date);
        }

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        flag = getArguments().getString("flag");
        if (flag == ARG_DATE) {
            date = (Date) getArguments().getSerializable(ARG_DATE);
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            mDatePicker = (DatePicker) view.findViewById(R.id.date_picker);
            mDatePicker.init(year, month, day, null);

            return new AlertDialog.Builder(getActivity())
                    .setView(view)
//                    .setTitle(R.string.date_picker_title)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int year = mDatePicker.getYear();
                            int month = mDatePicker.getMonth();
                            int day = mDatePicker.getDayOfMonth();
                            date.setYear(year-1900);
                            date.setMonth(month);
                            date.setDate(day);
                            sendResult(Activity.RESULT_OK, date);
                        }
                    })
                    .create();

        } else {
            date = (Date) getArguments().getSerializable(ARG_TIME);
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

            calendar.setTime(date);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            mTimePicker = (TimePicker) view.findViewById(R.id.time_picker);
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
            mTimePicker.setIs24HourView(true);

            return new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .setTitle(R.string.time_picker_title)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int hour = mTimePicker.getHour();
                            int minute = mTimePicker.getMinute();
                            date.setHours(hour);
                            date.setMinutes(minute);
                            sendResult(Activity.RESULT_OK, date);
                        }
                    })
                    .create();
        }


    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();

        intent.putExtra(EXTRA_DATE, date);
        intent.putExtra("flag", flag);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
