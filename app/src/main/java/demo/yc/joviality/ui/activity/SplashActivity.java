package demo.yc.joviality.ui.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.joviality.mvp.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvp.mvppresenter.imp.SplashPresenterImp;
import demo.yc.joviality.mvp.mvpview.SplashView;
import demo.yc.jovialityyc.R;

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
        mMainPresenter = new SplashPresenterImp(this, this);
        mMainPresenter.initialized();
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



}
