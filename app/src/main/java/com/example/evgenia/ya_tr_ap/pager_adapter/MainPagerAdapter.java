package com.example.evgenia.ya_tr_ap.pager_adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Evgenia on 04.04.2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "MainPagerAdapter";



    private ArrayList<Fragment> list;

    public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
        super(fm);
        this.list = fragmentsList;
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
        String title = "";
        if(list.get(position).getArguments()!= null) {
            title = list.get(position).getArguments().getString(Utils.KEY_TITLE);
        }
        return (CharSequence) title;
    }
}

