package com.coye.xptest;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class myServer implements IXposedHookLoadPackage{
    public static int times = 0;

    public static void log(String logInfo){
        Log.d("xposedHook",logInfo);
    }
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("xposedHook","=====================hook start=====================");
//        if(!loadPackageParam.packageName.equals("cn.fjzzy.faceapk")) return;
//        if(times>3) return;
        times +=1;
        log(String.format("find package %s ", loadPackageParam.packageName));
        //Todo
        Log.d("xposedHook","=====================hook end=====================");

    }
}
