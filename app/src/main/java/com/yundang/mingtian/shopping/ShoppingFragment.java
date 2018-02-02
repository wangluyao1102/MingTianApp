package com.yundang.mingtian.shopping;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.common.BaseFragment;
import com.yundang.mingtian.shopping.adapter.ShoppingFragmentAdapter;
import com.yundang.mingtian.shopping.bean.ResultBeanData;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017-12-30.
 */
public class ShoppingFragment extends BaseFragment {

    @Bind(R.id.rv_home)
    RecyclerView rvHome;
    ResultBeanData.ResultBean resultBean;
    ShoppingFragmentAdapter shoppingFragmentAdapter;
    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initData(String content) {
        ResultBeanData resultBeanData = JSON.parseObject(content, ResultBeanData.class);
        resultBean = resultBeanData.getResult();


            if(resultBean!=null){
                shoppingFragmentAdapter=new ShoppingFragmentAdapter(getContext(),resultBean);
                rvHome.setAdapter(shoppingFragmentAdapter);
                //设置布局管理者
                GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

                    @Override
                    public int getSpanSize(int position) {
                        if(position<=3){

                            //隐藏

                        }else{
                            //显示

                        }
                        return 1;
                    }
                });
                rvHome.setLayoutManager(manager);
            }

    }

    @Override
    protected void initTiltle() {

    }

    @Override
    protected String getUrl() {
        return AppNetConfig.SHOPPING;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping;
    }


}
