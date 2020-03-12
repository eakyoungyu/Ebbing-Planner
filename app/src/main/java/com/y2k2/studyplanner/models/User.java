package com.y2k2.studyplanner.models;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Long> repeatDays;
//TODO add dayoff, pushAlarm, activeList(?)

    User(){
        repeatDays = new ArrayList<Long>(){
            {
                add(0L); add(1L); add(3L);
                add(6L); add(13L); add(29L);
            }
        };
    }

    public List<Long> getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(List<Long> repeatDays) {
        this.repeatDays = repeatDays;
    }
}
