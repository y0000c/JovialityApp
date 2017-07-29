package demo.yc.joviality.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import demo.yc.joviality.activity.base.BaseAppActivity;
import demo.yc.joviality.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvppresenter.imp.MainPresenterImp;
import demo.yc.joviality.mvpview.MainView;
import demo.yc.jovialityyc.R;

public class MainActivity extends BaseAppActivity implements MainView
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
        return R.layout.activity_main;
    }

    @Override
    protected void initEvents()
    {
        mMainPresenter = new MainPresenterImp(this, this);
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
        jumpToActivity(HomeActivity.class, true);
    }



}
