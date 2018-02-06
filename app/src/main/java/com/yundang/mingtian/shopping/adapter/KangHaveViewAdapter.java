package com.yundang.mingtian.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.shopping.bean.ResultBeanData;

import java.util.List;

/**
 * Created by Administrator on 2018-02-02.
 */

public class KangHaveViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{





    private Context mContext;
    private ResultBeanData.ResultBean.KanghavelInfoBean data;
    private List<ResultBeanData.ResultBean.KanghavelInfoBean.ListBean> list;


    public KangHaveViewAdapter(Context mContext, ResultBeanData.ResultBean.KanghavelInfoBean data) {
        this.mContext = mContext;
        this.data = data;
        list = data.getList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KangHaveViewAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_travel, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFigure;

        private LinearLayout ll_root;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivFigure = (ImageView) itemView.findViewById(R.id.iv_figure);

            ll_root = (LinearLayout) itemView.findViewById(R.id.ll_root);
            ll_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onKangHaveRecyclerView != null) {
                        onKangHaveRecyclerView.onClick(getLayoutPosition());
                    }
                }
            });


        }

        public void setData(int position) {
            ResultBeanData.ResultBean.KanghavelInfoBean.ListBean listBean = list.get(position);
            Glide.with(mContext).load(AppNetConfig.BASE_URL_IMG + listBean.getFigure()).into(ivFigure);
        }
    }



    /**
     * 监听器
     */
    public interface OnKangHaveRecyclerView {
        //当某条被点击的时候回调
        void onClick(int position);
    }




    private OnKangHaveRecyclerView onKangHaveRecyclerView;

    public void setOnKangHaveRecyclerView(OnKangHaveRecyclerView onKangHaveRecyclerView) {
        this.onKangHaveRecyclerView = onKangHaveRecyclerView;
    }
}
