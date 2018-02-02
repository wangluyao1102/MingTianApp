package com.yundang.mingtian;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.yundang.mingtian.bean.Updata;
import com.yundang.mingtian.common.AppNetConfig;
import com.yundang.mingtian.common.BaseActivity;
import com.yundang.mingtian.util.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-01-17.
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_splash_page)
    ImageView ivSplashPage;
    @Bind(R.id.tv_splash_time)
    TextView tvSplashTime;
    @Bind(R.id.tv_welcome_version)
    TextView tvWelcomeVersion;
    long startTime;
    Updata updateInfo;
    private int timeCount = 3;
    private static final int TO_MAIN = 1;
    private static final int DOWLOAD_VERSION_SUCCESS = 2;
    private static final int DOWNLOAD_APK_FAIL = 3;
    private static final int DOWNLOAD_APK_SUCCESS = 4;
    ProgressDialog dialog;
    File apkFile;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
                case DOWLOAD_VERSION_SUCCESS:
                    //获取当前应用的版本信息
                    String version = getVersion();
                    tvWelcomeVersion.setText(version);
                    if (version.equals(updateInfo.version)) {
                        UIUtils.toast("当前应用已经是最新版本", false);
                        toMain();
                    } else {
                        new AlertDialog.Builder(SplashActivity.this).setTitle("下载最新版本")
                                .setMessage(updateInfo.desc)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //下载服务器保存的应用数据
                                        downloadApk();
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                toMain();
                            }
                        }).setCancelable(false).show();


                    }
                    break;
                case DOWNLOAD_APK_FAIL:
                    UIUtils.toast("联网下载数据失败", false);
                    toMain();
                    break;
                case DOWNLOAD_APK_SUCCESS:
                    UIUtils.toast("下载应用数据成功", false);
                    dialog.dismiss();//进条度消失
                    installApk();//安装下载好的应用
                    finish();
                    break;
            }

        }
    };


    private void installApk() {


        if (Build.VERSION.SDK_INT >= 24) {
            File file = new File(apkFile.getAbsolutePath());
            Uri apkUri = FileProvider.getUriForFile(getBaseContext(), "com.dafangya.app.pro.fileprovider", file);//在AndroidManifest中的android:authorities值
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            getBaseContext().startActivity(install);

        } else {
            Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
            intent.setData(Uri.parse("file:" + apkFile.getAbsolutePath()));
            startActivity(intent);
        }

    }


    //下载新版APP
    private void downloadApk() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
        //下载文件的位置
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //存在存储卡
            filesDir = getExternalFilesDir("");
        } else {
            //手机存储卡
            filesDir = getFilesDir();
        }


        //将下载的文件存入filesDir路径中
        apkFile = new File(filesDir, "updata.apk");
        //启动分线程下载应用
        new Thread() {
            @Override
            public void run() {
                String path = updateInfo.apkUrl;
                HttpURLConnection conn = null;
                InputStream inputStream = null;
                FileOutputStream fos = null;
                try {
                    URL url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        dialog.setMax(conn.getContentLength());//设置dialog的最大值
                        inputStream = conn.getInputStream();
                        fos = new FileOutputStream(apkFile);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buffer)) != -1) {
                            dialog.incrementProgressBy(len);
                            fos.write(buffer, 0, len);
                        }
                        handler.sendEmptyMessage(DOWNLOAD_APK_SUCCESS);
                    } else {
                        handler.sendEmptyMessage(DOWNLOAD_APK_FAIL);
                    }


                } catch (Exception ex) {

                }

            }
        }.start();


    }

    private String getVersion() {
        String version = "未知版本";
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e) {

        }
        return version;
    }


    @Override
    protected void initData() {
        requestJson();
        updataApkFile();

    }

    private void updataApkFile() {
        //得到系统当前时间
        startTime = System.currentTimeMillis();
        //1.检查是否有网
        Boolean connect = isConnect();
        if (!connect) {
            //没有网络
            UIUtils.toast("当前没有联网", false);
            toMain();
        } else {
            //有网络,联系服务器检查最新版本
            AsyncHttpClient client = new AsyncHttpClient();
            String url = AppNetConfig.UPDATA;
            client.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    updateInfo = JSON.parseObject(content, Updata.class);
                    handler.sendEmptyMessage(DOWLOAD_VERSION_SUCCESS);

                }

                @Override
                public void onFailure(Throwable error, String content) {
                    UIUtils.toast("联网请求数据失败", false);
                    toMain();
                }
            });


        }

    }

    //检查是否有网
    private Boolean isConnect() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();

        }
        return connected;
    }


    private void toMain() {
        long currentTime = System.currentTimeMillis();
        long delayTime = 3000 - (currentTime - startTime);
        if (delayTime < 0) {
            delayTime = 0;
        } else {

            tvSplashTime.setText("点击进入");
        }
        handler.sendEmptyMessageDelayed(TO_MAIN, delayTime);

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_page;
    }

    @OnClick(R.id.tv_splash_time)
    public void clickMain(View view) {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    public void requestJson() {
        //加载过程中的图片显示placeholder,
        //加载失败中的图片显示error
        Picasso.with(SplashActivity.this).load(AppNetConfig.BASE_SERVER_SPLASH).fit().into(ivSplashPage);

    }
    //为了避免出现内存的泄漏，需要在onDestroy()中，移除所有未被执行的消息
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //方式一：
//        handler.removeMessages(WHAT_RESET_BACK);//移除指定id的所有的消息
        //方式二：移除所有的未被执行的消息
        handler.removeCallbacksAndMessages(null);
    }

}
