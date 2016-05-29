package com.ringov.testandroid.view.friends_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ringov.testandroid.R;
import com.ringov.testandroid.model.User;
import com.ringov.testandroid.presenter.AccessPresenter;
import com.ringov.testandroid.presenter.FriendsListPresenter;
import com.ringov.testandroid.view.BaseFragment;
import com.ringov.testandroid.view.SingleFragmentActivity;
import com.ringov.testandroid.view.login.LoginActivity;

import java.util.List;

public class FriendsListActivity extends SingleFragmentActivity implements FriendsListView, LogoutView {

    private FriendsListPresenter presenter;
    private AccessPresenter logoutPresenter;
    private FriendsListFragment fragment;

    @Override
    protected BaseFragment createFragment(){
        fragment = new FriendsListFragment();
        fragment.setLogoutButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutPresenter.logout();
            }
        });
        fragment.setClearCacheButtonListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                presenter.clearCache();
                Toast.makeText(FriendsListActivity.this, getResources().getString(R.string.lear_cache_message),Toast.LENGTH_SHORT).show();
            }
        });
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new FriendsListPresenter(this);
        logoutPresenter = new AccessPresenter(this);

        Intent intent = getIntent();
        //TODO move hardcode text in const value
        boolean onlineMode = intent.getBooleanExtra("isOnlineMode",false);

        fragment.setModeOnline(onlineMode);
        if(onlineMode) {

            presenter.sendFriendsListRequest();
        }else{
            presenter.loadFriendsList();
        }
    }

    @Override
    public void logout() {
        finish();
    }

    @Override
    public void showFriends(List<User> friends) {
        fragment.addAllFriends(friends);
    }

    @Override
    public void close() {
        this.finish();
    }
}
