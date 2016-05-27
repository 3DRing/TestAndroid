package com.ringov.testandroid.presenter;

import com.ringov.testandroid.view.friends_list.FriendsListView;

public class FriendsListPresenter extends BasePresenter {

    private FriendsListView view;

    public FriendsListPresenter(FriendsListView view) {
        this.view = view;
    }
}
