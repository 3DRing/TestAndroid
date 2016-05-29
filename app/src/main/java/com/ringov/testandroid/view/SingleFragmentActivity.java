package com.ringov.testandroid.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ringov.testandroid.R;
import com.ringov.testandroid.model.CurrentContext;

public abstract class SingleFragmentActivity extends AppCompatActivity implements BaseView {

    protected abstract BaseFragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    protected void onResume() {
        super.onResume();
        CurrentContext.setCrtActivity(this);
    }

    private ProgressDialog loadingDialog;

    @Override
    public void showLoading(String loadingMessage){
        if(loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setTitle(R.string.loading_dialog_title);
            loadingDialog.setMessage(loadingMessage);
            loadingDialog.setCancelable(false);
            loadingDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    SingleFragmentActivity.this.finish();
                }
            });
        }
        loadingDialog.show();
    }

    @Override
    public void loadingComplete() {
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
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

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
