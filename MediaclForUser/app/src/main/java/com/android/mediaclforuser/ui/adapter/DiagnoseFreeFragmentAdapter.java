package com.android.mediaclforuser.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.model.Free;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/10/26.
 */
public class DiagnoseFreeFragmentAdapter extends RecyclerView.Adapter<DiagnoseFreeFragmentAdapter.ViewHolder> {

    private List<Free> frees = new ArrayList<>();
    private Context mContext;

    private ClickListener<Free> clickListener;

    public void setListData(List<Free> listData) {
        this.frees.clear();
        this.frees = listData;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;

    }

    public DiagnoseFreeFragmentAdapter(Context ctx) {
        super();
        this.mContext = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.fragment_diagnose_list_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return frees == null ? 0 : frees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.name)
        TextView mName;
        @Bind(R.id.time)
        TextView mTime;
        @Bind(R.id.icon)
        ImageView mIcon;
        @Bind(R.id.info)
        TextView mInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            if (clickListener != null) {
                Free free = frees.get(position);
                clickListener.OnClick(free);
            }
        }
    }
}
