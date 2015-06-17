package com.quandongli.calender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;


public class CalendarView extends ListView{
	
	private CalendarAdapter mAdapter;
	
	private CalendarDateSelectManager mManager;

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CalendarView(Context context) {
		super(context);
	}
	
	public void init(CalendarDateSelectManager.DurationSelectListener listener) {
		mManager = new CalendarDateSelectManager(listener);
		if(mAdapter == null) {
			mAdapter = new CalendarAdapter(getContext());
			setAdapter(mAdapter);
		}
		
	}
	
	
	private class CalendarAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private List<Date> mMonthList = new ArrayList<Date>();
		private int mCount;
		private Calendar mCalendar;
		
		public CalendarAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mCount = 100;
			mCalendar = Calendar.getInstance();
			mCalendar.add(Calendar.MONTH, 0-mCount+1);
			mCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
			for(int i = 0; i < mCount; i++) {				
				mMonthList.add(mCalendar.getTime());
				mCalendar.add(Calendar.MONTH, 1);
			}
		}

		@Override
		public int getCount() {
			return mCount;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MonthLayoutHolder holder;
			if(convertView == null) {
				convertView = mInflater.inflate(R.layout.month_layout, null);
				holder = new MonthLayoutHolder((ViewGroup)convertView, mCalendar);
				convertView.setTag(holder);
			} else {
				holder = (MonthLayoutHolder)convertView.getTag();
			}
			holder.setSelectManager(mManager);
			holder.updateView(mMonthList.get(position));
			
			return convertView;
		}		
	}
}
