package com.quandongli.calender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class DateItem extends TextView{
	
	private int mWidth;
	private int mHeight;
	private int mLabelX;
	private String mLabel;
	private CalendarDateSelectManager mSelectManager;

	public DateItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public DateItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DateItem(Context context) {
		super(context);	
	}
	
	public void setSelectManager(CalendarDateSelectManager manager) {
		mSelectManager = manager;
	}
	
	public void updateLabel(String label) {
		mLabel = label;
		Rect bounds = new Rect();
		getPaint().getTextBounds(label, 0, label.length(), bounds);
		mLabelX = (int)(mWidth-bounds.width())/2;
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);	
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(isSelected()) {
			canvas.drawText(mLabel, mLabelX, mHeight-20, getPaint());
			
		}
	}

	@Override
	public boolean performClick() {
		setSelected(isSelected() ? false : true);
		mSelectManager.onDateSelected(this);
		return super.performClick();
	}
	
	

}
