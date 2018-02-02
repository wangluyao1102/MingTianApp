package com.yundang.mingtian.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


import com.loopj.android.http.AsyncHttpClient;
import com.yundang.mingtian.bean.User;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-01-15.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //将当前的activity添加到ActivityManager中
        ActivityManager.getInstance().add(this);
        initTitle();
        initData();


    }

    public AsyncHttpClient client = new AsyncHttpClient();

    //启动新的activity
    public void goToActivity(Class Activity, Bundle bundle) {
        Intent intent = new Intent(this, Activity);
        //携带数据
        if (bundle != null && bundle.size() != 0) {
            intent.putExtra("data", bundle);
        }
        startActivity(intent);
    }

    //销毁当前的Activity
    public void removeCurrentActivity() {
        ActivityManager.getInstance().removeCurrent();
    }

    //销毁所有的activity
    public void removeAll() {
        ActivityManager.getInstance().removeAll();
    }

    //保存用户信息
    public void saveUser(User user) {
        SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", user.getName());
        editor.putString("imageurl", user.getImageurl());
        editor.putBoolean("iscredit", user.isCredit());
        editor.putString("phone", user.getPhone());
        editor.commit();//必须提交，否则保存不成功
    }

    //读取用户信息
    public User readUser() {
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        User user = new User();
        user.setName(sp.getString("name", ""));
        user.setImageurl(sp.getString("imageurl", ""));
        user.setPhone(sp.getString("phone", ""));
        user.setCredit(sp.getBoolean("iscredit", false));
        return user;
    }


    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();
}
