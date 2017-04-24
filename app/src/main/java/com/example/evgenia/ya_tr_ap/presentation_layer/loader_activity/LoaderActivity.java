package com.example.evgenia.ya_tr_ap.presentation_layer.loader_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.presentation_layer.MainActivity;
import com.example.evgenia.ya_tr_ap.presentation_layer.preferences.Preferences;

/**
 * Created by User on 20.04.2017.
 */

public class LoaderActivity extends AppCompatActivity implements LoaderContract.ILoaderView{

    private static final String TAG = "LoaderActivity";
    private LoaderPresenter presenter;
    private LinearLayout errorLayout;
    private TextView repeat;
    private ProgressBar progressBar;
    private boolean isFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        presenter = new LoaderPresenter();

         errorLayout = (LinearLayout)findViewById(R.id.error_layout);
         repeat = (TextView)findViewById(R.id.error_repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToStart();
            }
        });
         progressBar = (ProgressBar)findViewById(R.id.progress);


        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");

        presenter.attachView(this);
        tryToStart();



        super.onStart();
    }

    private void tryToStart(){
        isFirst = (boolean)Preferences.getPreference(Preferences.EnumKeys.FIRST_LAUNCH);
        if(!isFirst) {
            Log.d(TAG, "tryToStart: first="+isFirst);
            if (presenter.hasConnection(this)) {
                errorLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                presenter.loadLanguagesToDb();
            } else {
                progressBar.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
            }
        }else {
            presenter.loadLanguagesToDb();
        }
    }


    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void openMainActivity() {
        Log.d(TAG, "openMainActivity: ");
        if(!isFirst)
            Preferences.putPreference(Preferences.EnumKeys.FIRST_LAUNCH, true);
        setUpSelectedLangs();

        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainActivity);
    }

    private void setUpSelectedLangs(){
        String textLang = (String) Preferences.getPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE);
        String translateLang = (String) Preferences.getPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE);

        if(textLang == null || textLang.length() == 0 || translateLang == null || translateLang.length() == 0){
            Log.d(TAG, "onCreate: change langs");
            Preferences.putPreference(Preferences.EnumKeys.ENTER_TEXT_LANG_CODE, "ru");
            Preferences.putPreference(Preferences.EnumKeys.TRANSLATE_TEXT_LANG_CODE, "en");
        }
    }

}
