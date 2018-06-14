package com.android.base.system;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity 管理类
 * <p>
 * created at 18:28 2018/6/7
 *
 * @author XuShuai
 * @version v1.0
 */
public class ActivityStack {

    /**
     * Activity栈，用于存放APP已打开的Activity引用，管理App 的Activity
     */
    private static Stack<Activity> sActivityStack = new Stack<>();

    /**
     * addActivity Activity入栈方法
     * <p>
     * created at 2018/6/13 14:33
     *
     * @param activity activity对象
     */
    public static void addActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    /**
     * removeActivity activity出栈方法
     * <p>
     * created at 2018/6/11 15:50
     *
     * @param activity Activity对象
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
        }
    }

    /**
     * currentActivity 获取当前在栈顶的activity方法
     * <p>
     * created at 2018/6/11 15:32
     *
     * @return android.app.Activity
     */
    public static Activity currentActivity() {
        return sActivityStack.empty() ? null : sActivityStack.lastElement();
    }

    /**
     * finishActivity 结束activity
     * <p>
     * created at 2018/6/11 15:50
     *
     * @param activity Activity对象
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            removeActivity(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     * <p>
     * created at 2018/6/13 14:54
     *
     * @param cls 类名
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * finishAllActivity 结束当前所有栈中的activity
     * <p>
     * created at 2018/6/11 15:52
     */
    public static void finishAllActivity() {
        while (!sActivityStack.empty()) {
            Activity activity = currentActivity();
            finishActivity(activity);
        }
    }

    /**
     * 退出App
     * <p>
     * created at 2018/6/13 14:55
     */
    public static void AppExit() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}