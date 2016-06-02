package com.xyxd.fisher;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;


import com.avos.avoscloud.AVOSCloud;
import com.xyxd.fisher.Activity.MainActivity;
import com.xyxd.fisher.Http.Client;

import java.util.List;

public class MApplication extends Application {


    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }
    private static final String LTAG = MainActivity.class.getSimpleName();
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            Log.d(LTAG, "action: " + s);

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"F9JATEw7PKIUBQ3RObXW7n7y-gzGzoHsz","JMggWcALiKHQqkzj10mNuYHF");

        String processName = getProcessName(this, android.os.Process.myPid());
        if (getApplicationInfo().packageName.equals(processName)) {

        }

         String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
         String FILE = "saveUserNamePwd";//用于保存SharedPreferences的文件
         SharedPreferences sp = null;
        sp = getSharedPreferences(FILE, MODE_PRIVATE);
        isMemory = sp.getString("isMemory", "no");
        if (isMemory.equals("yes")) {
            String phone = sp.getString("phone", "");
            String password = sp.getString("password", "");
            Client.TryLogin(phone, password);
        }
    }
}
