package com.a388hw.yangxiao.notenner.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.a388hw.yangxiao.notenner.R;
import com.a388hw.yangxiao.notenner.main.MainActivity;
import com.a388hw.yangxiao.notenner.user.Event;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link EventDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventDisplayFragment extends Fragment {
    public static final String TAG = "EventDisplayFragment";

    private static final String EVENT_ = "EVENT";

    TextView et, st, title, detail;
    Button editBtn;

    Event event;

    public EventDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventDisplayFragment newInstance(Event event) {
        EventDisplayFragment fragment = new EventDisplayFragment();
        Bundle args = new Bundle();
        args.putParcelable(EVENT_, event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("View Event");
        if (getArguments() != null) {
            event = getArguments().getParcelable(EVENT_);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_event_display, container, false);

        et = root.findViewById(R.id.et);
        title = root.findViewById(R.id.title);
        detail = root.findViewById(R.id.content);
        st = root.findViewById(R.id.st);

        et.setText(event.getEndTime());
        title.setText(event.getTitle());
        detail.setText(event.getDetail());
        st.setText(event.getStartTime());

        editBtn = root.findViewById(R.id.editEvent);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
                ((MainActivity) getActivity()).switchTo(AddItemFragment.TAG, event, true);
            }
        });

        return root;
    }


}
