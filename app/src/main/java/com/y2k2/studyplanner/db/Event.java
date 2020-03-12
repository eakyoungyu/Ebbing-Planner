package com.y2k2.studyplanner.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.prolificinteractive.materialcalendarview.CalendarDay;

@Entity(indices = @Index(value = {"id"}))
public class Event {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "date")
    public CalendarDay date;

    @ColumnInfo(name = "description")
    public String description;

    public Event(CalendarDay date, String description){
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "id: "+id+" date: "+date.toString()+" description: "+description;
    }
}
