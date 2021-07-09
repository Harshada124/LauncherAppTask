package com.example.android.tessrecttask1;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sdk.LauncherSingleton;

public class AppDataPresenter implements AppContractor.Presenter {

    private AppContractor.View mView;
    private Context mContext;
    private ArrayList<AppDataModel> app_list = new ArrayList<>();

    AppDataPresenter(AppContractor.View view,Context context){
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public ArrayList<AppDataModel> getAppList() {
        Log.i("Task1","getAppList() called  : in AppDataPresenter ");
        app_list = LauncherSingleton.getInstance().getInstalledAppList(this.mContext);
        Collections.sort(app_list, new Comparator<AppDataModel>(){
            public int compare(AppDataModel s1, AppDataModel s2) {
                return s1.getApp_name().compareToIgnoreCase(s2.getApp_name());
            }
        });

        for (int i=0;i<app_list.size();i++) {
            Log.i("Task1","Application name : "+app_list.get(i).getApp_name());
        }

        return app_list;
    }

    @Override
    public ArrayList<AppDataModel> searchAppList(ArrayList<AppDataModel> appDataModelArrayList, String appname) {
        for(int i=0; i<appDataModelArrayList.size();i++){
            if (appDataModelArrayList.get(i).getApp_name().contains(appname)){

            }
        }
        return appDataModelArrayList;
    }


}
