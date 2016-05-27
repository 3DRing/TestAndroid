package com.ringov.testandroid.model;

import android.app.Activity;
import android.content.Intent;

import com.ringov.testandroid.presenter.LoginPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class Login {

    private LoginPresenter presenter;
    private String[] scope;

    public Login(LoginPresenter presenter){
        this.presenter = presenter;

        //hardcode so far
        scope = new String[]{
                VKScope.FRIENDS,
        };
    }



    public void login(Activity activity){
        // TODO vk login
    }

    public boolean isLoggedIn() {
        // TODO vk check
        return true;//VKSdk.isLoggedIn();
    }

    public void loginResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                presenter.loginSuccess();
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                presenter.loginError(error.errorMessage);
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            // TODO understand
            //super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
