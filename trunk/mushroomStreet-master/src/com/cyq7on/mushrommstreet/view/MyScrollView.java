package com.cyq7on.mushrommstreet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView{
	/**   
	 * @Title: MyScrollView.java 
	 * @Package com.cyq7on.mushrommstreet.view 
	 * @Description: ���ViewPager��ScrollView�еĻ����������� 
	 * @author cyq7on  
	 * @date 2015-11-13 ����11:50:53 
	 * @version V1.0   
	 */
	
	 // �������뼰����
    private float xDistance, yDistance, xLast, yLast;
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                    
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                    
                if(xDistance > yDistance){
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
