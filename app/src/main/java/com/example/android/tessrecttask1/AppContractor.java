package com.example.android.tessrecttask1;

import java.util.ArrayList;

public interface AppContractor {

    interface View{
        void initView();
        void setRecyclerAdapter(ArrayList<AppDataModel> appDataModelArrayList);
    }

    interface Presenter{
        ArrayList<AppDataModel> getAppList();
        ArrayList<AppDataModel> searchAppList(ArrayList<AppDataModel> appDataModelArrayList, String appname);
    }
}
