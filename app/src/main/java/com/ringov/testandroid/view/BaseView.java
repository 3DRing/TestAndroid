package com.ringov.testandroid.view;

public interface BaseView {
    void showLoading(String loadingMessage);
    void loadingComplete();
    void error(String errorMessage);
    void showMessage(String message);
}
