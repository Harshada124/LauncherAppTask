package com.example.android.tessrecttask1;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application
{
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext()
    {
        Log.i("getContext()","getContext()");
        return context;
    }
}