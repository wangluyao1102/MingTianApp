package com.yundang.mingtian.home.adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yundang.mingtian.R;
import com.yundang.mingtian.SplashActivity;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.common.BaseActivity;
import com.yundang.mingtian.home.HomeFragment;
import com.yundang.mingtian.home.PublicInfoActivity;
import com.yundang.mingtian.home.bean.HotSpot;
import com.yundang.mingtian.util.UIUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HotSpotAdapter extends BaseAdapter implements View.OnClickListener {

    private List<HotSpot> mDataset;
    private Context context;
    private HomeFragment homeFragment;
    public HotSpotAdapter(Context context, List<HotSpot> mDataset, HomeFragment homeFragment) {
        this.mDataset = mDataset;
        this.context = context;
        this.homeFragment=homeFragment;
    }


    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_home_hotspot, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //装配数据
        HotSpot hotSpot = mDataset.get(position);
        holder.tvHHT.setText(hotSpot.hotSportTitle);
        holder.tvHHC.setText(hotSpot.hotSportContent);
        Picasso.with(context).load(hotSpot.hotSportimagePath).error(R.drawable.listdel).placeholder(R.drawable.listdel).into(holder.ivHHI);
        holder.tvHHC.setOnClickListener(this);
        holder.tvHHC.setTag(position);
        holder.tvHHT.setOnClickListener(this);
        holder.tvHHT.setTag(position);
        holder.ivHHI.setOnClickListener(this);
        holder.ivHHI.setTag(position);


        return convertView;
    }

    @Override
    public void onClick(View v) {
        Integer position = Integer.parseInt(v.getTag().toString());
        HotSpot hotSpot = mDataset.get(position);
        Bundle mBundle = new Bundle();
        mBundle.putString("title", hotSpot.hotSportTitle);
        mBundle.putString("website", hotSpot.hotSportWebSite);
        ((BaseActivity) homeFragment.getActivity()).goToActivity(PublicInfoActivity.class, mBundle);


    }


    class ViewHolder {
        @Bind(R.id.iv_home_hotspot_image)
        ImageView ivHHI;
        @Bind(R.id.tv_home_hostpot_title)
        TextView tvHHT;
        @Bind(R.id.tv_home_hostpot_content)
        TextView tvHHC;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
