package com.android.mediaclforuser.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 */
public class ViewHolder {
    private SparseArray<View> mView;
    private int mPosition=0;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mView = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }


    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }else{
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View>T getView(int viewId){
        View view = mView.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

    public ViewHolder setText(int viewId,String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

}
