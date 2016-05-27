package com.ringov.testandroid.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ringov.testandroid.R;

public abstract class SingleFragmentActivity extends AppCompatActivity implements BaseView {

    protected abstract BaseFragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FragmentManager fm = getSupportFragmentManager();

        BaseFragment fragment = (BaseFragment) fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void showLoading(String loadingMessage){

    }

    @Override
    public void error(String errorMessage){
        AlertDialog.Builder errorBuilder = new AlertDialog.Builder(this);
        errorBuilder.setMessage(errorMessage)
                .setCancelable(false)
                .setNegativeButton(R.string.login_error_dialog_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = errorBuilder.create();
        alert.show();
    }
}
