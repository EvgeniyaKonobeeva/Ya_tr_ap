package com.example.evgenia.ya_tr_ap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.hisroty.MainHistoryFavoritesFrg;
import com.example.evgenia.ya_tr_ap.pager_adapter.MainPagerAdapter;
import com.example.evgenia.ya_tr_ap.settings.SettingsFrg;
import com.example.evgenia.ya_tr_ap.translate.TranslateFrg;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), createFragmentsList()));
        viewPager.setScrollEnable(false);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager, true);

    }

    private ArrayList<Fragment> createFragmentsList(){
        ArrayList<Fragment> list = new ArrayList<>();
        TranslateFrg translateFrg =TranslateFrg.newInstance("icon 0");
        MainHistoryFavoritesFrg historyFrg = MainHistoryFavoritesFrg.newInstance("icon 1");
        SettingsFrg settingsFrg = SettingsFrg.newInstance("icon 2");

        list.add(translateFrg);
        list.add(historyFrg);
        list.add(settingsFrg);
        return list;
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        Log.d(TAG, "onResumeFragments: ");
        super.onResumeFragments();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged: ");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if(fragment != null){
            if(fragment instanceof MainHistoryFavoritesFrg){
                getSupportFragmentManager().saveFragmentInstanceState(fragment);
            }
        }
        super.onSaveInstanceState(outState);
    }
}
