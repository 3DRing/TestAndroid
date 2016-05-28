package com.ringov.testandroid.model;

import com.ringov.testandroid.presenter.FriendsListPresenter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKParser;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiFriends;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetFriendsList {

    private FriendsListPresenter presenter;
    private List<User> friends;

    public GetFriendsList(FriendsListPresenter presenter){
        this.presenter = presenter;
    }

    public void getFriendsList(){
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"id,first_name,last_name,online"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKUsersArray usersArray = (VKUsersArray) response.parsedModel;
                friends = new ArrayList<User>();

                for (VKApiUserFull vkUser : usersArray){
                    friends.add(new User(vkUser.id,vkUser.first_name, vkUser.last_name,vkUser.online));
                }

                presenter.showFriends(friends);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);

                // TODO move text to resources
                presenter.showLoading("Загружаем список друзей");
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                presenter.showError(error.errorMessage);
            }
        });
    }

}
