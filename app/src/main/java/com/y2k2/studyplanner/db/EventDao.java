package com.y2k2.studyplanner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Query("SELECT *, (Event.date + EbbingEvent.add_days) AS added_date FROM Event, EbbingEvent WHERE Event.id = EbbingEvent.eid")
    List<EventEbbingEventJoin> getAllEbbingEventsJoin();

    @Query("SELECT * FROM EbbingEvent")
    List<EbbingEvent> getAllEbbingEvents();

    @Query("SELECT * FROM event WHERE id IN (:eventIds)")
    List<Event> loadAllByIds(int[] eventIds);

    @Query("SELECT * FROM event WHERE date = :findDate")
    List<Event> findEventByDate(CalendarDay findDate);

    @Query("SELECT *, (Event.date + EbbingEvent.add_days) AS added_date FROM Event, EbbingEvent WHERE Event.id = EbbingEvent.eid AND added_date = :findDate")
    List<EventEbbingEventJoin> findEbbingEventByDate(CalendarDay findDate);

    @Query("DELETE FROM event")
    void deleteAllEvents();

    @Query("DELETE FROM ebbingevent")
    void deleteAllEbbingEvents();

    @Query("SELECT id FROM event WHERE rowid = :rowId")
    int findIdByRowid(long rowId);

    @Insert
    long insertEvent(Event event);

    @Insert
    List<Long> insertEvents(List<Event> events);

    @Update
    void updateEvents(Event... events);

    @Delete
    void deleteEvents(Event events);

    @Insert
    void insertEbbingEvent(EbbingEvent ebbingEvent);

    @Insert
    void insertEbbingEvents(List<EbbingEvent> ebbingEvents);

    @Update
    void updateEbbingEvents(EbbingEvent... ebbingEvents);

    @Delete
    void deleteEbbingEvents(EbbingEvent... ebbingEvents);

}
