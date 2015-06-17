package com.quandongli.calendarview;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.quandongli.calender.CalendarDateSelectManager;
import com.quandongli.calender.CalendarView;

import java.util.Date;


public class CalendarActivity extends ActionBarActivity {

    public static final int REQUEST_DURATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = (CalendarView)findViewById(R.id.calendar);
        calendarView.init(new CalendarDateSelectManager.DurationSelectListener() {
            @Override
            public void onDurationSelected(Date start, Date end) {
                Intent data = new Intent();
                data.putExtra("start_time", start.getTime());
                data.putExtra("end_time", end.getTime());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
