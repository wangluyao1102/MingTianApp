package com.yundang.mingtian.common;

/**
 * 配置网络请求相关的地址
 */
public class AppNetConfig {
   public static final String IPADDRESS = "47.100.23.222";
    //public static final String IPADDRESS = "192.168.3.123";
    public static final String BASE_URL = "http://" + IPADDRESS + ":8080/MingTianAppServer/";
    public static final String BASE_URL_IMG = "http://" + IPADDRESS + ":8080/MingTianAppServer/img/";


    public static final String BASE_SERVER_SPLASH = BASE_URL+"images/splash.png";
    public static final String HOME_SERVER = BASE_URL+"homeServlet";//首页数据请求
    public static final String UPDATA = BASE_URL+"updataServlet";//版本更新请求
    public static final String SHOPPING = BASE_URL+"shoppingServlet";//商城数据请求


}
