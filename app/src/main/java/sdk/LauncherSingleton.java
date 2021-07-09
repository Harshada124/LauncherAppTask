package sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.android.tessrecttask1.AppDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LauncherSingleton {
    // Create the instance
    private static LauncherSingleton instance;
    private ArrayList<AppDataModel> app_list = new ArrayList<>();
    public static LauncherSingleton getInstance()
    {
        if (instance== null) {
            synchronized(LauncherSingleton.class) {
                if (instance == null)
                    instance = new LauncherSingleton();
            }
        }
        // Return the instance
        return instance;
    }

    private LauncherSingleton()
    {
    }

    public ArrayList<AppDataModel> getInstalledAppList(Context appContext)
    {
        PackageManager packageManager = appContext.getPackageManager();

        List<PackageInfo> packList = packageManager.getInstalledPackages(0);

        Log.i("Task1", "packlist in  size is : " + packList.size());
        for (int i=0; i < packList.size(); i++)
        {
            PackageInfo packInfo = packList.get(i);
            if (  (packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
            {
                String appName = packInfo.applicationInfo.loadLabel(packageManager).toString();
                Log.i("Task1","Application name : "+appName);
                Log.i("Task1","Application ICON : "+packInfo.applicationInfo.loadIcon(packageManager));

                AppDataModel dataModel = new AppDataModel();
                dataModel.setApp_name(appName);
                dataModel.setPackage_name(packInfo.packageName);
                Drawable drawable = packInfo.applicationInfo.loadIcon(appContext.getPackageManager());

               //int icon = packInfo.applicationInfo.icon;

                dataModel.setApp_icon(drawable);
                dataModel.setVersion_code(String.valueOf(packInfo.versionCode));
                dataModel.setVersion_name(packInfo.versionName);
                //packageManager.getLaunchIntentForPackage(packInfo.packageName);
                app_list.add(dataModel);

            }
        }

        Log.i("Task1","size of app_list : "+app_list.size());

        for(int i=0;i<app_list.size();i++){
            AppDataModel app = app_list.get(i);
            Log.i("Task1","Inside LauncherSingleton AppDataModel ICON : "+app.getApp_icon());

        }
        return app_list;
    }

}
