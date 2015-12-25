package com.android.mediaclforuser.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
public abstract class CommonArrayAdapter<T> extends ArrayAdapter<T> {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater inflater;
    protected  int mViewId;

    public CommonArrayAdapter(Context context, int resource, ArrayList<T> objects) {
        super(context, resource, objects);
        this.mContext =context;
        this.mDatas =objects;
        this.mViewId = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = ViewHolder.get(mContext,convertView,parent, mViewId,position);
        convert(holder,getItem(position),position);
        return holder.getConvertView();
    }

    public abstract  void convert(ViewHolder holder,T t,int i);
}
