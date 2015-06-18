package com.quandongli.calendar;

import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class MonthLayoutHolder {
	
	private Calendar mCalendar;
	private CalendarDateSelectManager mSelectManager;
	private ViewGroup mWeekGroup;
	private TextView mMonthLabel;
	
	public MonthLayoutHolder(ViewGroup monthLayout,Calendar calendar) {
		mMonthLabel = (TextView)monthLayout.findViewById(R.id.month_label);
		mWeekGroup = (ViewGroup)monthLayout.findViewById(R.id.week_list);
		mCalendar = calendar;
	}
	
	public void setSelectManager(CalendarDateSelectManager manager) {
		mSelectManager = manager;
	}
	
	public void updateView(Date date) {
//		Log.v("test", DateFormat.format("yyyy-MM", date).toString());
		
		mCalendar.setTime(date);
		mMonthLabel.setText(DateFormat.format("yyyy MM", date));
		int daysOfMonth = mCalendar.getActualMaximum(Calendar.DATE);
		mCalendar.set(Calendar.DAY_OF_MONTH, 1);
		int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
		
		int weeksOfMonth = mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		mCalendar.add(Calendar.DATE, 1-dayOfWeek);
		for(int i = 0; i < 6; i++) {
			if(i > weeksOfMonth-1) {
				mWeekGroup.getChildAt(i).setVisibility(View.GONE);
			} else {
				ViewGroup weekRow = (ViewGroup)mWeekGroup.getChildAt(i);
				weekRow.setVisibility(View.VISIBLE);
				for(int d = 0; d < 7; d++) {
					int index = 7*i+d;
					DateItem dateCell = ((DateItem)weekRow.getChildAt(d));
					dateCell.setSelectManager(mSelectManager);
					
					if(index >= dayOfWeek-1 && index < dayOfWeek-1 + daysOfMonth) {					
						dateCell.setText(String.valueOf(mCalendar.get(Calendar.DATE)));
						dateCell.setTag(mCalendar.getTime());
					} else {
						dateCell.setText("");
						dateCell.setTag(null);
					}
					if(mSelectManager.isStartItem(dateCell)) {
						dateCell.updateLabel("start");
						dateCell.setSelected(true);
					} else if(mSelectManager.isEndItem(dateCell)) {
						dateCell.updateLabel("end");
						dateCell.setSelected(true);
					} else {
						dateCell.setSelected(false);
					}
					
					mCalendar.add(Calendar.DATE, 1);
				}
			}
		}
	}

}
