package com.ringov.testandroid.view.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ringov.testandroid.R;
import com.ringov.testandroid.view.BaseFragment;

public class LoginFragment extends BaseFragment {

    private View.OnClickListener loginButtonListener;
    private Button loginButton;

    private View.OnClickListener offlineButtonListener;
    private Button offlineButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, parent, false);

        loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(loginButtonListener);

        offlineButton = (Button) v.findViewById(R.id.offline_button);
        offlineButton.setOnClickListener(offlineButtonListener);
        return v;
    }

    public void setLoginButtonListener(View.OnClickListener listener){
        loginButtonListener = listener;
    }

    public void setOfflineButtonListener(View.OnClickListener listener){
        offlineButtonListener = listener;
    }
}
