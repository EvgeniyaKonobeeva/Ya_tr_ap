package com.example.evgenia.ya_tr_ap.presentation_layer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.MainHistoryFavoritesFrg;
import com.example.evgenia.ya_tr_ap.presentation_layer.pager_adapter.MainPagerAdapter;
import com.example.evgenia.ya_tr_ap.presentation_layer.preferences.Preferences;
import com.example.evgenia.ya_tr_ap.presentation_layer.settings.SettingsFrg;
import com.example.evgenia.ya_tr_ap.presentation_layer.translate.TranslateFrg;
import com.example.evgenia.ya_tr_ap.presentation_layer.ui.CustomViewPager;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        ArrayList<Fragment> list = createFragmentsList();
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setScrollEnable(false);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager, true);
        for(int i = 0; i < tabLayout.getTabCount(); i++){
            int res;
            if(list.get(i).getArguments() != null){
                if((res = list.get(i).getArguments().getInt(Utils.KEY_ICON)) != 0){
                    tabLayout.getTabAt(i).setIcon(res);
                }
            }
        }

    }

    private ArrayList<Fragment> createFragmentsList(){
        ArrayList<Fragment> list = new ArrayList<>();
        TranslateFrg translateFrg =TranslateFrg.newInstance(R.mipmap.language_48);
        MainHistoryFavoritesFrg historyFrg = MainHistoryFavoritesFrg.newInstance(R.mipmap.bookmark_ribbon);
        SettingsFrg settingsFrg = SettingsFrg.newInstance(R.mipmap.settings_48);

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
        super.onSaveInstanceState(outState);
    }

}
