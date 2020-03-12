package com.y2k2.studyplanner;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.y2k2.studyplanner.db.AppDatabase;
import com.y2k2.studyplanner.db.EventDao;
import com.y2k2.studyplanner.db.EventEbbingEventJoin;
import com.y2k2.studyplanner.view.EbbingEventAdapter;
import com.y2k2.studyplanner.view.EventAdapter;

import java.util.List;

public class EventListDialog extends Dialog {
    private Context mContext;
    private CalendarDay date;
    private EventDao eventDao;
    private ListView listView;
    private EbbingEventAdapter eventAdapter;

    public EventListDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        this.date = CalendarDay.today();
    }
    public EventListDialog(@NonNull Context context, CalendarDay date) {
        super(context);
        this.mContext = context;
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_dialog);

        FloatingActionButton floatingActionButton = findViewById(R.id.event_list_fab_add_event);
        TextView textView = findViewById(R.id.event_list_tv_date);
        listView = findViewById(R.id.event_list_listview_events);
        eventAdapter = new EbbingEventAdapter(mContext);


        textView.setText(date.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                findEvents();
            }
        }).start();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO schedule
                Intent intent = new Intent(mContext, AddEventActivity.class);
                intent.putExtra("date", date);
                mContext.startActivity(intent);
            }
        });

    }



    public void findEvents(){
        // TODO find by date
        eventDao = AppDatabase.getInstance(mContext).eventDao();
        List<EventEbbingEventJoin> eventList = eventDao.findEbbingEventByDate(date);
        eventAdapter.addEvents(eventList);
        listView.setAdapter(eventAdapter);
    }
}
