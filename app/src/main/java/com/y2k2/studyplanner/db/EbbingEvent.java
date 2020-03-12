package com.y2k2.studyplanner.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.y2k2.studyplanner.models.User;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Event.class, parentColumns = "id", childColumns = "eid", onDelete = CASCADE),
        indices = @Index(value = {"eid"}))
public class EbbingEvent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ebid")
    public int ebid;

    @ColumnInfo(name = "eid")
    public int eid;

    @ColumnInfo(name = "add_days")
    public CalendarDay addDays;

    @ColumnInfo(name = "color")
    public int color;

    @ColumnInfo(name = "is_done")
    public boolean isDone;

    public EbbingEvent(int eid, CalendarDay addDays, int color, boolean isDone){
        this.eid = eid;
        this.addDays = addDays;
        this.color = color;
        this.isDone = isDone;
    }

    @NonNull
    @Override
    public String toString() {
        return "ebid: " + ebid + " add_days: " + addDays.toString() + " color: " + color + " isDone: " + isDone;
    }
}
