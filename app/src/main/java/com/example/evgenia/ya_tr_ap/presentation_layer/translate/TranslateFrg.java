package com.example.evgenia.ya_tr_ap.presentation_layer.translate;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Def;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.LanguagesDialog;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.recyclerview.RvDialogAdapter;
import com.example.evgenia.ya_tr_ap.presentation_layer.preferences.Preferences;
import com.example.evgenia.ya_tr_ap.presentation_layer.translate.recyclerview.RvDictionaryAdapter;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.LanguagesDialog.TEXT_LANGUAGE;
import static com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils.DIALOG_TAG;

/**
 * Created by Evgenia on 02.04.2017.
 */
public class TranslateFrg extends Fragment implements TranslateContract.ITranslateView, RvDialogAdapter.OnSelectLangListener{
    public final static String TAG = "TranslateFrg";
    public final static String ERR_DIALOG_TAG = "err_dialog_tag";
    public final static String SAVE_TRANSLATE = "save_traslate";

    private TabLayout tabLayout;
    private RecyclerView rvTranslate;
    private CheckBox bookMark;
    private TextView mainTranslate;
    private RelativeLayout layoutTranslate;
    private TranslatePresenter presenter;
    private EditText editText;
    private RelativeLayout rlButtons;
    private ImageView ivClear;
    private ImageView ivTranslate;

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
        String translate = "";

        if(savedInstanceState != null){
            if(getActivity() != null) {
                Log.d(TAG, "onCreateView: not null");

                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);
                if(fragment != null && fragment instanceof LanguagesDialog){
                    Log.d(TAG, "find: dialog");
                    ((LanguagesDialog)fragment).setLangListener(this);

                }

                translate = savedInstanceState.getString(SAVE_TRANSLATE);
            }
        }

        initTablayout(view);
        initEnterText(view);
        initRecyclerViewTranslate(view);

        mainTranslate = (TextView)view.findViewById(R.id.tv_main_translate);

        layoutTranslate = (RelativeLayout)view.findViewById(R.id.rl_translate);

        bookMark = (CheckBox)view.findViewById(R.id.chb_mark);
        bookMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(presenter != null && editText.getText().length()>0){
                    String translateFrom = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
                    String translateTo = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);
                    presenter.addToFavor(new HistoryFavorModel(editText.getText().toString(),
                            mainTranslate.getText().toString(),
                            translateFrom+ "-" + translateTo,
                            1, isChecked == true?1:0));
                }
            }
        });

        presenter.attachView(this);

        if(translate != null && translate.length()>0){
            editText.setText(translate);
            onClickTranslate(translate);
        }

        return view;

    }

    private void initEnterText(View root){
        editText = (EditText)root.findViewById(R.id.et_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    rlButtons.setVisibility(View.VISIBLE);
                }else {
                    rlButtons.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rlButtons = (RelativeLayout)root.findViewById(R.id.rl_buttons);

        ivClear = (ImageView)root.findViewById(R.id.iv_clear);
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                mainTranslate.setText("");
                ((RvDictionaryAdapter)rvTranslate.getAdapter()).updateList(new ArrayList<>());
            }
        });

        ivTranslate = (ImageView)root.findViewById(R.id.iv_translate);
        ivTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTranslate(editText.getText().toString());
            }
        });


    }

    private void initRecyclerViewTranslate(View root){
        rvTranslate = (RecyclerView)root.findViewById(R.id.rv_translate);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rvTranslate.getContext(), LinearLayoutManager.VERTICAL,false);
        rvTranslate.setLayoutManager(layoutManager);

        rvTranslate.setAdapter(new RvDictionaryAdapter(new ArrayList<Def>()));


    }

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

            LanguagesDialog dialog = LanguagesDialog.newInstance(getString(R.string.text_language),
                    TEXT_LANGUAGE);
            dialog.setLangListener(this);

            dialog.show(getActivity().getSupportFragmentManager(), DIALOG_TAG);

        }else if (tabPos == 2){

            LanguagesDialog dialog = LanguagesDialog.newInstance(getString(R.string.tarnslate_language),
                    LanguagesDialog.TRANSLATE_LANGUAGE);
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
        presenter = null;

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        if(editText.getText().length()>0)
            outState.putString(SAVE_TRANSLATE , editText.getText().toString());

        super.onSaveInstanceState(outState);

    }

    public void renameTabs(String text, String translate){
        Log.d(TAG, "renameTabs: ");

        if(text != null && text.length() > 0){
            tabLayout.getTabAt(0).setText(text);
        }

        if(translate != null && translate.length() > 0){
            tabLayout.getTabAt(2).setText(translate);
        }

        if(this.isResumed()){

            if(editText != null) {
                onClickTranslate(editText.getText().toString());
            }
        }

    }


    @Override
    public void languageSelected(String code, String lang) {
        Log.d(TAG, "languageSelected: ");

        if(getActivity() != null){

            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);


            if(fragment != null && fragment instanceof LanguagesDialog
                    && fragment.getArguments()!= null && fragment.isVisible()){



                int type = fragment.getArguments().getInt(Utils.KEY_TYPE);
                changeLanguagePreferences(code, lang, type);

                ((LanguagesDialog)fragment).dismiss();
            }

        }

    }

    private void changeLanguagePreferences(String newLang, String langFull, int type){

        String textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
        String translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);

        if(type == LanguagesDialog.TEXT_LANGUAGE && translateLang.equals(newLang)){

            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, textLang);
            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, newLang);

            renameTabs(tabLayout.getTabAt(2).getText().toString(), tabLayout.getTabAt(0).getText().toString());

        }else if(type == LanguagesDialog.TRANSLATE_LANGUAGE && textLang.equals(newLang)){

            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, translateLang);
            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, newLang);

            renameTabs(tabLayout.getTabAt(2).getText().toString(), tabLayout.getTabAt(0).getText().toString());


        }else if(type == LanguagesDialog.TEXT_LANGUAGE){

            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, newLang);
            renameTabs(langFull, "");

        }else if(type == LanguagesDialog.TRANSLATE_LANGUAGE){

            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, newLang);
            renameTabs("", langFull);

        }

    }

    @Override
    public void showMainTranslate(List<String> list) {
        if(list.size() == 1){
            mainTranslate.setText(list.get(0));
        }else {
            for(int i = 0 ; i < list.size()-1; i++){
                mainTranslate.append(list.get(i) + ", ");
            }
            mainTranslate.append(list.get(list.size()-1));
        }

        if(presenter != null){
            String translateFrom = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
            String translateTo = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);
            presenter.addToHistory(new HistoryFavorModel(editText.getText().toString(),
                    mainTranslate.getText().toString(),
                    translateFrom + "-"+ translateTo,
                    1,0));
        }
    }

    @Override
    public void showDictionary(List<Def> list) {
        ((RvDictionaryAdapter)rvTranslate.getAdapter()).updateList(list);
    }

    @Override
    public void showErrorDialog(String msg, String title) {
        if(getActivity() != null){


            Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(ERR_DIALOG_TAG);
            if (prev == null) {
                Log.d(TAG, "showErrorDialog: prev == null");
                DialogFragment errDialog = ErrorDialog.newInstance(msg, title);
                errDialog.setCancelable(false);

                InputMethodManager imm =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainTranslate.getWindowToken(), 0);
                mainTranslate.clearFocus();

                errDialog.show(getActivity().getSupportFragmentManager(), ERR_DIALOG_TAG);

            }else if(!prev.isVisible()){
                Log.d(TAG, "showErrorDialog: isVisible" +prev.isVisible());
            }else {
                Log.d(TAG, "showErrorDialog: prev not null");
            }



        }else Log.d(TAG, "showErrorDialog: activity is null");

    }

    public void onClickTranslate(String textToTranslate) {
        Log.d(TAG, "onClickTranslate: ");

        if(presenter != null && textToTranslate.length()>0 ){
            if(presenter.hasConnection(getContext())){
                String translateFrom = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
                String translateTo = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);
                presenter.translateText(textToTranslate, translateFrom, translateTo);
            }else {
                showErrorDialog(getString(R.string.error_network), getString(R.string.error_network_title));
            }

        }else {
            mainTranslate.setText("");
            ((RvDictionaryAdapter)rvTranslate.getAdapter()).updateList(new ArrayList<>());
            Log.d(TAG, "onClickTranslate: presenter is null");
        }
    }
}
