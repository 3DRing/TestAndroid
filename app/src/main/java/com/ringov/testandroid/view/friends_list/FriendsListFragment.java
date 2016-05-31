package com.ringov.testandroid.view.friends_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ringov.testandroid.R;
import com.ringov.testandroid.model.User;
import com.ringov.testandroid.view.BaseFragment;

import java.util.List;

public class FriendsListFragment extends BaseFragment {

    private RecyclerView list;
    private FriendsListAdapter adapter;

    private Button logoutButton;
    private Button clearCacheButton;

    private List<User> tempFriends;
    private boolean modeOnline;
    private boolean cacheIsEmpty;

    private FriendsListCallBack callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_friends, parent, false);
        logoutButton = (Button) v.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callLogout();
            }
        });
        logoutButton.setVisibility(modeOnline ? View.VISIBLE : View.GONE);

        clearCacheButton = (Button) v.findViewById(R.id.clear_cache_button);
        clearCacheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                callback.callClearCache();
            }
        });
        if(cacheIsEmpty){
            clearCacheButton.setEnabled(false);
        }

        list = (RecyclerView) v.findViewById(R.id.list_friends_RV);
        list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new FriendsListAdapter();
        list.setAdapter(adapter);

        if(tempFriends != null){        //was loaded in offline in the same thread BEFORE onCreateView() method
            adapter.addAll(tempFriends);
        }

        return v;
    }

    public void addAllFriends(List<User> list){
        if(list == null){
            cacheIsEmpty = true;
            return;
        }
        if(adapter != null) {
            adapter.addAll(list);
        }else{
            tempFriends = list;// offline loading in save thread happens BEFORE initialization
        }
    }

    public void setModeOnline(boolean modeOnline) {
        this.modeOnline = modeOnline;
    }

    public void setCallback(FriendsListCallBack callback) {
        this.callback = callback;
    }
}
