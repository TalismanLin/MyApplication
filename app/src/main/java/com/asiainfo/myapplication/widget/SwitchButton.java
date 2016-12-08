/**
 * Package:com.jialin.widget
 * Author:Zhu JL
 * Date:2016年5月21日
 */
package com.asiainfo.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CompoundButton;

/**
 * @author Zhu JL
 *
 */
public class SwitchButton extends CompoundButton{

	private Context ctx;
	
	private final Paint paint = new Paint();
	private final Path path = new Path();
	
	private int mWidth,mHeight;
	private float sWidth,sHeight;
	private float sLeft,sTop,sRight,sBottom;
	private float sCenterX,sCenterY;
	
	private float sAnim;
	
	private boolean isOn;
	
	private float bRadius,bStrokeWidth;
	private float bWidth;
	private float bLeft,bTop,bRight,bBottom;
	private float sScaleCenterX;
	private float sScale;
	
	/**
	 * @param context
	 * @param attrs
	 */
	public SwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = context;
	}
	
	public SwitchButton(Context context){
		super(context);
		ctx = context;
	}
	
	/* 
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
		sLeft = sTop = 0;
		sRight = mWidth;
		sBottom = mHeight*0.8f;
		sWidth = sRight - sLeft;
		sHeight = sBottom - sTop;
		sCenterX = (sRight + sLeft)/2;
		sCenterY = (sTop + sBottom)/2;
		
		RectF sRectF = new RectF(sLeft,sTop, sRight, sBottom);
		path.arcTo(sRectF, 90, 180);
		sRectF.left = sRight - sBottom;
		sRectF.right = sRight;
		path.arcTo(sRectF, 270, 180);
		path.close();
		
		bLeft = bTop = 0;
		bRight = bBottom = sBottom;
		bWidth = bRight - bLeft;
		final float halfHeihgtOfS = (sBottom - sTop)/2;
		bRadius = halfHeihgtOfS * 0.9f;
		bStrokeWidth = 2 * (halfHeihgtOfS - bRadius);
		
		sScale = 1 - bStrokeWidth/sHeight;
		sScaleCenterX = sWidth - halfHeihgtOfS;
	}
	
	
	/* 
	 * @see android.widget.TextView#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = (int) (width*0.65f);
		setMeasuredDimension(width, height);
	}
	
	/* 
	 * @see android.widget.CompoundButton#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setColor(0xffcccccc);
//		canvas.drawColor(0xffcccccc);
		canvas.drawPath(path, paint);
		sAnim = sAnim - 0.1f > 0 ? sAnim - 0.1f : 0;
		
		final float scale = 0.98f*(isOn ? sAnim : 1 - sAnim);
		canvas.save();
		canvas.scale(scale, scale, sCenterX, sCenterY);
		paint.setColor(0xfffffff);
		canvas.drawPath(path, paint);
		canvas.restore();
		
		paint.reset();
		if(sAnim > 0){
			invalidate();
		}
		canvas.scale(scale, scale, sScaleCenterX, sScaleCenterX);
	}
	
	/* 
	 * @see android.widget.TextView#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			return true;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			sAnim = 1;
			isOn = !isOn;
			invalidate();
			break;
		}
		return super.onTouchEvent(event);
	}
	
	
	
}
