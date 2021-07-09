package com.example.android.tessrecttask1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdk.LauncherSingleton;

public class MainActivity extends AppCompatActivity implements AppContractor.View{
    final String TAG = "Task1";
    private AppDataPresenter presenter;
    private AppDataAdapter appDataAdapter;
    private RecyclerView recyclerView;
    private ArrayList<AppDataModel> app_list;
    private EditText search_edtxt;
    private PackageObserverBroadCast packageObserverBroadCast;
    private PackageChangeListner packageChangeListner;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        presenter = new AppDataPresenter(this, getApplicationContext());
        packageObserverBroadCast = new PackageObserverBroadCast(getApplicationContext());
        packageChangeListner = new PackageObserverBroadCast(getApplicationContext(),packageChangeListner);
        initView();
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclervw);
        search_edtxt = findViewById(R.id.search_edttxt);

        app_list = presenter.getAppList();
        setRecyclerAdapter(app_list);

        search_edtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                appDataAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void setRecyclerAdapter(ArrayList<AppDataModel> appDataModelArrayList) {
        appDataAdapter = new AppDataAdapter(appDataModelArrayList, MainActivity.this);
        linearLayoutManager= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(appDataAdapter);
        appDataAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        Log.i("Task1","onResume() called");
        packageObserverBroadCast.registerPackageChangeListener();
        super.onResume();
    }

    @Override
    protected void onPause() {
        packageObserverBroadCast.unRegisterPackageChangeListener();
        super.onPause();
    }
}