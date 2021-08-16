package com.wusy.wusypro.home.view;


import android.content.Context;

import com.wusy.wusylibrary.view.bottomSelect.BottomSelectBean;
import com.wusy.wusypro.R;
import com.wusy.wusypro.me.view.MeFragment;
import com.wusy.wusypro.tool.ToolFragment;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by XIAO RONG on 2018/5/6.
 */

public class MainDataUtil {
    private Context mC;
    private static MainDataUtil mainDataUtil;
    private List<BottomSelectBean> bottomSelectBeanList;

    private MainDataUtil(Context context) {
        this.mC=context;
    }

    public static synchronized MainDataUtil getInstance(Context context) {
        if (mainDataUtil == null) mainDataUtil = new MainDataUtil(context);
        return mainDataUtil;
    }

    /**
     * 底部选择器构建数据
     *
     * @return
     */
    public List<BottomSelectBean> getBottomSelectData() {
        bottomSelectBeanList = new ArrayList<>();

        //首页构建数据
        BottomSelectBean home = new BottomSelectBean();
        home.setSelect(false);
        home.setTitle("首页");
        home.setNormalIcon(R.mipmap.icon_home_normal);
        home.setSelectIcon(R.mipmap.icon_home_selected);
        home.setFragment(new HomeFragment());
        home.setListener(() -> {
        });
        //工具构建数据
        BottomSelectBean tool = new BottomSelectBean();
        tool.setSelect(true);
        tool.setTitle("工具");
        tool.setNormalIcon(R.mipmap.icon_home_normal);
        tool.setSelectIcon(R.mipmap.icon_home_selected);
        tool.setFragment(new ToolFragment());
        tool.setListener(() -> {
        });
        //我的构建数据
        BottomSelectBean my = new BottomSelectBean();
        my.setSelect(false);
        my.setTitle("我的");
        my.setNormalIcon(R.mipmap.icon_home_normal);
        my.setSelectIcon(R.mipmap.icon_home_selected);
//        my.setRedIconNormal(R.mipmap.icon_my_new_normal);
//        my.setRedIconSelect(R.mipmap.icon_my_new_selected);
        my.setFragment(new MeFragment());
        my.setListener(() -> {
        });
        bottomSelectBeanList.add(home);
        bottomSelectBeanList.add(tool);
        bottomSelectBeanList.add(my);

        return bottomSelectBeanList;
    }
}
