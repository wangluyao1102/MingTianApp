package com.yundang.mingtian.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yundang.mingtian.R;
import com.yundang.mingtian.common.BaseActivity;
import com.yundang.mingtian.home.activity.PhotographActivity;
import com.yundang.mingtian.home.bean.Introduce;
import com.yundang.mingtian.util.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-01-25.
 */

public class PublicInfoActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.ib_public_back)
    ImageButton ibPublicBack;

    @Bind(R.id.tv_public_title)
    TextView tvPublicTitle;
    @Bind(R.id.ib_public_more)
    ImageButton ibPublicMore;
    @Bind(R.id.ll_root)
    LinearLayout lr;
    @Bind(R.id.wb_public_info)
    WebView wPI;

    Bundle bundle;
    String title;
    String website;

    @Override
    protected void initData() {
        setWebView(website);
        ibPublicBack.setOnClickListener(this);
        ibPublicMore.setOnClickListener(this);
    }

    @Override
    protected void initTitle() {

        Bundle data = getIntent().getBundleExtra("data");
        website = (String) data.getString("website");
        title = (String) data.getString("title");
        tvPublicTitle.setText(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publicinfo;
    }



    private void setWebView(String url) {
        if (url != null) {
            //wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wPI.loadUrl(url);
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wPI.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            //启用支持javascript
            WebSettings settings = wPI.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            //优先使用缓存
            // wPI.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ib_public_back:
                removeCurrentActivity();
                //startActivity(new Intent(PublicInfoActivity.this, PhotographActivity.class));
                break;
            case R.id.ib_public_more:
                if (lr.getVisibility() == View.VISIBLE) {
                    lr.setVisibility(View.GONE);
                } else {
                    lr.setVisibility(View.VISIBLE);
                }
                break;

        }
    }
}
