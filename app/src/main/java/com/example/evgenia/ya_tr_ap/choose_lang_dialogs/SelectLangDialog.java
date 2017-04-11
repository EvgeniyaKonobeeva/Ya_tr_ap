package com.example.evgenia.ya_tr_ap.choose_lang_dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgenia.ya_tr_ap.R;
import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.recyclerview.RvDialogAdapter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgenia on 09.04.2017.
 */

public class SelectLangDialog extends DialogFragment implements View.OnClickListener, DialogContract.IDialogView{

    /**
     * определим тип для вида диалогов - диалог с языками перевода, или с языками введенного текста
     * в зависимости от типа будут делаться разные запросы с разными результатоми
     * ограничим вид параметра, передаваемого для выбора вида диалога, т.е выбирать сможем только из перечисленных констант*/
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TEXT_LANGUAGE, TRANSLATE_LANGUAGE})
    public @interface DialogType{}
    public static final int TEXT_LANGUAGE = 0;
    public static final int TRANSLATE_LANGUAGE = 1;
    public static @SelectLangDialog.DialogType int getType(int t){
        switch (t){
            case TEXT_LANGUAGE:
                return TEXT_LANGUAGE;
            case TRANSLATE_LANGUAGE:
                return TRANSLATE_LANGUAGE;
            default:
                return TEXT_LANGUAGE;
        }
    }

    private static final String TAG = "SelectLangDialog";
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_DIALOG_TYPE = "key_dialog_type";

    private RvDialogAdapter.OnSelectLangListener langListener;
    private DialogPresenter presenter;
    private RecyclerView recyclerView;
    private RvDialogAdapter adapter;

    public static SelectLangDialog newInstance(String title, @DialogType int type1){
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putInt(KEY_DIALOG_TYPE, type1);

        SelectLangDialog dialog = new SelectLangDialog();
        dialog.setArguments(bundle);

        return dialog;
    }

    public void setLangListener(RvDialogAdapter.OnSelectLangListener listener){
        this.langListener = listener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        presenter = new DialogPresenter();
        presenter.attachView(this);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        super.onCreate(savedInstanceState);


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.dialog_frg_choose_lang, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setTitle(getArguments().getString(KEY_TITLE));

        initRecyclerView(root);


        if(presenter != null){
            // загрузим из БД даные в зависимости от переданного типа диалога (либо языки перевода, либо языки текста)
            presenter.loadLanguages(getType(getArguments().getInt(KEY_DIALOG_TYPE)));
        }

        return root;
    }

    private void initRecyclerView(View root){
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_dialog);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RvDialogAdapter(new ArrayList<DialogModel>());

        if(langListener != null){

            adapter.setListener(langListener);
        }


        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.d(TAG, "onCancel: ");
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d(TAG, "onDismiss: ");
        presenter.detachView();
        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void showItems(List<DialogModel> itemList) {
        if(adapter != null){
            adapter.updateItems(itemList);
        }
    }
}
