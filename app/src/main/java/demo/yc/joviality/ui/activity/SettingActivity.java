package demo.yc.joviality.ui.activity;

import android.os.Bundle;
import android.util.Log;

import butterknife.OnClick;
import demo.yc.joviality.MyApp;
import demo.yc.joviality.gen.SkinEntityDao;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.skin.SkinManager;
import demo.yc.lib.skin.callback.ISkinChangeCallback;
import demo.yc.lib.skin.config.Const;
import demo.yc.lib.utils.LogUtil;
import demo.yc.lib.utils.ResUtils;

public class SettingActivity extends BaseAppActivity
{


    SkinEntityDao dao = MyApp.getDaoSession().getSkinEntityDao();

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
        Log.w("file","database skin size is "+dao.loadAll().size());
    }

    @OnClick(R.id.setting_change_green)
    public void onClickedGreen()
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

    @OnClick(R.id.setting_change_red)
    public void onClickedRed()
    {
        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_RED_APK).getAbsolutePath(), Const.SKIN_RED_PACK, new ISkinChangeCallback()
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

    @OnClick(R.id.setting_change_purple)
    public void onClickedPurple()
    {
        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_PURPLE_APK).getAbsolutePath(), Const.SKIN_PURPLE_PACK, new ISkinChangeCallback()
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

    @OnClick(R.id.setting_reset)
    public void onClickedReset()
    {
        SkinManager.getInstance().reset();
    }
}
