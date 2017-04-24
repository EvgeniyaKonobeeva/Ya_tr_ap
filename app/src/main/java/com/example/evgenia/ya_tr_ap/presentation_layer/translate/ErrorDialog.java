package com.example.evgenia.ya_tr_ap.presentation_layer.translate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;

import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

/**
 * Created by User on 23.04.2017.
 */

public class ErrorDialog extends DialogFragment {
    private static final String KEY_MESSAGE = "key_message";
    private static final String KEY_TITLE = "key_title";
    private static final String TAG = "ErrorDialog";


    public static ErrorDialog newInstance(String message, String title){
        Log.d(TAG, "newInstance: ");
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_TITLE, title);

        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.setArguments(bundle);

        return errorDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = "";
        String title = "";
        if(getArguments() != null){
            message = getArguments().getString(KEY_MESSAGE);
            title = getArguments().getString(KEY_TITLE);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });

        if(title != null && title.length() > 0){
            builder.setTitle(title);
        }

        AlertDialog alert = builder.create();
        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        alert.setCancelable(false);

        return alert;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d(TAG, "onDismiss: ");
        super.onDismiss(dialog);
    }
}
