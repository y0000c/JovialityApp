package demo.yc.joviality.ui.activity;

import android.os.Bundle;

import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.ResUtils;

public class SettingActivity extends BaseAppActivity
{


    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEvents()
    {
        setTitle(ResUtils.resToStr(this,R.string.setting));
    }
}
