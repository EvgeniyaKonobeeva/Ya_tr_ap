package com.example.evgenia.ya_tr_ap.presentation_layer.hisroty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.pager_adapter.MainPagerAdapter;
import com.example.evgenia.ya_tr_ap.presentation_layer.translate.TranslateFrg;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Evgenia on 02.04.2017.
 */
public class MainHistoryFavoritesFrg extends Fragment implements ViewPager.OnPageChangeListener{

    public interface OnTabClickListener{
        void onClearItems();
    }

    public final static String TAG = "MainHistoryFavoritesFrg";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ImageButton btnClear;

    public static MainHistoryFavoritesFrg newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TITLE, title);

        MainHistoryFavoritesFrg mainHistoryFavoritesFrg = new MainHistoryFavoritesFrg();
        mainHistoryFavoritesFrg.setArguments(bundle);

        return mainHistoryFavoritesFrg;
    }

    public static MainHistoryFavoritesFrg newInstance(int iconRes){
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.KEY_ICON, iconRes);

        MainHistoryFavoritesFrg frg = new MainHistoryFavoritesFrg();
        frg.setArguments(bundle);

        return frg;
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
        initTabLayout(view);




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


    private void initTabLayout(View root){
        tabLayout = (TabLayout) root.findViewById(R.id.vp_tabs);
        tabLayout.setupWithViewPager(viewPager);
        btnClear = (ImageButton)root.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frg = getChildFragmentManager().findFragmentByTag(makeFragmentName(viewPager.getId(), viewPager.getCurrentItem()));
                if(frg.isAdded() && frg instanceof OnTabClickListener){
                    Log.d(TAG, "onClick: ");
                    ((OnTabClickListener)frg).onClearItems();
                }
            }
        });
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
            Log.d(TAG, "onPageSelected: ");
            frg.onResume();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
