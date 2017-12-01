package com.a388hw.yangxiao.notenner.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * @author lytbf on 11/30/2017.
 */

public class util {

    public static void showSnackbar(View view, Context context, String str) {
        final Snackbar snackbar = Snackbar.make(view, str, Snackbar.LENGTH_LONG);
        snackbar.setAction("CLOSE", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
                .show();
    }
}
