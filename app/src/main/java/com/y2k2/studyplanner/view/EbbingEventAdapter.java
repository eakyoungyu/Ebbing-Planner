package com.y2k2.studyplanner.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.y2k2.studyplanner.db.Event;
import com.y2k2.studyplanner.db.EventEbbingEventJoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EbbingEventAdapter extends BaseAdapter {
    private ArrayList<EventEbbingEventJoin> events = new ArrayList<>();
    private Context context;

    public EbbingEventAdapter(Context context) {
        this.context = context;
    }

    public void addEvents(EventEbbingEventJoin... events){
        this.events.addAll(Arrays.asList(events));
    }

    public void addEvents(List<EventEbbingEventJoin> events){
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
        EbbingEventItemView eventItemView = new EbbingEventItemView(context);
        EventEbbingEventJoin event = events.get(i);
        eventItemView.setDescription(event.event.description);
        eventItemView.setDate(event.addedDate);
        return eventItemView;
    }
}
