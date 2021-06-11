package com.wusy.wusylibrary.util.loggerExpand;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.LogAdapter;

public class MyDiskLogAdapter implements LogAdapter {

    @NonNull
    private final MyFormatStrateg formatStrategy;


    public MyDiskLogAdapter(Context context){
        formatStrategy = MyFormatStrateg.newBuilder().build(context);
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        formatStrategy.log(priority, tag, message);
    }
}
