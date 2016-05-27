package com.ringov.testandroid.view;

public interface BaseView {
    void showLoading(String loadingMessage);
    void success();
    void error(String errorMessage);
}
