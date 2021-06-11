package com.wusy.wusylibrary.util.loggerExpand;

import android.content.Context;
import android.os.Environment;

import com.wusy.wusylibrary.util.FileUtil;

import java.io.File;

public class LoggerSetting {
    public static String fileName="LogsByWusyLib";
    public static int fileSize=1024*1024;
    public static String getFileDir(Context context) {
        return FileUtil.getFileDir(context);
    }
}
