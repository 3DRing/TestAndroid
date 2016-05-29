package com.ringov.testandroid.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.ringov.testandroid.presenter.FriendsListPresenter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONException;
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
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"id,first_name,last_name"));

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                //save friends list as json string
                saveFriendsListJson(response.responseString);

                VKUsersArray usersArray = (VKUsersArray) response.parsedModel;
                List<User> friends = parseUsersFromVkUsers(usersArray);

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

    private List<User> parseUsersFromVkUsers(VKUsersArray vkUsersArray){
        friends = new ArrayList<User>();

        for (VKApiUserFull vkUser : vkUsersArray){
            friends.add(new User(vkUser.id,vkUser.first_name, vkUser.last_name));
        }
        return friends;
    }

    private final String TEST_ANDROID_PREF = "com.ringov.testandroid.pref";
    private final String SAVE_FRIENDS_LIST = "com.ringov.testandroid.friendslist";

    public void saveFriendsListJson(String jsonString){
        SharedPreferences sPref = CurrentContext.getCrtActivity().getSharedPreferences(TEST_ANDROID_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVE_FRIENDS_LIST,jsonString);
        editor.commit();
    }

    public String loadFriendsListJson(){
        SharedPreferences sPref = CurrentContext.getCrtActivity().getSharedPreferences(TEST_ANDROID_PREF,Context.MODE_PRIVATE);
        String jsonString = sPref.getString(SAVE_FRIENDS_LIST, "");
        return jsonString;
    }

    public void loadFriendsListFromCache() {
        JSONObject jObject = null;
        String jString = loadFriendsListJson();
        if(jString.equals("")){
            // TODO move hardcode text outside
            presenter.showMessage("Кэш пуст");
            presenter.showFriends(null);
            return;
        }
        try {
            jObject = new JSONObject(jString);
        } catch (JSONException e) {
           presenter.showError(e.getMessage());
            return;
        }
        if(jObject == null){
            presenter.showError((new NullPointerException("Couldn't parse saved list of friends")).getMessage());
            return;
        }

        VKUsersArray usersArray = new VKUsersArray();
        try {
            usersArray.parse(jObject);
        } catch (JSONException e) {
            presenter.showError(e.getMessage());
            return;
        }

        friends = parseUsersFromVkUsers(usersArray);

        presenter.showFriends(friends);
    }

    public void clearCache(){
        SharedPreferences sPref = CurrentContext.getCrtActivity().getSharedPreferences(TEST_ANDROID_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVE_FRIENDS_LIST,"");
        editor.commit();
    }
}
