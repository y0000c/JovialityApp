package demo.yc.joviality.ui.activity.base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseActivity;
import demo.yc.lib.utils.LogUtil;

public abstract class BaseAppActivity extends BaseActivity
{

    protected Toolbar mToolbar;


    @Override
    public void setContentView(int layoutId)
    {
        super.setContentView(layoutId);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected boolean hasPermission(String... ps)
    {
        LogUtil.d("permission",ps[0]+"----"+ Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(ContextCompat.checkSelfPermission(this,ps[0])
                != PackageManager.PERMISSION_GRANTED)
            return false;
        return true;
    }

    protected void requestPermission(int code,String...ps)
    {
        ActivityCompat.requestPermissions(this,ps,code);
    }


    // 跳转到设置界面
    protected void openPermission(String msg,final boolean isFinish)
    {
        new AlertDialog.Builder(this).setMessage(msg)
                .setPositiveButton("去设置", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent localIntent = new Intent();
                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivity(localIntent);

                        if(isFinish)
                            finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(isFinish)
                            finish();
                    }
                }).create().show();

    }
}
