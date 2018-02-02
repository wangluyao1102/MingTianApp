package com.yundang.mingtian.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.common.BaseFragment;
import com.yundang.mingtian.util.UIUtils;


/**
 * Created by Administrator on 2017-12-30.
 */
public class MyFragment extends BaseFragment {


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initData(String content) {

    }

    @Override
    protected void initTiltle() {

    }

    @Override
    protected String getUrl() {
        return AppNetConfig.BASE_URL;
    }

    @Override
    public int getLayoutId() {
        return  R.layout.fragment_my;
    }
}
