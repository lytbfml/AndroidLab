package com.a388hw.yangxiao.notenner.main.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.a388hw.yangxiao.notenner.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeFragment extends DialogFragment {

    public static final String TAG = "DateTimeFragment";
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Calendar calendar;

    private Button setDate;
    private Button setTime;

    public static DateTimeFragment newInstance(Date date) {
        DateTimeFragment dialogFragment = new DateTimeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATE", date);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_date_time, null);
        builder.setView(view);

        datePicker = view.findViewById(R.id.datePicker1);
        timePicker = view.findViewById(R.id.timePicker1);

        calendar = Calendar.getInstance();

        setDate = view.findViewById(R.id.selDate);
        setTime = view.findViewById(R.id.selTime);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.GONE);
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.GONE);
            }
        });

        // Add action buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                String formatedDate = sdf.format(new Date(year, month, day));

                Log.e(TAG, "" + formatedDate);

                calendar.set(
                        datePicker.getYear(), datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getHour(), timePicker.getMinute());
                calendar.getTime();

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATE", calendar.getTime());
                intent.putExtras(bundle);
                getTargetFragment().onActivityResult(getTargetRequestCode(),
                        Activity.RESULT_OK, intent);
            }
        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DateTimeFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

}
