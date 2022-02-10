package com.coye.xptest;

import android.util.Log;
import android.view.View;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class myServer implements IXposedHookLoadPackage{
    public static int times = 0;

    public static void log(String logInfo){
        Log.d("xposedHook",logInfo);
    }
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("xposedHook","=====================hook start=====================");
        if(!loadPackageParam.packageName.equals("cn.fjzzy.faceapk")) return;
        log(String.format("find package %s ", loadPackageParam.packageName));
        //获取class 参数 类名，包
        Class<?> clazz = XposedHelpers.findClass("classname",loadPackageParam.classLoader);
        //hook类clazz的静态int类型变量name的值
        XposedHelpers.setStaticIntField(clazz,"name",123);
        //hook类clazz的静态String类型变量name的值
        XposedHelpers.setStaticObjectField(clazz,"name","aaa");
        //无参构造函数
        XposedHelpers.findAndHookConstructor(clazz, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                log("无参构造函数执行前");
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                log("无参构造函数执行后操作");
                super.afterHookedMethod(param);
            }
        });
        //有参构造函数
        XposedHelpers.findAndHookConstructor(clazz, String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                //不要去hook实例变量，此时还没产生
                param.args[0] = "修改后的参数，0代表第一个参数";
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
        //静态，私有，公共方法hook
        XposedHelpers.findAndHookMethod(clazz, "methodName", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
        //hook匿名内部类
        //classname: 报名加类名
        XposedHelpers.findAndHookMethod("com.xx.xx$1",loadPackageParam.classLoader, "methodName", View.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                View view = (View)param.args[0];
                log(Integer.toString(view.getId()));
            }
        });
        //Todo
        Log.d("xposedHook","=====================hook end=====================");

    }
}
