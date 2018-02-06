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
 * Created by Administrator on 2018-02-02.
 */

public class IntegrationGridViewAdapter extends BaseAdapter {


    private Context mContext;
    private ResultBeanData.ResultBean.IntegrationBean data;
    private List<ResultBeanData.ResultBean.IntegrationBean.ListBean> list;
    public IntegrationGridViewAdapter(Context mContext, ResultBeanData.ResultBean.IntegrationBean data) {
        this.mContext = mContext;
        this.data = data;
        this.list=data.getList();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SPGridViewAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_sp_grid_view, null);
            holder = new SPGridViewAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SPGridViewAdapter.ViewHolder) convertView.getTag();
        }

        ResultBeanData.ResultBean.IntegrationBean.ListBean integrationBean = list.get(position);
        Glide.with(mContext)
                .load(AppNetConfig.BASE_URL + "img" +integrationBean.getFigure())
                .into(holder.ivSpImage);
        holder.tvSpName.setText(integrationBean.getName());
        holder.tvSpPrice.setText("￥" + integrationBean.getPrice());
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
