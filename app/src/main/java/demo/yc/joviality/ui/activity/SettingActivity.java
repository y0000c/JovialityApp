package demo.yc.joviality.ui.activity;

import android.os.Bundle;

import java.io.File;

import demo.yc.joviality.conf.FilePath;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;
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

        FilePath.createDir(FilePath.cachePath);
        File file = new File(FilePath.cachePath);
        LogUtil.d("file","file size is "+ file.length());
    }
}
