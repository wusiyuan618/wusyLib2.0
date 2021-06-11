package com.wusy.wusylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by DalaR on 2017/11/20.
 */

public class CommonUtil {

    /**
     * 通过包名获取类名
     *
     * @param packageName
     * @return
     */
    public static String getClassName(String packageName) {
        String[] names = packageName.split("\\.");
        String name = names[names.length - 1];
        return name;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null && cm.getActiveNetworkInfo() != null) {
            return cm.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }


    /**
     * 冒泡排序
     */
    public static void BubbleSort(ArrayList<Float> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                if (list.get(j - 1) > list.get(j)) {
                    //交换两个元素
                    float temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);
                }
            }
        }
    }
    /**
     * 打开软键盘
     * @param view
     */
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }
    /**
     * 关闭软键盘
     * @param view
     */
    public static void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    /**
     * 自动打开/关闭软键盘
     * @param view
     */
    public static void  toggleSoftInput(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0,0);
        }
    }

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 是否连接WIFI
     */
    public static boolean isWifiConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetworkInfo.isConnected())
        {
            return true ;
        }

        return false ;
    }

    /**
     * 判断点击区域是否在View内
     * @param v 目标View
     * @param event 点击实体
     * @return
     */
    private boolean isInView(View v, MotionEvent event) {
        int[] l = { 0, 0 };
        v.getLocationInWindow(l);
        int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
        float eventX = event.getX();
        float eventY = event.getY();
        Rect rect = new Rect(left, top, right, bottom);
        return rect.contains((int) eventX, (int) eventY);
    }
}
