package com.example.evgenia.ya_tr_ap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.SelectLangDialog;
import com.example.evgenia.ya_tr_ap.hisroty.MainHistoryFavoritesFrg;
import com.example.evgenia.ya_tr_ap.pager_adapter.MainPagerAdapter;
import com.example.evgenia.ya_tr_ap.preferences.Preferences;
import com.example.evgenia.ya_tr_ap.settings.SettingsFrg;
import com.example.evgenia.ya_tr_ap.translate.TranslateFrg;
import com.example.evgenia.ya_tr_ap.utils.Utils;

import java.util.ArrayList;

import static com.example.evgenia.ya_tr_ap.translate.TranslateFrg.DIALOG_TAG;

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
        Preferences.createPreferences(this);
        Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG, "en");
        Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG, "ru");
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
