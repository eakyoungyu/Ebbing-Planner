package com.y2k2.studyplanner.db;

import androidx.room.TypeConverter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.LocalDate;

import java.util.Calendar;

public class Converters {
    @TypeConverter
    public static CalendarDay longToCalendarDay(Long date){
        return date == null ? null : CalendarDay.from(LocalDate.ofEpochDay(date));
    }

    @TypeConverter
    public static Long calendarDayTolong(CalendarDay date){
        return date == null ? null : date.getDate().toEpochDay();
    }
}
