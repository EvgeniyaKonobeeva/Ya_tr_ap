package com.example.evgenia.ya_tr_ap.translate.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by User on 17.04.2017.
 */
public class MyLL extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public MyLL(final Context context) {
        super(context);
    }

    public MyLL(final Context context, final int orientation,
                                         final boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLL(final Context context, final AttributeSet attrs,
                                         final int defStyleAttr,
                                         final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
//Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}