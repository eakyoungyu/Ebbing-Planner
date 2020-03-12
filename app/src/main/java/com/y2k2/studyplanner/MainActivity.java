package com.y2k2.studyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.*;
import com.y2k2.studyplanner.db.AppDatabase;
import com.y2k2.studyplanner.db.EbbingEvent;
import com.y2k2.studyplanner.db.Event;
import com.y2k2.studyplanner.db.EventDao;
import com.y2k2.studyplanner.db.EventEbbingEventJoin;
import com.y2k2.studyplanner.decorators.EventDecorator;
import com.y2k2.studyplanner.view.EbbingEventAdapter;
import com.y2k2.studyplanner.view.EventAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private MaterialCalendarView materialCalendarView;
    private EventAdapter eventAdapter;
    private EbbingEventAdapter ebbingEventAdapter;
    private List<CalendarDay> events = new ArrayList<CalendarDay>();

    private AppDatabase database;
    private EventDao eventDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      connect Room DB

        materialCalendarView = findViewById(R.id.calendarView);
        eventAdapter = new EventAdapter(getApplicationContext());
        ebbingEventAdapter = new EbbingEventAdapter(getApplicationContext());

        createDB();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testDB();
            }
        }).start();

//      config calendar
        materialCalendarView.setTopbarVisible(true);
        materialCalendarView.setLeftArrow(R.color.colorPrimaryDark);
//        materialCalendarView.setTitleFormatter();
        materialCalendarView.state().edit()
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

//        new EventMaker(events).executeOnExecutor(Executors.newSingleThreadExecutor());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                org.threeten.bp.LocalDate localDate = date.getDate();
                System.out.println(CalendarDay.from(localDate.plusDays(1)));
                events.add(date);
//                EventListDialog eventListDialog = new EventListDialog(MainActivity.this, date);
//                eventListDialog.show();
                EventListDialogFragment fragment = EventListDialogFragment.getInstance(date);
                fragment.show(getSupportFragmentManager(), EventListDialogFragment.TAG);
            }
        });

//        new EventMaker(events).executeOnExecutor(Executors.newSingleThreadExecutor());

    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO events 저장
    }

    private class EventMaker extends AsyncTask<Void, Void, List<CalendarDay>> {
        List<CalendarDay> events;
        EventMaker(List<CalendarDay> events){
            this.events = events;
        }
        @Override
        protected List<CalendarDay> doInBackground(Void... voids) {
//            try{
//                Thread.sleep(500);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }


            return events;
        }

        @Override
        protected void onPostExecute(List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if(isFinishing()){
                return;
            }
            // dot color
            materialCalendarView.addDecorator(new EventDecorator(R.color.colorPrimaryDark, calendarDays, MainActivity.this));
        }
    }

    public void createDB(){
        AppDatabase.setDBName("user-name");
        database = AppDatabase.getInstance(getApplicationContext());
        eventDao = database.eventDao();
    }

    public void closeDB(){
        database.destroyInstance();
    }

    public void testDB(){
        System.out.println("DB Testing....");
        List<EventEbbingEventJoin> eventJoinList = new ArrayList<>();
        eventJoinList = eventDao.getAllEbbingEventsJoin();
        ebbingEventAdapter.addEvents(eventJoinList);

        List<Event> eventList = new ArrayList<>();
        eventList = eventDao.getAll();
        eventAdapter.addEvents(eventList);

        List<EbbingEvent> ebbingEvents = new ArrayList<>();
        ebbingEvents = eventDao.getAllEbbingEvents();

        System.out.println("SIZE "+ eventJoinList.size() + " " + eventList.size() + " " + ebbingEvents.size());
        System.out.println(eventJoinList.toString());
        System.out.println(eventList.toString());
        System.out.println(ebbingEvents.toString());
    }

    private void deleteDB(){
        eventDao.deleteAllEvents();
    }
}
