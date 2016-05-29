package com.ringov.testandroid.view.friends_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ringov.testandroid.R;
import com.ringov.testandroid.model.User;
import com.ringov.testandroid.view.BaseFragment;

import java.util.List;

public class FriendsListFragment extends BaseFragment {

    private RecyclerView list;
    private FriendsListAdapter adapter;

    private View.OnClickListener logoutButtonClickListener;
    private Button logoutButton;
    private View.OnClickListener clearCacheListener;
    private Button clearCacheButton;

    private List<User> tempFriends;
    private boolean modeOnline;
    private boolean cacheIsEmpty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v;
        if(modeOnline){
            v = inflater.inflate(R.layout.fragment_list_friends_online, parent, false);

            logoutButton = (Button) v.findViewById(R.id.logout_button);
            logoutButton.setOnClickListener(logoutButtonClickListener);
        }else{
            v = inflater.inflate(R.layout.fragment_list_friends_offline,parent,false);
        }

        clearCacheButton = (Button) v.findViewById(R.id.clear_cache_button);
        clearCacheButton.setOnClickListener(clearCacheListener);
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

    public void setLogoutButtonClickListener(View.OnClickListener listener) {
        this.logoutButtonClickListener = listener;
    }

    public void setClearCacheButtonListener(View.OnClickListener listener){
        this.clearCacheListener = listener;
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
}
