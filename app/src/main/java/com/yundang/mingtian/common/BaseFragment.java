package com.yundang.mingtian.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.yundang.mingtian.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-01-22.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadingPage=new LoadingPage(container.getContext()) {
            @Override
            public int layoutId() {
                return getLayoutId();
            }

            @Override
            protected void onSuccss(ResultState resultState, View view) {
                ButterKnife.bind(BaseFragment.this,view);
                initTiltle();
                initData(resultState.getContent());
            }

            @Override
            public String url() {
                return getUrl();
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }
        };


        return loadingPage;
    }

    public void show(){
        loadingPage.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        },1000);


    }




    protected abstract RequestParams getParams();
    protected abstract void initData(String content);
    protected abstract void initTiltle();
    protected abstract String getUrl();
    public abstract int getLayoutId();
}
