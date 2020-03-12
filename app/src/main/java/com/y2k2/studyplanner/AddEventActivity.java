package com.y2k2.studyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.y2k2.studyplanner.db.AppDatabase;
import com.y2k2.studyplanner.db.Converters;
import com.y2k2.studyplanner.db.EbbingEvent;
import com.y2k2.studyplanner.db.Event;
import com.y2k2.studyplanner.db.EventDao;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {
    private Button dateButton, cancelButton, okButton;
    private EditText editText;
    private Context mContext;
    private long[] repeatDays = {0, 1, 3, 7, 15, 30};
    private AppDatabase database;
    private EventDao eventDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        mContext = getApplicationContext();

        database = AppDatabase.getInstance(mContext);
        eventDao = database.eventDao();

        dateButton = (Button) findViewById(R.id.add_event_btn_date);
        cancelButton = (Button) findViewById(R.id.add_event_btn_cancel);
        okButton = (Button) findViewById(R.id.add_event_btn_ok);
        editText = (EditText) findViewById(R.id.add_event_edittext_description);


        Intent intent = getIntent();
        final CalendarDay date = intent.getParcelableExtra("date");
        dateButton.setText(date.toString());

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO calendar dialog
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText.getText())){
                    Toast.makeText(mContext, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                }else{
                    final String description = editText.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            addEvent(date, description);
                        }
                    }).start();
                }
                finish();
            }
        });
    }

    public void addEvent(CalendarDay date, String description){
        Event newEvent = new Event(date, description);
        long rowId = eventDao.insertEvent(newEvent);
        int id = eventDao.findIdByRowid(rowId);

        List<EbbingEvent> ebbingEvents = new ArrayList<>();

        for(long add: repeatDays){
            ebbingEvents.add(new EbbingEvent(id, CalendarDay.from(LocalDate.ofEpochDay(add)), R.color.colorAccent, add == 0));
        }
        eventDao.insertEbbingEvents(ebbingEvents);
    }

}
