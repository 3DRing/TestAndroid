package com.ringov.testandroid.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.ringov.testandroid.model.Access;
import com.ringov.testandroid.view.friends_list.LogoutView;
import com.ringov.testandroid.view.login.LoginView;

public class AccessPresenter extends BasePresenter {

    private LoginView loginView;
    private LogoutView logoutView;
    private Access access;

    public AccessPresenter(LoginView loginView){
        this.loginView = loginView;
        this.access = new Access(this);
    }

    public AccessPresenter(LogoutView logoutView){
        this.logoutView = logoutView;
        this.access = new Access(this);
    }

    private void checkLoginView(){
        if(loginView == null){
            throw new NullPointerException("LoginView wan't initialized");
        }
    }

    private void checkLogoutView(){
        if(logoutView == null){
            throw new NullPointerException("LogoutView wan't initialized");
        }
    }

    public boolean isLoggedIn(){
        return access.isLoggedIn();
    }

    public void login(Activity activity) {
        checkLoginView();

        //TODO set loading text correctly
        loginView.showLoading("Логинимся..");

        ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(!access.isInternetConnected(connectivityManager.getActiveNetworkInfo())){
            //TODO remove hardcode text
            loginView.showMessage("Интернет недоступен\nЗагружено из кэша");
            access.loginOffline();
            return;
        }
        if(isLoggedIn()){
            loginView.loadingComplete();
            loginView.login(true);
        }else{
            access.login(activity);
        }
    }

    public void logout(){
        access.logout();
    }

    public void loginSuccess(boolean onlineMode) {
        checkLoginView();

        loginView.loadingComplete();
        if(!onlineMode) {
            // TODO remove hardcode text
            loginView.showMessage("Загружено из кэша");
        }
        loginView.login(onlineMode);
    }

    public void loginActivityResult(int requestCode, int resultCode, Intent data) {
        access.loginResult(requestCode, resultCode, data);
    }

    public void loginError(String errorMessage) {
        checkLoginView();

        loginView.loadingComplete();
        //loginView.error(errorMessage);
    }

    public void logoutSuccess() {
        checkLogoutView();

        logoutView.logout();
    }

    public void loginOffline() {
        //TODO set loading text correctly
        loginView.showLoading("Логинимся..");

        access.loginOffline();
    }
}
