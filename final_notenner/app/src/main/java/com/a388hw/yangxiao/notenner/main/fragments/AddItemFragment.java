package com.a388hw.yangxiao.notenner.main.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a388hw.yangxiao.notenner.R;
import com.a388hw.yangxiao.notenner.main.MainActivity;
import com.a388hw.yangxiao.notenner.user.Event;
import com.a388hw.yangxiao.notenner.user.User;
import com.a388hw.yangxiao.notenner.util.UserHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddItemFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "AddItemFragment";

    private static final String EVENT_ = "EVENT";


    private static final int EVENT_DATE_START = 202;
    private static final int EVENT_DATE_END = 203;

    private EditText eventTitle;
    private EditText eventDateS;
    private EditText eventDateE;
    private EditText eventDescription;
    private Button saveEvent;
    private Button cancelEvent;

    boolean edit = false;
    Event event = null;

    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddItemFragment.
     */
    public static AddItemFragment newInstance(Event event) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putParcelable(EVENT_, event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Create Event");
        if (getArguments() != null) {
            event = getArguments().getParcelable(EVENT_);
            edit = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        eventTitle = view.findViewById(R.id.eventTitle);
        eventDescription = view.findViewById(R.id.eventDescription);
        eventDateS = view.findViewById(R.id.eventDateS);
        eventDateS.setOnClickListener(this);
        eventDateE = view.findViewById(R.id.eventDateE);
        eventDateE.setOnClickListener(this);
        saveEvent = view.findViewById(R.id.saveEvent);
        saveEvent.setOnClickListener(this);
        cancelEvent = view.findViewById(R.id.cancelEvent);
        cancelEvent.setOnClickListener(this);

        if (event != null) {
            eventDateE.setText(event.getEndTime());
            eventTitle.setText(event.getTitle());
            eventDescription.setText(event.getDetail());
            eventDateS.setText(event.getStartTime());
        }

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.eventDateS:
                DialogFragment newFragment = DateTimeFragment.newInstance(null);
                Fragment f = getActivity().getSupportFragmentManager().findFragmentById(
                        R.id.mainContent);
                newFragment.setTargetFragment(f, EVENT_DATE_START);
                newFragment.show(getFragmentManager(), "DateTimePicker");
                break;
            case R.id.eventDateE:
                DialogFragment newFragmentE = DateTimeFragment.newInstance(null);
                Fragment fE = getActivity().getSupportFragmentManager().findFragmentById(
                        R.id.mainContent);
                newFragmentE.setTargetFragment(fE, EVENT_DATE_END);
                newFragmentE.show(getFragmentManager(), "DateTimePicker");
                break;
            case R.id.saveEvent:
                updateEvent();
                getActivity().onBackPressed();
                break;
            case R.id.cancelEvent:
                getActivity().onBackPressed();
                break;
        }
    }


    private void updateEvent() {
        if (edit) {
            User user = ((UserHolder) getActivity()).getUser();
            DatabaseReference reference = user.getDatabaseReference().child(
                    Event.PROPERTY_EVENTS).child(event.getKey());
            reference.child(Event.EVENT_TITLE).setValue(eventTitle.getText().toString());
            reference.child(Event.START_TIME).setValue(eventDateS.getText().toString());
            reference.child(Event.END_TIME).setValue(eventDateE.getText().toString());
            reference.child(Event.EVENT_DETAIL).setValue(eventDescription.getText().toString());
        } else {
            User user = ((UserHolder) getActivity()).getUser();
            DatabaseReference reference = user.getDatabaseReference();
            reference.child("email").setValue(user.getEmail());
            String key = reference.child("events").push().getKey();
            reference.child("events").child(key).child(Event.EVENT_TITLE).setValue(
                    eventTitle.getText().toString());
            reference.child("events").child(key).child(Event.START_TIME).setValue(
                    eventDateS.getText().toString());
            reference.child("events").child(key).child(Event.END_TIME).setValue(
                    eventDateE.getText().toString());
            reference.child("events").child(key).child(Event.EVENT_DETAIL).setValue(
                    eventDescription.getText().toString());
        }
    }


    private void updateLabel(TextView view, Date date) {
        String myFormat = "MM-dd-yy, HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        view.setText(sdf.format(date));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case EVENT_DATE_START:
                if (resultCode == Activity.RESULT_OK) {
                    // After Ok code.
                    Bundle bundle = data.getExtras();
                    Date date = (Date) bundle.getSerializable("DATE");
                    updateLabel(eventDateS, date);

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // After Cancel code.
                }
                break;
            case EVENT_DATE_END:
                if (resultCode == Activity.RESULT_OK) {
                    // After Ok code.
                    Bundle bundle = data.getExtras();
                    Date date = (Date) bundle.getSerializable("DATE");
                    updateLabel(eventDateE, date);

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // After Cancel code.
                }
                break;
        }
    }
}
