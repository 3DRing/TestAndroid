package com.ringov.testandroid.view.friends_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.ringov.testandroid.R;
import com.ringov.testandroid.view.BaseFragment;

public class FriendsListFragment extends BaseFragment {

    ListView list;
    private View.OnClickListener logoutButtonClickListener;
    private Button logoutButton;
    private View.OnClickListener clearCacheListener;
    private Button clearCacheButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_friends, parent, false);

        list = (ListView) v.findViewById(R.id.list_friends);

        logoutButton = (Button) v.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(logoutButtonClickListener);
        clearCacheButton = (Button) v.findViewById(R.id.clear_cache_button);
        clearCacheButton.setOnClickListener(clearCacheListener);
        return v;
    }

    public void setLogoutButtonClickListener(View.OnClickListener listener) {
        this.logoutButtonClickListener = listener;
    }

    public void setClearCacheButtonListener(View.OnClickListener listener){
        this.clearCacheListener = listener;
    }
}
