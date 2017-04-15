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
import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.DialogModel;
import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.SelectLangDialog;
import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.recyclerview.RvDialogAdapter;
import com.example.evgenia.ya_tr_ap.favorites.FavoritesFrg;
import com.example.evgenia.ya_tr_ap.utils.Utils;

/**
 * Created by Evgenia on 02.04.2017.
 */
// TODO: 14.04.2017 сделать чтою нельзя было выбрать одинаковый язык перевода и набора текста
public class TranslateFrg extends Fragment implements TranslateContract.ITranslateView, RvDialogAdapter.OnSelectLangListener {
    public final static String TAG = "TranslateFrg";
    private TabLayout tabLayout;



    public static TranslateFrg newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TITLE, title);

        TranslateFrg translateFrg = new TranslateFrg();
        translateFrg.setArguments(bundle);

        return translateFrg;
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

                onSelectLang(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: " + tab.getText());

                onSelectLang(tabLayout.getSelectedTabPosition());
            }
        });
    }

    private void onSelectLang(int tabPos){
        Log.d(TAG, "onSelectLang: ");

        if (tabPos == 0){

            SelectLangDialog dialog = SelectLangDialog.newInstance(getString(R.string.text_language),
                    SelectLangDialog.TEXT_LANGUAGE);
            dialog.setLangListener(this);

            dialog.show(getActivity().getSupportFragmentManager(), "dialog");

        }else if (tabPos == 2){

            SelectLangDialog dialog = SelectLangDialog.newInstance(getString(R.string.tarnslate_language),
                    SelectLangDialog.TRANSLATE_LANGUAGE);
            dialog.setLangListener(this);

            dialog.show(getActivity().getSupportFragmentManager(), "dialog");
        }else {
            onSelectArrow();
        }
    }

    private void onSelectArrow(){
        Log.d(TAG, "onSelectArrow: ");
        /**
         * обновляет бд языков
         * посылает запрос на перевод текста (в этом запросе уже и запрос к бд с изменениями табов и запрос на сервер)
         * как в {@link TranslateFrg#languageSelected()}*/

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
    public void showLanguages(TranslateModel translateModel) {
        Log.d(TAG, "showLanguages: ");
        renameTabs(translateModel);
    }

    public void renameTabs(TranslateModel translateModel){
        Log.d(TAG, "renameTabs: ");
        tabLayout.getTabAt(0).setText(translateModel.getTextLang());
        tabLayout.getTabAt(2).setText(translateModel.getTranslateLang());
    }


    @Override
    public void languageSelected() {
        Log.d(TAG, "languageSelected: ");
        /**
         * одна строка - вызов метода презентера
         * послать запрос на сервер для перевода
         * сделать запрос к бд за измененными данными
         * переименовать табы {@link TranslateFrg#showLanguages(com.example.evgenia.ya_tr_ap.translate.TranslateModel)}
         * послать запрос к серверу*/

    }
}
