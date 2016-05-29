package com.ringov.testandroid.view.friends_list;

import com.ringov.testandroid.model.User;
import com.ringov.testandroid.view.BaseView;

import java.util.List;

public interface FriendsListView extends BaseView {
    void showFriends(List<User> friends);
    void close();
}
