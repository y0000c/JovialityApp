package demo.yc.joviality.ui.activity;

import android.os.Bundle;

import java.io.File;

import butterknife.OnClick;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.skin.SkinManager;
import demo.yc.lib.skin.callback.ISkinChangeCallback;
import demo.yc.lib.skin.config.Const;
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
        setTitle(ResUtils.resToStr(this, R.string.setting));
        for(String item:Const.SKIN_PLUGIN_APK)
        {
            File file = getFileStreamPath(item);
            if (!file.exists())
            {
                LogUtil.w("file","setting-->"+file.getAbsolutePath());
            }else
            {
                LogUtil.w("file","setting-->file is  exists");
            }
        }
    }

    @OnClick(R.id.setting_change)
    public void onViewClicked()
    {
        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_GREEN_APK).getAbsolutePath(), Const.SKIN_GREEN_PACK, new ISkinChangeCallback()
        {
            @Override
            public void onStart()
            {
                LogUtil.w("skin","=========start to change skin========");
            }

            @Override
            public void onError(Exception e)
            {
                e.printStackTrace();
                LogUtil.w("skin","=============change skin error=========== ");
            }

            @Override
            public void onSuccess()
            {
                LogUtil.w("skin","============change skin  ok");
            }
        });
    }
}
