package com.example.evgenia.ya_tr_ap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.hisroty.HistoryFrg;
import com.example.evgenia.ya_tr_ap.settings.SettingsFrg;
import com.example.evgenia.ya_tr_ap.translate.TranslateFrg;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private BottomNavigationView navigation;

    private TranslateFrg translateFrg;
    private HistoryFrg historyFrg;
    private SettingsFrg settingsFrg;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(translateFrg);
                    return true;
                case R.id.navigation_dashboard:
                    showFragment(historyFrg);
                    return true;
                case R.id.navigation_notifications:
                    showFragment(settingsFrg);
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        Fragment currVisibleFrg = getSupportFragmentManager().findFragmentById(R.id.content);

        if( currVisibleFrg == null){
            // если фраменты еще не были созданы, создадим их и добавим в менеджер

            translateFrg = new TranslateFrg();
            historyFrg = new HistoryFrg();
            settingsFrg = new SettingsFrg();

            addAllFragments();
        }else {
            // если фрагменты уже созданы, проинициализируем поля существующими объектами
            translateFrg = (TranslateFrg) getSupportFragmentManager().findFragmentByTag(TranslateFrg.TAG);
            historyFrg = (HistoryFrg) getSupportFragmentManager().findFragmentByTag(HistoryFrg.TAG);
            settingsFrg = (SettingsFrg) getSupportFragmentManager().findFragmentByTag(SettingsFrg.TAG);
        }


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    public void addAllFragments(){
        Log.d(TAG, "addAllFragments: ");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, historyFrg, HistoryFrg.TAG);
        fragmentTransaction.hide(historyFrg);

        fragmentTransaction.add(R.id.content,settingsFrg, SettingsFrg.TAG);
        fragmentTransaction.hide(settingsFrg);

        fragmentTransaction.commit();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, translateFrg, TranslateFrg.TAG);
        fragmentTransaction.commit();


    }

    public void showFragment(Fragment fragmentShow){
        Log.d(TAG, "showFragment: ");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        for(Fragment frg : getSupportFragmentManager().getFragments()){
            if(frg.isVisible()){
                fragmentTransaction.hide(frg);
            }
        }

        fragmentTransaction.show(fragmentShow);
        fragmentTransaction.commit();

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

        if(translateFrg.isVisible()){
            super.onBackPressed();
        }else {
            navigation.setSelectedItemId(R.id.navigation_home);
        }

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
