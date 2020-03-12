package com.y2k2.studyplanner.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.prolificinteractive.materialcalendarview.CalendarDay;

@Entity
public class EventEbbingEventJoin {
    @Embedded
    public Event event;

    @Embedded
    public EbbingEvent ebbingEvent;

    @ColumnInfo(name = "added_date")
    public CalendarDay addedDate;

    @NonNull
    @Override
    public String toString() {
        return event.toString() + " " + ebbingEvent.toString() + " addedDate: " + addedDate;
    }
}
