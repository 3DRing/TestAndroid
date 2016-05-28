package com.ringov.testandroid.view.friends_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ringov.testandroid.R;
import com.ringov.testandroid.model.User;
import com.ringov.testandroid.presenter.AccessPresenter;
import com.ringov.testandroid.presenter.FriendsListPresenter;
import com.ringov.testandroid.view.BaseFragment;
import com.ringov.testandroid.view.SingleFragmentActivity;
import com.ringov.testandroid.view.login.LoginActivity;
import com.ringov.testandroid.view.login.LoginView;

import java.util.List;

public class FriendsListActivity extends SingleFragmentActivity implements FriendsListView, LogoutView {

    private FriendsListPresenter presenter;
    private AccessPresenter logoutPresenter;

    private Button logoutButton;

    @Override
    protected BaseFragment createFragment(){
        FriendsListFragment fragment = new FriendsListFragment();
        fragment.setLogoutButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutPresenter.logout();
            }
        });
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        presenter = new FriendsListPresenter(this);
        logoutPresenter = new AccessPresenter(this);

        presenter.sendFriendsListRequest();
    }

    @Override
    public void loadingComplete() {

    }

    @Override
    public void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void showFriends(List<User> friends) {
        //debug point
        boolean b = false;
    }
}
