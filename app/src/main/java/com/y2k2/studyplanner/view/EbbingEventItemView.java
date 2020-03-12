package com.y2k2.studyplanner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.y2k2.studyplanner.R;


public class EbbingEventItemView extends LinearLayout {
    private TextView textViewDescription;
    private TextView textViewDate;

    public EbbingEventItemView(Context context) {
        super(context);

        init(context);
    }
    public EbbingEventItemView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.event_item, this, true);

        textViewDescription = findViewById(R.id.event_item_tv_description);
        textViewDate = findViewById(R.id.event_item_tv_date);
    }

    public void setDescription(String description){
        textViewDescription.setText(description);
    }

    public void setDate(CalendarDay date){
        textViewDate.setText(date.toString());
    }
}
