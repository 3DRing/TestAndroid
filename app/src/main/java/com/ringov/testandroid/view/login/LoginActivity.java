package com.ringov.testandroid.view.login;

import android.content.Intent;
import android.os.Bundle;

import com.ringov.testandroid.presenter.AccessPresenter;
import com.ringov.testandroid.view.BaseFragment;
import com.ringov.testandroid.view.SingleFragmentActivity;
import com.ringov.testandroid.view.friends_list.FriendsListActivity;

public class LoginActivity extends SingleFragmentActivity implements LoginView, LoginCallBack {

    protected LoginFragment fragment;
    protected AccessPresenter presenter;

    @Override
    protected BaseFragment createFragment(){
        fragment = new LoginFragment();
        fragment.setCallBack(this);
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new AccessPresenter(this);

        //String[] fingerprint = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        //Log.d("Fingerprint", fingerprint[0]);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.loginActivityResult(requestCode,resultCode,data);

    }

    @Override
    public void login(boolean onlineMode) {
        Intent intent = new Intent(this, FriendsListActivity.class);
        //TODO move hardcode text in const value
        intent.putExtra("isOnlineMode", onlineMode);

        startActivity(intent);
    }

    @Override
    public void callLogin() {
        presenter.login(LoginActivity.this);
    }

    @Override
    public void callLoginOffline() {
        presenter.loginOffline();
    }
}
