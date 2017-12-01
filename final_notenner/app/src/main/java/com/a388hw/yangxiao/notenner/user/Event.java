package com.a388hw.yangxiao.notenner.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author lytbf on 12/1/2017.
 */

public class Event implements Parcelable {

    public static final String TAG = "Event";

    public static final String PROPERTY_EVENTS = "events";

    public static final String EVENT_TITLE = "Event_title";
    public static final String START_TIME = "Start_time";
    public static final String END_TIME = "End_time";
    public static final String EVENT_DETAIL = "Event_detail";

    private String title, startTime, endTime, detail, key;

    protected Event(Parcel in) {
        title = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        detail = in.readString();
        key = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return key != null ? key.equals(event.key) : event.key == null;
    }

    public Event(String key, String title, String startTime, String endTime, String detail) {
        this.key = key;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.detail = detail;
    }


    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDetail() {
        return detail;
    }

    public String getKey() {
        return key;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(detail);
        dest.writeString(key);
    }
}
