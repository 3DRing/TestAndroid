package com.ringov.testandroid.model;

import android.app.Activity;
import android.content.Context;

public class CurrentContext {
    private static Context context;
    private static Activity activity;

    public static void setCrtActivity(Activity activity){
        CurrentContext.activity = activity;
    }

    public static Activity getCrtActivity(){
        return activity;
    }
}
