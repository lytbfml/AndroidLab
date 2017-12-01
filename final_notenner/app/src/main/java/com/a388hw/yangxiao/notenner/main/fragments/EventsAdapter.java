package com.a388hw.yangxiao.notenner.main.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a388hw.yangxiao.notenner.R;
import com.a388hw.yangxiao.notenner.user.Event;

import java.util.ArrayList;

/**
 * @author lytbf on 12/1/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

    public static final String TAG = "EventsAdapter";

    private static ArrayList<Event> events;

    private final EventsListFragment.OnListFragmentInteractionListener mListener;

    public EventsAdapter(ArrayList<Event> data, EventsListFragment.OnListFragmentInteractionListener mListener){
        events = data;
        this.mListener = mListener;
        Log.e(TAG, "EventsAdapter " + events.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title = events.get(position).getTitle();
        holder.event = events.get(position);
        Log.e(TAG, holder.title);
        holder.mIdView.setText(events.get(position).getTitle());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction(holder.event);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (events == null) {
            return 0;
        }
        Log.e(TAG, "getItemCount " + events.size());
        return events.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public Event event;
        public String title;

		public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            //			mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
