package com.yundang.mingtian.home;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.common.BaseActivity;
import com.yundang.mingtian.common.BaseFragment;
import com.yundang.mingtian.home.adapter.HotSpotAdapter;
import com.yundang.mingtian.home.bean.Home;
import com.yundang.mingtian.home.bean.Introduce;
import com.yundang.mingtian.home.bean.Slide;
import com.yundang.mingtian.home.bean.TodayNews;
import com.yundang.mingtian.util.MyTextView;
import com.yundang.mingtian.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2017-12-30.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.lv_home_list)
    ListView lHL;//明天动态与热点
    Banner banner;//幻灯片
    TextView tHI;//明天介绍内容
    MyTextView mHT;//今日头条
    Button bHW;
    HotSpotAdapter mAdapter;
    Introduce introduce;
    TodayNews todayNews;
    List<Slide> slide;
    List<String> images = new ArrayList<>();//幻灯片图片路径集合


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initData(String content) {
        //将服务器数据装载成对象
        Gson gson = new Gson();
        Home home = gson.fromJson(content, Home.class);//服务器返回集合
        introduce = home.introduce;//明天介绍集合
        todayNews = home.todayNews;//今日头条
        slide = home.slide;//幻灯片
        //加载List的头部信息
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_hear, null);
        banner = (Banner) view.findViewById(R.id.br_home_banner);
        //今日头条
        mHT = (MyTextView) view.findViewById(R.id.mv_home_todayNewsTitle);
        mHT.setText(todayNews.todayNewsIntroduced);
        mHT.setOnClickListener(this);
        //明天介绍
        tHI = (TextView) view.findViewById(R.id.tv_home_introduceContent);
        tHI.setText(introduce.introduceContent);
        //进入官网
        bHW = (Button) view.findViewById(R.id.bt_home_website);
        bHW.setOnClickListener(this);


        //幻灯片图片路径装载
        for (int i = 0; i < slide.size(); i++) {
            images.add(slide.get(i).slideImagePath);
        }


        //设置banner手风琴效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置循环指示器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImages(images, new OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                //联网请求图片glide
                Glide.with(getContext()).load(url.toString()).into(view);
            }
        });








        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Slide slideEvent = slide.get(position-1);
                Bundle mBundle = new Bundle();
                mBundle.putString("title", slideEvent.slideTitle);
                mBundle.putString("website", slideEvent.slideWebSite);
                ((BaseActivity) getActivity()).goToActivity(PublicInfoActivity.class, mBundle);
            }
        });



        lHL.addHeaderView(view);

        //装载listview的Adapter
        mAdapter = new HotSpotAdapter(UIUtils.getContext(), home.hotSpot,HomeFragment.this);
        lHL.setAdapter(mAdapter);
    }

    @Override
    protected void initTiltle() {

    }

    @Override
    protected String getUrl() {
        return AppNetConfig.HOME_SERVER;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    public void onClick(View v) {

        String title = "";
        String website = "";
        switch (v.getId()) {
            //进入官网
            case R.id.bt_home_website:
                title = introduce.introduceTitle;
                website = introduce.website;
                break;
            //今日头条
            case R.id.mv_home_todayNewsTitle:
                title = todayNews.todayNewsTitle;
                website = todayNews.todayNewsWebsite;
                break;
        }

        Bundle mBundle = new Bundle();
        mBundle.putString("title", title);
        mBundle.putString("website", website);
        ((BaseActivity) getActivity()).goToActivity(PublicInfoActivity.class, mBundle);
    }
}
