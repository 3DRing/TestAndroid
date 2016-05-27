package com.ringov.testandroid.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ringov.testandroid.R;
import com.ringov.testandroid.presenter.AccessPresenter;
import com.ringov.testandroid.view.BaseFragment;
import com.ringov.testandroid.view.SingleFragmentActivity;
import com.ringov.testandroid.view.friends_list.FriendsListActivity;

public class LoginActivity extends SingleFragmentActivity implements LoginView {

    protected LoginFragment fragment;
    protected AccessPresenter presenter;

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

        /*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        */

        presenter = new AccessPresenter(this);

        if(presenter.isLoggedIn()){
            presenter.loginSuccess();
        }

        //String[] fingerprint = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        //Log.d("Fingerprint", fingerprint[0]);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.loginActivityResult(requestCode,resultCode,data);

    }

    @Override
    public void loadingComplete() {

    }

    @Override
    public void login() {
        Intent intent = new Intent(this, FriendsListActivity.class);
        startActivity(intent);
        this.finish();
    }
    /*
    @Override
    public void superOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
    }*/
}
