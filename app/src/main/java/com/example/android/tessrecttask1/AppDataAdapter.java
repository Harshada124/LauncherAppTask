package com.example.android.tessrecttask1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class AppDataAdapter extends RecyclerView.Adapter<AppDataAdapter.AppViewHolder> implements Filterable {
    private ArrayList<AppDataModel> app_list = new ArrayList<>();
    private Context context;
    private Filter filterApps;

    public AppDataAdapter(ArrayList<AppDataModel> app_list, Context context) {
        this.app_list = app_list;
        this.context = context;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterview, parent, false);
        return new AppDataAdapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        final AppDataModel app = app_list.get(position);
        holder.appname_txtvw.setText(app.getApp_name());
        holder.packgname_txtvw.setText(app.getPackage_name());
        holder.vercode_txtvw.setText(app.getVersion_code());
        holder.vername_txtvw.setText(app.getVersion_name());

        Log.i("Task1", " Inside Adapter app.getApp_Icon() : "+app.getApp_icon());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.error(R.drawable.ic_launcher_foreground);

        Glide.with(context).setDefaultRequestOptions(requestOptions).load(app.getApp_icon()).into(holder.appicon_imgvw);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(app.getPackage_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return app_list.size();
    }

    @Override
    public Filter getFilter() {
        Log.i("Task1","getFilter called ");

        if(filterApps == null) {
            filterApps=new AppFilter();
        }
        return filterApps;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView appname_txtvw, packgname_txtvw, vercode_txtvw, vername_txtvw;
        ImageView appicon_imgvw;
        LinearLayout linearLayout;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            appname_txtvw = (TextView) itemView.findViewById(R.id.appname_txtvw);
            packgname_txtvw = (TextView) itemView.findViewById(R.id.pckgnm_txtvw);
            vercode_txtvw = (TextView) itemView.findViewById(R.id.vercode_txtvw);
            vername_txtvw = (TextView) itemView.findViewById(R.id.vernm_txtvw);
            appicon_imgvw = (ImageView) itemView.findViewById(R.id.appicon_imgvw);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.row_linlay);
        }

    }

    private class AppFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                //No need for filter
                results.values = app_list;
                results.count = app_list.size();
                Log.i("Task1","performFiltering "+results.count);
            } else {
                ArrayList<AppDataModel> fRecords = new ArrayList<>();
                for (int i=0;i<app_list.size();i++){
                    AppDataModel dataModel = app_list.get(i);
                    String s = dataModel.getApp_name();
                    if (s.toUpperCase().trim().contains(constraint.toString().toUpperCase().trim())) {
                        fRecords.add(dataModel);
                    }
                }

                results.values = fRecords;
                results.count = fRecords.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //app_list = (ArrayList<AppDataModel>) results.values;
            app_list.clear();
            app_list.addAll((ArrayList<AppDataModel>) results.values);
            notifyDataSetChanged();
        }
    }
}
