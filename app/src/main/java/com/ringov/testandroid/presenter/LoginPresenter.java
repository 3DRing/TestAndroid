package com.ringov.testandroid.presenter;

import android.app.Activity;
import android.content.Intent;

import com.ringov.testandroid.model.Login;
import com.ringov.testandroid.view.login.LoginView;

public class LoginPresenter extends BasePresenter {

    private LoginView view;
    private Login loginModel;

    public LoginPresenter(LoginView view){
        this.view = view;
        this.loginModel = new Login(this);
    }

    public boolean isLoggedIn(){
        return loginModel.isLoggedIn();
    }

    public void login(Activity activity) {
        //TODO set loading text correctly
        view.showLoading("Loading");

        if(isLoggedIn()){
            view.success();
        }else{
            loginModel.login(activity);
        }
    }


    public void loginSuccess() {
        view.success();
    }

    public void loginActivityResult(int requestCode, int resultCode, Intent data) {
        loginModel.loginResult(requestCode, resultCode, data);
    }

    public void loginError(String errorMessage) {
        view.error(errorMessage);
    }
}
