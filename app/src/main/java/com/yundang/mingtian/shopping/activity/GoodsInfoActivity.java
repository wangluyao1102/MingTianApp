package com.yundang.mingtian.shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.common.BaseActivity;
import com.yundang.mingtian.shopping.bean.GoodsBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-02-07.
 */

public class GoodsInfoActivity extends BaseActivity {

    @Bind(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @Bind(R.id.tv_good_info_name)
    TextView tvGoodInfoName;

    @Bind(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;

    GoodsBean goods_bean;
    @Bind(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        goods_bean = (GoodsBean) intent.getSerializableExtra("goods_bean");
        if (goods_bean != null) {
            setDataFormView(goods_bean);
        }

    }

    private void setDataFormView(GoodsBean goodsBean) {
        String name = goodsBean.getName();
        String price = goodsBean.getPrice();
        String figure = goodsBean.getFigure();
        String productid = goodsBean.getProduct_id();
        String remark = goodsBean.getRemark();
        String product_type = goodsBean.getProduct_type();


        Glide.with(this).load(AppNetConfig.BASE_URL_IMG + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (price != null) {
            tvGoodInfoPrice.setText("￥" + price);
        }
        setWebView(productid);

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_googsinfo;
    }

    private void setWebView(String product_id) {

        if (product_id != null) {
            //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
//            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wbGoodInfoMore.loadUrl("https://item.jd.com/1278702.html");
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            //启用支持javascript
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);

            //优先使用缓存
            //wbGoodInfoMore.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

}
