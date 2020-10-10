package com.wusy.wusylibrary.view.bottomSelect;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

/**
 * Created by DalaR on 2017/11/20.
 */

public class BottomSelectBean implements Serializable {
    /**
     * 是否被选中
     */
    private boolean isSelect;
    /**
     * 标题名
     */
    private String title;
    /**
     * 被选中时时显示的图标
     */
    private int selectIcon;
    /**
     * 未被选中时显示的图标
     */
    private int normalIcon;
    /**
     * 红点图片选中状态
     */
    private int redIconSelect;
    /**
     * 红点图片未选中状态
     */
    private int redIconNormal;

    /**
     * Item项的LinearLayout
     */
    private LinearLayout linearLayout;
    /**
     * Item项的TextView
     */
    private TextView textView;
    /**
     * Item项的imageView
     */
    private ImageView imageView;
    /**
     * Item对应要显示的fragment
     */
    private Fragment fragment;
    /**
     * Fragment是否被添加
     */
    private boolean isAdd=false;
    private BottomSelectView.BottomSelectViewClickListener listener;



    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(int selectIcon) {
        this.selectIcon = selectIcon;
    }

    public int getNormalIcon() {
        return normalIcon;
    }

    public void setNormalIcon(int normalIcon) {
        this.normalIcon = normalIcon;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public BottomSelectView.BottomSelectViewClickListener getListener() {
        return listener;
    }

    public void setListener(BottomSelectView.BottomSelectViewClickListener listener) {
        this.listener = listener;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public int getRedIconSelect() {
        return redIconSelect;
    }

    public void setRedIconSelect(int redIconSelect) {
        this.redIconSelect = redIconSelect;
    }

    public int getRedIconNormal() {
        return redIconNormal;
    }

    public void setRedIconNormal(int redIconNormal) {
        this.redIconNormal = redIconNormal;
    }

    @Override
    public String toString() {
        return "BottomSelectBean{" +
                "isSelect=" + isSelect +
                ", title='" + title + '\'' +
                ", selectIcon=" + selectIcon +
                ", normalIcon=" + normalIcon +
                ", linearLayout=" + linearLayout +
                ", textView=" + textView +
                ", imageView=" + imageView +
                ", fragment=" + fragment +
                ", isAdd=" + isAdd +
                ", listener=" + listener +
                '}';
    }
}
