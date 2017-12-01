package com.a388hw.yangxiao.notenner.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a388hw.yangxiao.notenner.R;
import com.a388hw.yangxiao.notenner.user.Event;
import com.a388hw.yangxiao.notenner.util.UserHolder;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Use the {@link EventsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsListFragment extends Fragment {


    public static final String TAG = "EventsListFragment";

    private RecyclerView recyclerView;
    private Button button;
    private EventsAdapter eventsAdapter;

    public static LinkedHashMap<String, Event> eventsData;
    public static ArrayList<Event> eventsDataList;
    private ArrayList<String> nameList;

    DatabaseReference reference;

    private OnListFragmentInteractionListener mListener;

    public EventsListFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsListFragment newInstance(String param1, String param2) {
        EventsListFragment fragment = new EventsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        Context context = view.getContext();

        eventsData = new LinkedHashMap<>();
        eventsDataList = new ArrayList<>();
        nameList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.eventsRecycler);

        reference = ((UserHolder) context).getUser().getDatabaseReference().child(
                Event.PROPERTY_EVENTS);


        eventsAdapter = new EventsAdapter(eventsDataList, mListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(eventsAdapter);

        getData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Your Events");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void getAllChildren() {
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    eventsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getData() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG, dataSnapshot.getKey() + " , " + dataSnapshot.getValue().toString());

                String key = dataSnapshot.getKey();

                if (!eventsData.containsValue(key)) {
                    String title = "";
                    String st = "";
                    String et = "";
                    String ed = "";
                    String loc = "";

                    for (DataSnapshot children : dataSnapshot.getChildren()) {
//                        Log.e(TAG,
//                                "Key: " + children.getKey() + "----Value: " + children.getValue().toString());
                        switch (children.getKey()) {
                            case Event.EVENT_TITLE:
                                title = children.getValue(String.class);
                                break;
                            case Event.START_TIME:
                                st = children.getValue(String.class);
                                break;
                            case Event.END_TIME:
                                et = children.getValue(String.class);
                                break;
                            case Event.EVENT_DETAIL:
                                ed = children.getValue(String.class);
                                break;
                            case Event.EVENT_LOCATION:
                                loc = children.getValue(String.class);
                                break;
                        }
                    }
                    nameList.add(title);

                    Event event = new Event(key, title, st, et, ed, loc);

                    eventsData.put(key, event);
                    eventsDataList.add(event);
                    Log.e(TAG, "Size = " + eventsDataList.size());
                    eventsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e(TAG,
                        "Changing!!! " + dataSnapshot.getKey() + " , " + dataSnapshot.getValue().toString() + " --- s: " + s);

                String key = dataSnapshot.getKey();

                String title = "";
                String st = "";
                String et = "";
                String ed = "";
                String loc = "";

                for (DataSnapshot children : dataSnapshot.getChildren()) {
//                    Log.e(TAG,
//                            "Key: " + children.getKey() + "----Value: " + children.getValue().toString());
                    switch (children.getKey()) {
                        case Event.EVENT_TITLE:
                            title = children.getValue(String.class);
                            break;
                        case Event.START_TIME:
                            st = children.getValue(String.class);
                            break;
                        case Event.END_TIME:
                            et = children.getValue(String.class);
                            break;
                        case Event.EVENT_DETAIL:
                            ed = children.getValue(String.class);
                        case Event.EVENT_LOCATION:
                            loc = children.getValue(String.class);
                            break;
                    }

                    Event event = new Event(key, title, st, et, ed, loc);

                    eventsData.put(key, event);
                    eventsDataList.remove(event);
                    eventsDataList.add(event);
                    Log.e(TAG, "Size = " + eventsDataList.size());

                    eventsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Event e = eventsData.remove(dataSnapshot.getKey());
                eventsDataList.remove(e);
                eventsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getData3() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Event event);
    }

}





