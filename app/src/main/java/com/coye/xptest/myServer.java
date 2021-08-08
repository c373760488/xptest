package com.coye.xptest;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class myServer implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("xposedHook","=========hook start========");
        if(!loadPackageParam.packageName.equals("package name")) return;
        Log.d("xposedHook", String.format("=========find package %s ========", loadPackageParam.packageName));
        //Todo
        Log.d("xposedHook","=========hook end========");
    }
}
