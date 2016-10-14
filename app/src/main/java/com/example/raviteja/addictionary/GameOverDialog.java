package com.example.raviteja.addictionary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameOverDialog extends DialogFragment {

    private String titleText, messageText;

    public void setTitle(String titleText) {
        this.titleText = titleText;
    }

    public void setMessage(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titleText);
        builder.setMessage(messageText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }
}
