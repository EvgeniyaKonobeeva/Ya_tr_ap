package com.example.evgenia.ya_tr_ap.pager_adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.hisroty.HistoryFrg;
import com.example.evgenia.ya_tr_ap.settings.SettingsFrg;
import com.example.evgenia.ya_tr_ap.translate.TranslateFrg;

import java.util.ArrayList;

/**
 * Created by Evgenia on 04.04.2017.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MainPagerAdapter";

    private TranslateFrg translateFrg;
    private HistoryFrg historyFrg;
    private SettingsFrg settingsFrg;

    private ArrayList<Fragment> list;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        translateFrg = new TranslateFrg();
        historyFrg = new HistoryFrg();
        settingsFrg = new SettingsFrg();
        list.add(translateFrg);
        list.add(historyFrg);
        list.add(settingsFrg);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Parcelable saveState() {
        Log.d(TAG, "saveState: ");
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        Log.d(TAG, "restoreState: ");
        super.restoreState(state, loader);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "title " + position;
    }
}

