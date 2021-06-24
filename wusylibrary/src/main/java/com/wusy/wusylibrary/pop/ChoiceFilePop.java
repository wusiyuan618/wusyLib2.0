package com.wusy.wusylibrary.pop;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;


import com.hjl.artisan.app.CameraUtil;
import com.hjl.artisan.tool.view.LocationFileSelectActivity;
import com.wusy.wusylibrary.R;
import com.wusy.wusylibrary.activity.uploadFile.camera.CameraActivity;
import com.wusy.wusylibrary.activity.uploadFile.localAum.common.LocalImageHelper;
import com.wusy.wusylibrary.activity.uploadFile.localAum.ui.LocalAlbum;

import razerdp.basepopup.BasePopupWindow;

public class ChoiceFilePop extends BasePopupWindow {
    Activity context;
    public int checkAlnum = 0x01;
    public int checkFile = 0x02;

    public String type = "";

    public ChoiceFilePop(Activity context) {
        super(context);
        this.context = context;
        setBlurBackgroundEnable(true);
        setPopupGravity(Gravity.CENTER);
        findViewById(R.id.tvCancel).setOnClickListener(v -> dismiss());
        findViewById(R.id.viewCancel).setOnClickListener(v -> dismiss());
        findViewById(R.id.tvAlbumMultiple).setOnClickListener(v -> {
            LocalImageHelper.getInstance().clear();
            Intent intent = new Intent(context, LocalAlbum.class);
            context.startActivityForResult(intent, checkAlnum);
            dismiss();
        });

        findViewById(R.id.tvFile).setOnClickListener(v -> {
            chooseFile(checkFile);
            dismiss();
        });


        findViewById(R.id.tvCamera).setOnClickListener(v -> {
            if (type.equals("")) {
                CameraUtil util = new CameraUtil(context);
                util.gotoCaptureRawOnly(context);
                dismiss();
            } else if (type.equals("人脸")) {
                CameraActivity.startMe(context, 6, CameraActivity.MongolianLayerType.FACE);
                dismiss();
            } else if (type.equals("身份证正面")) {
                CameraActivity.startMe(context, 6, CameraActivity.MongolianLayerType.IDCARD_POSITIVE);
                dismiss();
            }
        });
    }
    public void chooseFile(int requestCode){
        Intent intent = new Intent();
        intent.setClass(context, LocationFileSelectActivity.class);
        context.startActivityForResult(intent, requestCode);
    }
    public void setShowFile(boolean showFile){
        if(showFile) findViewById(R.id.tvFile).setVisibility(View.VISIBLE);
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

}
