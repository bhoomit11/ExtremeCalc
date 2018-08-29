package com.extremecalculator.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by agile on 3/11/15.
 */
public abstract class BaseActivity extends FragmentActivity {

    public Context mContext;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
    }

    /* To avoid multiple clicks on screen at a same time */
    public boolean isClickEnable() {

        int timeBetweenClick = 600; //in ns
        if (SystemClock.elapsedRealtime() - mLastClickTime < timeBetweenClick)
            return false;
        else {
            mLastClickTime = SystemClock.elapsedRealtime();
            return true;
        }
    }

    /* To Show The SnackBar Message */
    public void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}