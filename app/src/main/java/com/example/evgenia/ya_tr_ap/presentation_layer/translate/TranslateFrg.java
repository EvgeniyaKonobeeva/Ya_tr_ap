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
public class TranslateFrg extends Fragment implements TranslateContract.ITranslateView, RvDialogAdapter.OnSelectLangListener, RvEnterTextAdapter.RvEnterTextAdapterListener{
    public final static String TAG = "TranslateFrg";

    private TabLayout tabLayout;
    private RecyclerView rvEnterText;
    private RecyclerView rvTranslate;
    private CheckBox bookMark;
    private TextView mainTranslate;
    private RelativeLayout layoutTranslate;
    private TranslatePresenter presenter;


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

        presenter = new TranslatePresenter();
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_traslate, container, false);

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

        initTablayout(view);
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

        presenter.attachView(this);

        return view;

    }

    private void initRecyclerViewText(View root){
        rvEnterText = (RecyclerView)root.findViewById(R.id.rv_et_text);



        MyLL ll = new MyLL(getContext() );
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
                if(swipeDir == ItemTouchHelper.LEFT && adapter.getItemCount()>0){
                    Log.d(TAG, "onSwiped: left" + swipedPosition);
                    adapter.addItem("");
                    // TODO: 17.04.2017 обновить бд - всавить последнюю запись новую и получить список новый
                    adapter.removeItem(swipedPosition);
                }else if(swipeDir == ItemTouchHelper.RIGHT && adapter.getItemCount()>0){
                    Log.d(TAG, "onSwiped: RIGHT" + swipedPosition);
                    if(adapter.getItemCount() <= 1 ) {
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
        rvTranslate = (RecyclerView)root.findViewById(R.id.rv_translate);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rvTranslate.getContext(), LinearLayoutManager.VERTICAL,false);
        rvTranslate.setLayoutManager(layoutManager);
    }


    /**
     * сетитть табы из разметки или из кода?
     * пока из разметки - меньше кода, лучше читается*/
    private void initTablayout(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_language);

        String textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_FULL);
        String translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_FULL);
        renameTabs(textLang, translateLang);
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
        String textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
        String translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);
        Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, textLang);
        Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, translateLang);

        renameTabs(tabLayout.getTabAt(2).getText().toString(), tabLayout.getTabAt(0).getText().toString());

        // TODO: 23.04.2017 запрос на перевод

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
        presenter.detachView();

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

        if(text != null && text.length() > 0){
            tabLayout.getTabAt(0).setText(text);
        }

        if(translate != null && translate.length() > 0){
            tabLayout.getTabAt(2).setText(translate);
        }


    }


    @Override
    public void languageSelected(String code, String lang) {
        Log.d(TAG, "languageSelected: ");

        if(getActivity() != null){

            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);


            if(fragment != null && fragment instanceof SelectLangDialog
                    && fragment.getArguments()!= null && fragment.isVisible()){



                int type = fragment.getArguments().getInt(Utils.KEY_TYPE);
                changeLanguagePreferences(code, lang, type);

                ((SelectLangDialog)fragment).dismiss();
            }

        }

    }

    private void changeLanguagePreferences(String newLang, String langFull, int type){

        String textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
        String translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);

        if(type == SelectLangDialog.TEXT_LANGUAGE && translateLang.equals(newLang)){

            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, textLang);
            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, newLang);

            renameTabs(tabLayout.getTabAt(2).getText().toString(), tabLayout.getTabAt(0).getText().toString());

        }else if(type == SelectLangDialog.TRANSLATE_LANGUAGE && textLang.equals(newLang)){

            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, translateLang);
            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, newLang);

            renameTabs(tabLayout.getTabAt(2).getText().toString(), tabLayout.getTabAt(0).getText().toString());


        }else if(type == SelectLangDialog.TEXT_LANGUAGE){

            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, newLang);
            renameTabs(langFull, "");

        }else if(type == SelectLangDialog.TRANSLATE_LANGUAGE){

            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, newLang);
            renameTabs("", langFull);

        }

    }




    @Override
    public void onClickTranslate(String textToTranslate) {
        Log.d(TAG, "onClickTranslate: ");
    }
}
