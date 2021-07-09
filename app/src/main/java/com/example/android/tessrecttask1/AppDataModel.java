package com.example.android.tessrecttask1;

import android.graphics.drawable.Drawable;

public class AppDataModel {
    String app_name;
    String package_name;
    Drawable app_icon;
    String version_code;
    String version_name;

    public AppDataModel() {
    }

    public AppDataModel(String app_name, String package_name, Drawable app_con, String version_code, String version_name) {
        this.app_name = app_name;
        this.package_name = package_name;
        this.app_icon = app_icon;
        this.version_code = version_code;
        this.version_name = version_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public Drawable getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(Drawable app_icon) {
        this.app_icon = app_icon;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }
}