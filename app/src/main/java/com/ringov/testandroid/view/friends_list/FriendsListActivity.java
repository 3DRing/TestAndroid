package com.ringov.testandroid.view.friends_list;

import android.os.Bundle;

import com.ringov.testandroid.R;
import com.ringov.testandroid.presenter.FriendsListPresenter;
import com.ringov.testandroid.view.BaseFragment;
import com.ringov.testandroid.view.SingleFragmentActivity;

public class FriendsListActivity extends SingleFragmentActivity implements FriendsListView{

    private FriendsListPresenter presenter;

    @Override
    protected BaseFragment createFragment(){
        return new FriendsListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        presenter = new FriendsListPresenter(this);
    }
}
