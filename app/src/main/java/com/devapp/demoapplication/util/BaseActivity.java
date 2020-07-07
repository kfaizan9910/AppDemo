package com.devapp.demoapplication.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.devapp.demoapplication.R;
import com.google.android.material.snackbar.Snackbar;


public abstract class BaseActivity extends AppCompatActivity {



    private ProgressDialog progressDialog;
    private Dialog mDialog;


    private Snackbar snackbar;




    public void hideProgress(){

        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
    public void showLoading(){
        hideLoading();
        mDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {

        if (mDialog != null && mDialog.isShowing()){
            mDialog.cancel();
        }
    }

    @Override
    protected void onStop() {
        hideProgress();
        super.onStop();
    }

    protected void showSnackBar(String message) {
        View view = findViewById(android.R.id.content);
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)snackbarView.getLayoutParams();
        params.gravity = Gravity.TOP;
        snackbarView.setLayoutParams(params);
        TextView snackTextView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        snackTextView.setMaxLines(5);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }

}
