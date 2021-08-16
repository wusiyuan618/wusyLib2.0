package com.wusy.wusylibrary.pop;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import com.huantansheng.easyphotos.EasyPhotos;
import com.wusy.wusylibrary.activity.easyPhoto.GlideEngine;
import com.wusy.wusylibrary.util.CameraUtil;
import com.wusy.wusylibrary.activity.uploadFile.LocationFileSelectActivity;
import com.wusy.wusylibrary.R;

import razerdp.basepopup.BasePopupWindow;

public class ChoiceFilePop extends BasePopupWindow {
    Activity context;
    public int checkAlnum = 0x01;
    public int checkFile = 0x02;
    public int maxSelectCount=1;
    public ChoiceFilePop(Activity context) {
        super(context);
        this.context = context;
        setBlurBackgroundEnable(true);
        setPopupGravity(Gravity.CENTER);
        findViewById(R.id.tvCancel).setOnClickListener(v -> dismiss());
        findViewById(R.id.viewCancel).setOnClickListener(v -> dismiss());
        findViewById(R.id.tvAlbumMultiple).setOnClickListener(v -> {
            EasyPhotos.createAlbum(context,false,false,GlideEngine.getInstance())
                    .setCount(maxSelectCount)//参数说明：最大可选数，默认1
                    .start(checkAlnum);
            dismiss();
        });

        findViewById(R.id.tvFile).setOnClickListener(v -> {
            chooseFile(checkFile);
            dismiss();
        });

        findViewById(R.id.tvCamera).setOnClickListener(v -> {
            CameraUtil util = new CameraUtil(context);
            util.gotoCaptureRawOnly(context);
            dismiss();
        });
    }

    public void chooseFile(int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, LocationFileSelectActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    public void setShowFile(boolean showFile) {
        if (showFile) findViewById(R.id.tvFile).setVisibility(View.VISIBLE);
        else findViewById(R.id.tvFile).setVisibility(View.GONE);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 250);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 250);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_choice_file);
    }

    public void setMaxSelectCount(int maxSelectCount) {
        this.maxSelectCount = maxSelectCount;
    }
}
