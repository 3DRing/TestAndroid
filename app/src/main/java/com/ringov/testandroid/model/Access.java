package com.ringov.testandroid.model;

import android.app.Activity;
import android.content.Intent;

import com.ringov.testandroid.presenter.AccessPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;

public class Access {

    private AccessPresenter presenter;
    private String[] scope;

    public Access(AccessPresenter presenter){
        this.presenter = presenter;

        //hardcode so far
        scope = new String[]{
                VKScope.FRIENDS,
        };
    }



    public void login(Activity activity){
        VKSdk.login(activity,scope);
    }

    public boolean isLoggedIn() {
        return VKSdk.isLoggedIn();
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
                String message = error.errorReason;

                //TODO move text of error to res
                if(message == ""){
                    message = "Ошибка входа";
                }
                presenter.loginError(message);
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            // TODO understand
            //presenter.superOnActivityResult(requestCode, resultCode, data);
            //super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void logout() {
        VKSdk.logout();
        presenter.logoutSuccess();
    }
}
