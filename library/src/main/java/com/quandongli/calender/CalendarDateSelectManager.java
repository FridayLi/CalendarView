package com.quandongli.calender;

import java.util.Date;


public class CalendarDateSelectManager implements DateSelectListener{
	
	public interface DurationSelectListener {
		void onDurationSelected(Date start, Date end);
	}
	
	private Date mStartDate;
	private Date mEndDate;
	private DateItem mStartItem;
	private DurationSelectListener mListener;
	
	public CalendarDateSelectManager(DurationSelectListener listener) {
		mListener = listener;
	}
	
	public boolean isStartItem(DateItem item) {
		if(item.getTag() == null) {
			return false;
		}
		if(mStartDate != null && mStartDate.equals((Date)item.getTag())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEndItem(DateItem item) {
		if(item.getTag() == null) {
			return false;
		}
		if(mEndDate != null && mEndDate.equals((Date)item.getTag())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void onDateSelected(DateItem item) {
		if(mStartDate == null) {
			mStartItem = item;
			mStartDate = (Date)item.getTag();
			item.updateLabel("start");
		} else if(mEndDate == null){
			Date date = (Date)item.getTag();
			mEndDate = date;
			item.updateLabel("end");
			if(mEndDate.before(mStartDate)) {
				mEndDate = mStartDate;
				mStartDate = date;
				if(mStartItem != null) {
					mStartItem.updateLabel("end");
				}	
				item.updateLabel("start");
			}
			mListener.onDurationSelected(mStartDate, mEndDate);
		} else {
			mStartItem = item;
			mStartItem.updateLabel("start");
		}
		
	}
}
