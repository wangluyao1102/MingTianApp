package com.yundang.mingtian.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2018-01-03.
 * 统一应用程序中所有的Activity的栈管理（单例）
 * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
 */

public class ActivityManager {
    private ActivityManager() {

    }

    private static ActivityManager activityManager = new ActivityManager();

    public static ActivityManager getInstance() {
        return activityManager;
    }

    //提供栈的对象
    private Stack<Activity> activityStack = new Stack<>();

    //activity的添加
    public void add(Activity activity) {
        if (activityStack != null) {
            activityStack.add(activity);
        }
    }

    //插曲：[12,3,44,6,332,65,-56,1]
    //删除指定的activity
    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity currentActivity = activityStack.get(i);
                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish();//销毁当前的activity
                    activityStack.remove(i);//从栈空间移除
                }
            }
        }
    }

    //删除当前的activity
    public void removeCurrent() {
        //方式二：
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //删除所有的activity
    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

    //返回栈大小
    public int size() {
        return activityStack.size();
    }


}
