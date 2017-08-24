package demo.yc.joviality.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import demo.yc.joviality.mvp.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvp.mvppresenter.imp.SplashPresenterImp;
import demo.yc.joviality.mvp.mvpview.SplashView;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.jovialityyc.R;

import static demo.yc.joviality.conf.CommonContent.PERMISSION_STORAGE;
import static demo.yc.joviality.conf.CommonContent.STORAGE_CODE;

public class SplashActivity extends BaseAppActivity implements SplashView
{
    @BindView(R.id.main_image)
    ImageView mMainImage;

    @BindView(R.id.main_daily_message)
    TextView mMainDailyMessage;

    /**
     * MainActivity对应的presenter层类
     */
    private BasePresenter mMainPresenter;

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    /**
     * 返回当前Activity要显示的layoutId
     *
     * @return
     */
    @Override
    protected int getLayoutId()
    {
        // 设置全屏要在setContentView之前，因此就在返回layout之前设置就好
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
               ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initEvents()
    {
       doSdcardPermission();
    }


    /**
     * 实现mainView，显示背景图片
     *
     * @param backImageId
     */
    @Override
    public void showBackImage(int backImageId)
    {
        mMainImage.setBackgroundResource(backImageId);
    }

    /**
     * 实现mainView，显示每日寄语
     *
     * @param msg
     */
    @Override
    public void showDailyMessage(String msg)
    {
        mMainDailyMessage.setText(msg);
    }

    /**
     * 实现mainView，显示背景图片的动画效果
     *
     * @param animation
     */
    @Override
    public void showAnimation(Animation animation)
    {
        mMainImage.startAnimation(animation);
    }

    /**
     * 实现mainView,跳转到HomeActivity。在背景图片的动画结束 后自动跳转
     */
    @Override
    public void startToHomeActivity()
    {
        jumpToActivity(HomeActivity.class, null,true);
    }


    /**
     * 判断有没有权限
     */
    private void doSdcardPermission()
    {
        if(hasPermission(PERMISSION_STORAGE))
            doPermissionSuccess();
        else
            requestPermission(STORAGE_CODE,PERMISSION_STORAGE);
    }

    /**
     * 权限申请成功
     */
    private void doPermissionSuccess()
    {
        mMainPresenter = new SplashPresenterImp(this, this);
        mMainPresenter.initialized();
    }

    /**
     * 权限申请失败
     */
    private void doPermissionError()
    {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == STORAGE_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                doPermissionSuccess();
            else
                openPermission("为了更好的使用，请开启手机存储访问权限",true);
        }
    }
}
