package com.injaz2019.antism.classes.Dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by CY_15 on 21/06/2018.
 */
public class Popup
{
    public static AlertDialog createPopup(Context context, View view, Boolean cancelable)
    {
        AlertDialog.Builder popupAddClassBuilder = new AlertDialog.Builder(context);
        popupAddClassBuilder.setView(view);
        popupAddClassBuilder.setCancelable(cancelable);
        AlertDialog alertDialog = popupAddClassBuilder.create();
        return alertDialog;
    }
}
