package com.yundang.mingtian;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yundang.mingtian.common.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-01-26.
 */

public class GuideActivity extends BaseActivity {
    @Bind(R.id.vp_guide)
    ViewPager vpGuide;
    @Bind(R.id.btn_start)
    TextView btnStart;
    @Bind(R.id.rl_root)
    RelativeLayout rlRoot;
    ArrayList<ImageView> mImageList;
    int[] mImageIds = new int[]{R.drawable.guide1,
            R.drawable.guide2, R.drawable.guide3};
    int timeCount = 2;
    SharedPreferences sp;


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countNum();
            handler.sendMessageDelayed(handler.obtainMessage(-1), 1000);
        }
    };


    private void countNum() {
        if (timeCount == 0) {
            startActivity(new Intent(GuideActivity.this, SplashActivity.class));
            finish();
        }
        timeCount--;
    }




    @Override
    protected void initData() {
        sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String guideinfo = sp.getString("guideinfo", "");
        //判断用户是否是初次使用,如果是进入引导页
        if (TextUtils.isEmpty(guideinfo)) {
            initViews();
            vpGuide.setAdapter(new GuidAdapter());
            vpGuide.setOnPageChangeListener(new GuidePageListener());
            btnStart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    // 跳转广告页面
                    handler.removeCallbacksAndMessages(null);
                    startActivity(new Intent(GuideActivity.this, SplashActivity.class));
                    finish();

                }
            });
        }else{
            //进入广告页
            startActivity(new Intent(GuideActivity.this, SplashActivity.class));
            finish();
        }

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }


    class GuidePageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPoxels) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {


            if (arg0 == mImageIds.length - 1) {
                //阈值用于判断下次用户是否还要登录
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("guideinfo","guideinfo");
                editor.commit();//必须提交，否则保存不成功

                btnStart.setVisibility(View.VISIBLE);
                handler.sendMessageDelayed(handler.obtainMessage(-1), 1000);
            }
        }

    }


    private void initViews() {
        mImageList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);
            image.setScaleType(ImageView.ScaleType.FIT_XY );
            mImageList.add(image);
        }
    }

    class GuidAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageList.get(position));

            return mImageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
