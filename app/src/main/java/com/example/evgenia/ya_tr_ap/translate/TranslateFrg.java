package com.example.evgenia.ya_tr_ap.translate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.utils.Utils;

/**
 * Created by Evgenia on 02.04.2017.
 */

public class TranslateFrg extends Fragment{
    public final static String TAG = "TranslateFrg";
    private TabLayout tabLayout;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_traslate, container, false);

        if(savedInstanceState != null) {
            initTablayout(view, savedInstanceState.getInt(Utils.TRANSLATE_SELECTED_TAB, 0));
        }
        else initTablayout(view, 0);

        return view;

    }

    private void initTablayout(View view, int selectedPos){
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_language);
        tabLayout.getTabAt(selectedPos).select();
//        TabLayout.Tab tab1 = tabLayout.newTab().setText("lang1").setTag("t1");
//        TabLayout.Tab tab2 = tabLayout.newTab().setIcon(getResources().getDrawable(R.mipmap.ic_launcher)).setTag("t2");
//        TabLayout.Tab tab3 = tabLayout.newTab().setText("lang2").setTag("t3");
//        tabLayout.addTab(tab1);
//        tabLayout.addTab(tab2);
//        tabLayout.addTab(tab3);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getText() + " " + tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: " + tab.getText());
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putInt(Utils.TRANSLATE_SELECTED_TAB, tabLayout.getSelectedTabPosition());
        super.onSaveInstanceState(outState);

    }
}
