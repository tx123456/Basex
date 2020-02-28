package com.asecho.security;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.multidex.MultiDex;

import com.asecho.security.common.api.Constant;
import com.blankj.utilcode.util.Utils;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.Bugly;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Utils.init(context);
        MultiDex.install(context);
        Bugly.init(getApplicationContext(), Constant.Companion.getKEY_BUGLY(), false);
        String channel = WalleChannelReader.getChannel(context);
        fitPushChannel();
        AutoSize.initCompatMultiProcess(context);
        AutoSizeConfig.getInstance().setCustomFragment(true).setOnAdaptListener(new onAdaptListener() {
            @Override
            public void onAdaptBefore(Object target, Activity activity) {
                //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传Application!!!
//                AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
//                AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
//                LogUtils.d(String.format( "%s onAdaptBefore!", target.getClass().getName()));
            }

            @Override
            public void onAdaptAfter(Object target, Activity activity) {
//                LogUtils.d(String.format("%s onAdaptAfter!", target.getClass().getName()));
            }
        });
    }

    private void fitPushChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelID = "1";
            String channelName = "系统通知";
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
    }

    //返回
    public static Context getAppContext(){
        return context;
    }
}
