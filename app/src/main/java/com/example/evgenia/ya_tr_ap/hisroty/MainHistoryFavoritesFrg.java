package com.example.evgenia.ya_tr_ap.hisroty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
// TODO: 13.04.2017 сделать два перезентера одинаковых, что бы при атачить к ним разные сущности одного фрагмента
public class MainHistoryFavoritesFrg extends Fragment implements TabLayout.OnTabSelectedListener{
    public final static String TAG = "MainHistoryFavoritesFrg";
    private TabLayout tabLayout;
    private HistoryFavorsPresenter presenter;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_main_history, container, false);
        initViewPager(view);
        return view;

    }

    private void initViewPager(View view){
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_history);
        tabLayout = (TabLayout) view.findViewById(R.id.vp_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(HistoryFavoritesFrg.newInstance(getString(R.string.history_title), HistoryFavoritesFrg.HISTORY));
        fragments.add(HistoryFavoritesFrg.newInstance(getString(R.string.favors_title), HistoryFavoritesFrg.FAVORITES));

        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

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
        if(presenter != null) presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }


    /*=========== onTabSelectedListener ===========*/

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        /**
         * запрос через презенетер к бд, в зависимости от выбранной табы - разные виды запросов и разные коллбэки*/

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        /**
         * возможно отмена запроса к бд*/
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        /**
         * запрос через презенетер к бд, в зависимости от выбранной табы - разные виды запросов и разные коллбэки*/
    }
}
