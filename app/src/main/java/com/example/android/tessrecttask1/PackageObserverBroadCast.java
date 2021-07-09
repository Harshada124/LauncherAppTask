package com.example.android.tessrecttask1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PackageObserverBroadCast extends BroadcastReceiver implements PackageChangeListner {
    Context appContext;
    PackageChangeListner packageChangeListner;
    ArrayList<AppDataModel> app_list = new ArrayList<>();

    public PackageObserverBroadCast(Context context) {
        this.appContext = context;
    }

    public PackageObserverBroadCast(Context appContext, PackageChangeListner packageChangeListner) {
        this.appContext = appContext;
        this.packageChangeListner = packageChangeListner;
    }


    @Override
    public ArrayList<AppDataModel> packageinstalled(ArrayList<AppDataModel> modelArrayList) {
        PackageManager packageManager = appContext.getPackageManager();

        List<PackageInfo> packList = packageManager.getInstalledPackages(0);

        Log.i("Task1", "packlist in PackageObserverBroadCast size is : " + packList.size());

        for (int i=0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            if ((packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = packInfo.applicationInfo.loadLabel(packageManager).toString();
                Log.i("Task1", "Application name : " + appName);

                AppDataModel dataModel = new AppDataModel();
                dataModel.setApp_name(appName);
                dataModel.setPackage_name(packInfo.packageName);
                Drawable drawable = packInfo.applicationInfo.loadIcon(appContext.getPackageManager());

                dataModel.setApp_icon(drawable);
                dataModel.setVersion_code(String.valueOf(packInfo.versionCode));
                dataModel.setVersion_name(packInfo.versionName);
                //packageManager.getLaunchIntentForPackage(packInfo.packageName);
                app_list.add(dataModel);

                Collections.sort(app_list, new Comparator<AppDataModel>() {
                    public int compare(AppDataModel s1, AppDataModel s2) {
                        return s1.getApp_name().compareToIgnoreCase(s2.getApp_name());
                    }
                });
            }
        }
        return app_list;

    }


    public final void registerPackageChangeListener(){
            appContext = MyApplication.getContext();
            Log.d("Darsh", "registerPackageChangeListener() " +appContext);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_INSTALL");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            appContext.registerReceiver(this,intentFilter);
        }

        public final void unRegisterPackageChangeListener() {
            appContext = MyApplication.getContext();

            Log.d("Darsh", "unRegisterPackageChangeListener() : "+appContext);
            appContext.unregisterReceiver(this);
        }


        @Override
    public void onReceive(Context context, Intent intent) {
            String packages =intent.getData().getSchemeSpecificPart();
            app_list = packageChangeListner.packageinstalled(app_list);
            Log.i("Task1", ""+packages);
            for(int i=0;i<app_list.size();i++){
                String package_name = app_list.get(i).package_name;
            }

            Toast.makeText(context,"App installed ",Toast.LENGTH_SHORT).show();

        /*
            String var4;
            label17: {
                if (intent != null) {
                    Uri var10000 = intent.getData();
                    if (var10000 != null) {
                        var4 = var10000.getEncodedSchemeSpecificPart();
                        break label17;
                    }
                }

                var4 = null;
            }

            String packageName = var4;
           PackageChangeListner var5 = this.listener;
            if (var5 != null) {
                var5.packageinstalled(this.getInstalledApps(context));
            }

            Log.d("Darsh", "onReceive() " + packageName);

            */
    }


}
