package com.yundang.mingtian.shopping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.shopping.bean.ResultBeanData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-02-01.
 */

public class SPGridViewAdapter extends BaseAdapter {


    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> data;

    public SPGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_sp_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = data.get(position);
        Glide.with(mContext)
                .load(AppNetConfig.BASE_URL + "img" +hotInfoBean.getFigure())
                .into(holder.ivSpImage);
        holder.tvSpName.setText(hotInfoBean.getName());
        holder.tvSpPrice.setText("￥" + hotInfoBean.getCover_price());
        return convertView;



    }

    static class ViewHolder {
        @Bind(R.id.iv_sp_image)
        ImageView ivSpImage;
        @Bind(R.id.tv_sp_price)
        TextView tvSpPrice;
        @Bind(R.id.tv_sp_name)
        TextView tvSpName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
