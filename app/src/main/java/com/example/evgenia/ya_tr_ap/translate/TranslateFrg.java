package com.example.evgenia.ya_tr_ap.translate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.translate.choose_lang_dialogs.SelectLangDialog;
import com.example.evgenia.ya_tr_ap.utils.Utils;

/**
 * Created by Evgenia on 02.04.2017.
 */

public class TranslateFrg extends Fragment{
    public final static String TAG = "TranslateFrg";
    private TabLayout tabLayout;


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
        View view = inflater.inflate(R.layout.fragment_traslate, container, false);

        initTablayout(view);

        return view;

    }


    /**
     * сетитть табы из разметки или из кода?
     * пока из разметки - меньше кода, лучше читается*/
    private void initTablayout(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_language);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: ");
                if (tabLayout.getSelectedTabPosition() == 0){
                    SelectLangDialog dialog = SelectLangDialog.newInstance("языка текста", SelectLangDialog.TEXT_LANGUAGE);
                    dialog.show(getActivity().getSupportFragmentManager(), "dialog");
                }else if (tabLayout.getSelectedTabPosition() == 2){
                    SelectLangDialog dialog = SelectLangDialog.newInstance("языка перевода", SelectLangDialog.TRANSLATE_LANGUAGE);
                    dialog.show(getActivity().getSupportFragmentManager(), "dialog");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: " + tab.getText());
                if (tabLayout.getSelectedTabPosition() == 0){
                    SelectLangDialog dialog = SelectLangDialog.newInstance(getString(R.string.text_language), SelectLangDialog.TEXT_LANGUAGE);
                    dialog.show(getActivity().getSupportFragmentManager(), "dialog");
                }else if (tabLayout.getSelectedTabPosition() == 2){
                    SelectLangDialog dialog = SelectLangDialog.newInstance(getString(R.string.tarnslate_language), SelectLangDialog.TRANSLATE_LANGUAGE);
                    dialog.show(getActivity().getSupportFragmentManager(), "dialog");
                }
            }
        });
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
        outState.putInt(Utils.TRANSLATE_SELECTED_TAB, tabLayout.getSelectedTabPosition());
        super.onSaveInstanceState(outState);

    }
}
