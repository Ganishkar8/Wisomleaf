package com.wisomleaf.ui.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDexApplication;

import com.wisomleaf.ui.customdialog.ApplicationCrashDialog;

public class WisdomLeafApplication extends MultiDexApplication {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Runtime.getRuntime().gc();
    }

    public void handleUncaughtException (Thread thread, Throwable e)
    {
        LogError(e);
    }

    public  void LogError(Throwable e)
    {

        Intent intent = new Intent (WisdomLeafApplication.this, ApplicationCrashDialog.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        startActivity (intent);
        System.exit(1); // kill off the crashed app
    }
}
