package com.example.evgenia.ya_tr_ap.presentation_layer.translate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.SelectLangDialog;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.recyclerview.RvDialogAdapter;
import com.example.evgenia.ya_tr_ap.presentation_layer.preferences.Preferences;
import com.example.evgenia.ya_tr_ap.presentation_layer.translate.recyclerview.MyLL;
import com.example.evgenia.ya_tr_ap.presentation_layer.translate.recyclerview.RvEnterTextAdapter;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;

import static com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.SelectLangDialog.TEXT_LANGUAGE;
import static com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils.DIALOG_TAG;

/**
 * Created by Evgenia on 02.04.2017.
 */
// TODO: 14.04.2017 сделать чтою нельзя было выбрать одинаковый язык перевода и набора текста
public class TranslateFrg extends Fragment implements TranslateContract.ITranslateView, RvDialogAdapter.OnSelectLangListener, RvEnterTextAdapter.RvEnterTextAdapterListener{
    public final static String TAG = "TranslateFrg";

    private TabLayout tabLayout;
    private RecyclerView rvEnterText;
    private RecyclerView rvTranslate;
    private CheckBox bookMark;
    private TextView mainTranslate;
    private RelativeLayout layoutTranslate;


    public static TranslateFrg newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(Utils.KEY_TITLE, title);

        TranslateFrg translateFrg = new TranslateFrg();
        translateFrg.setArguments(bundle);

        return translateFrg;
    }

    public static TranslateFrg newInstance(int iconRes){
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.KEY_ICON, iconRes);

        TranslateFrg translateFrg = new TranslateFrg();
        translateFrg.setArguments(bundle);

        return translateFrg;
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

        if(savedInstanceState != null){
            if(getActivity() != null) {
                Log.d(TAG, "onCreateView: not null");

                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);
                if(fragment != null && fragment instanceof SelectLangDialog){
                    Log.d(TAG, "find: dialog");
                    ((SelectLangDialog)fragment).setLangListener(this);

                }
            }
        }
        initRecyclerViewText(view);
        initRecyclerViewTranslate(view);

        mainTranslate = (TextView)view.findViewById(R.id.tv_main_translate);

        layoutTranslate = (RelativeLayout)view.findViewById(R.id.rl_translate);

        bookMark = (CheckBox)view.findViewById(R.id.chb_mark);
        bookMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: 17.04.2017 обновить бд на измненение, обновляем модель ?
            }
        });

        return view;

    }

    private void initRecyclerViewText(View root){
        rvEnterText = (RecyclerView)root.findViewById(R.id.rv_et_text);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


        MyLL ll = new MyLL(getContext(), LinearLayoutManager.VERTICAL, false);
        ll.setScrollEnabled(false);
        rvEnterText.setLayoutManager(ll);

        ArrayList<String> arr = new ArrayList<String>();
        for(int i = 0; i < 20; i++){
            arr.add("string " + i);
        }
        RvEnterTextAdapter adapter = new RvEnterTextAdapter(arr, this);
        rvEnterText.setAdapter(adapter);

        initItemTouchHelper();


    }

    private void initItemTouchHelper(){

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            private static final String TAG = ".SimpleCallback";



            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
//            @Override
//            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//                int position = viewHolder.getAdapterPosition();
//                return super.getSwipeDirs(recyclerView, viewHolder);
//            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int swipedPosition = viewHolder.getAdapterPosition();
                Log.d(TAG, "onSwiped: " + swipedPosition);
                RvEnterTextAdapter adapter = (RvEnterTextAdapter)rvEnterText.getAdapter();
                if(swipeDir == ItemTouchHelper.LEFT ){
                    Log.d(TAG, "onSwiped: left" + swipedPosition);
                    adapter.addItem("");
                    // TODO: 17.04.2017 обновить бд - всавить последнюю запись новую и получить список новый
                    adapter.removeItem(swipedPosition);
                }else if(swipeDir == ItemTouchHelper.RIGHT){
                    Log.d(TAG, "onSwiped: RIGHT" + swipedPosition);
                    if(adapter.getItemCount() <= 1) {
                        adapter.addItem(null);
                    }
                    adapter.removeItem(swipedPosition);
                }
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(rvEnterText);
    }

    private void initRecyclerViewTranslate(View root){
        rvTranslate = (RecyclerView)root.findViewById(R.id.rv_et_text);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rvTranslate.getContext(), LinearLayoutManager.VERTICAL,false);
        rvTranslate.setLayoutManager(layoutManager);
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
                    TEXT_LANGUAGE);
            dialog.setLangListener(this);

            dialog.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);

        }else if (tabPos == 2){

            SelectLangDialog dialog = SelectLangDialog.newInstance(getString(R.string.tarnslate_language),
                    SelectLangDialog.TRANSLATE_LANGUAGE);
            dialog.setLangListener(this);

            dialog.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);
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
    }

    public void renameTabs(String text, String translate){
        Log.d(TAG, "renameTabs: ");
        tabLayout.getTabAt(0).setText(text);
        tabLayout.getTabAt(2).setText(translate);
    }


    @Override
    public void languageSelected(String language) {
        Log.d(TAG, "languageSelected: ");

        if(getActivity() != null){

            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);


            if(fragment != null && fragment instanceof SelectLangDialog
                    && fragment.getArguments()!= null && fragment.isVisible()){



                int type = fragment.getArguments().getInt(Utils.KEY_TYPE);
                changeLanguagePreferences(language, type);


                ((SelectLangDialog)fragment).dismiss();
            }
        }

        // TODO: 17.04.2017 update database
        /**
         * одна строка - вызов метода презентера
         * послать запрос на сервер для перевода
         * сделать запрос к бд за измененными данными
         * переименовать табы {@link TranslateFrg#showLanguages(com.example.evgenia.ya_tr_ap.presentation_layer.translate.TranslateModel)}
         * послать запрос к серверу*/

    }

    private void changeLanguagePreferences(String newLang, int type){

        String textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG);
        String translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG);

        if(type == SelectLangDialog.TEXT_LANGUAGE && translateLang.equals(newLang)){

            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG, textLang);
            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG, newLang);

        }else if(type == SelectLangDialog.TRANSLATE_LANGUAGE && textLang.equals(newLang)){

            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG, translateLang);
            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG, newLang);


        }else if(type == SelectLangDialog.TEXT_LANGUAGE){

            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG, newLang);

        }else if(type == SelectLangDialog.TRANSLATE_LANGUAGE){

            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG, newLang);

        }

        textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG);
        translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG);
        // TODO: 17.04.2017 update database
        renameTabs(textLang, translateLang);

    }




    @Override
    public void onClickTranslate(String textToTranslate) {
        Log.d(TAG, "onClickTranslate: ");
    }
}
