package com.ringov.testandroid.view.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ringov.testandroid.R;
import com.ringov.testandroid.view.BaseFragment;

public class LoginFragment extends BaseFragment {

    private Button loginButton;
    private Button offlineButton;

    private LoginCallBack callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, parent, false);

        loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callLogin();
            }
        });

        offlineButton = (Button) v.findViewById(R.id.offline_button);
        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callLoginOffline();
            }
        });
        return v;
    }

    public void setCallBack(LoginCallBack callBack) {
        this.callback = callBack;
    }
}
