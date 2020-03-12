package com.y2k2.studyplanner.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.y2k2.studyplanner.db.Event;
import com.y2k2.studyplanner.view.EventItemView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventAdapter extends BaseAdapter {
    private ArrayList<Event> events = new ArrayList<>();
    private Context context;

    public EventAdapter(Context context) {
        this.context = context;
    }

    public void addEvents(Event... events){
        this.events.addAll(Arrays.asList(events));
    }

    public void addEvents(List<Event> events){
        this.events.addAll(events);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int i) {
        return events.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EventItemView eventItemView = new EventItemView(context);
        Event event = events.get(i);
        eventItemView.setDescription(event.description);
        eventItemView.setDate(event.date);
        return eventItemView;
    }
}
