package com.example.evgenia.ya_tr_ap.presentation_layer.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Evgenia on 04.04.2017.
 */

public class CustomViewPager extends ViewPager {
    private boolean enableScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollEnable(boolean enable){
        enableScroll = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(enableScroll){
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(enableScroll){
            return super.onTouchEvent(ev);
        }
        return false;
    }
}
