package com.ringov.testandroid.presenter;

import com.ringov.testandroid.model.GetFriendsList;
import com.ringov.testandroid.model.User;
import com.ringov.testandroid.view.friends_list.FriendsListView;

import java.util.List;

public class FriendsListPresenter extends BasePresenter {

    private FriendsListView view;
    private GetFriendsList model;

    public FriendsListPresenter(FriendsListView view) {
        this.view = view;
        this.model = new GetFriendsList(this);
    }

    public void showLoading(String loadingMessage) {
        view.showLoading(loadingMessage);
    }

    public void showError(String errorMessage) {
        view.error(errorMessage);
    }

    public void showFriends(List<User> friends) {
        view.loadingComplete();
        view.showFriends(friends);
    }

    public void sendFriendsListRequest() {
        model.getFriendsList();
    }
}
