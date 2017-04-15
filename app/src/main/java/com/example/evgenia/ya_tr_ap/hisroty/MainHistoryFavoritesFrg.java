package com.example.evgenia.ya_tr_ap.hisroty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.pager_adapter.MainPagerAdapter;
import com.example.evgenia.ya_tr_ap.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Evgenia on 02.04.2017.
 */
public class MainHistoryFavoritesFrg extends Fragment implements ViewPager.OnPageChangeListener{
    public final static String TAG = "MainHistoryFavoritesFrg";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;

    public static MainHistoryFavoritesFrg newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TITLE, title);

        MainHistoryFavoritesFrg mainHistoryFavoritesFrg = new MainHistoryFavoritesFrg();
        mainHistoryFavoritesFrg.setArguments(bundle);

        return mainHistoryFavoritesFrg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        fragments = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_main_history, container, false);
        initViewPager(view);


        tabLayout = (TabLayout) view.findViewById(R.id.vp_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;

    }


    private void initViewPager(View view){
        viewPager = (ViewPager) view.findViewById(R.id.vp_history);
        viewPager.addOnPageChangeListener(this);

        HistoryFavoritesFrg historyFrg = HistoryFavoritesFrg.newInstance(getString(R.string.history_title), HistoryFavoritesFrg.HISTORY);
        HistoryFavoritesFrg favorFrg = HistoryFavoritesFrg.newInstance(getString(R.string.favors_title), HistoryFavoritesFrg.FAVORITES);
        fragments.add(historyFrg);
        fragments.add(favorFrg);

        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);

    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
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

        super.onSaveInstanceState(outState);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Fragment frg = getChildFragmentManager().findFragmentByTag(makeFragmentName(viewPager.getId(), viewPager.getCurrentItem()));
        if(frg.isAdded()){
            frg.onResume();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
