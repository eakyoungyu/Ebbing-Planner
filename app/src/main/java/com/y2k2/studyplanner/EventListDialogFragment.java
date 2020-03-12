package com.y2k2.studyplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.y2k2.studyplanner.db.AppDatabase;
import com.y2k2.studyplanner.db.EventDao;
import com.y2k2.studyplanner.db.EventEbbingEventJoin;
import com.y2k2.studyplanner.view.EbbingEventAdapter;

import java.util.List;

public class EventListDialogFragment extends DialogFragment {
    public static final String TAG = "event_list";
    public static CalendarDay date;
    private EventDao eventDao;
    private ListView listView;
    private EbbingEventAdapter eventAdapter;

    public static EventListDialogFragment getInstance(CalendarDay calendarDay){
        date = calendarDay;
        EventListDialogFragment fragment = new EventListDialogFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_event_list_dialog, null);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.event_list_fab_add_event);
        TextView textView = view.findViewById(R.id.event_list_tv_date);
        listView = view.findViewById(R.id.event_list_listview_events);

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
                Intent intent = new Intent(getContext(), AddEventActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                findEvents();
            }
        }).start();
    }

    public void findEvents(){
        eventAdapter = new EbbingEventAdapter(getContext());
        eventDao = AppDatabase.getInstance(getContext()).eventDao();
        List<EventEbbingEventJoin> eventList = eventDao.findEbbingEventByDate(date);
        eventAdapter.addEvents(eventList);
        listView.setAdapter(eventAdapter);
    }
}
