package com.ringov.testandroid.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ringov.testandroid.R;
import com.ringov.testandroid.presenter.LoginPresenter;
import com.ringov.testandroid.view.BaseFragment;
import com.ringov.testandroid.view.SingleFragmentActivity;
import com.ringov.testandroid.view.friends_list.FriendsListActivity;

public class LoginActivity extends SingleFragmentActivity implements LoginView {

    protected LoginFragment fragment;
    protected LoginPresenter presenter;

    @Override
    protected BaseFragment createFragment(){
        fragment = new LoginFragment();
        fragment.setLoginButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(LoginActivity.this);
            }
        });
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        presenter = new LoginPresenter(this);

        if(presenter.isLoggedIn()){
            presenter.loginSuccess();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.loginActivityResult(requestCode,resultCode,data);

    }

    @Override
    public void success() {
        Intent intent = new Intent(this, FriendsListActivity.class);
        startActivity(intent);
        this.finish();
    }
}
