package com.example.evgenia.ya_tr_ap.presentation_layer.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

/**
 * Created by Evgenia on 02.04.2017.
 */

public class SettingsFrg extends Fragment {
    public final static String TAG = "SettingsFrg";



    public static SettingsFrg newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TITLE, title);

        SettingsFrg settingsFrg = new SettingsFrg();
        settingsFrg.setArguments(bundle);

        return settingsFrg;
    }

    public static SettingsFrg newInstance(int iconRes){
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.KEY_ICON, iconRes);

        SettingsFrg frg = new SettingsFrg();
        frg.setArguments(bundle);

        return frg;
    }
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
        return inflater.inflate(R.layout.fragment_settings, container, false);

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
}
