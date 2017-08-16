package demo.yc.joviality.mvp.mvppresenter.imp;

import android.content.Context;
import android.view.animation.Animation;

import demo.yc.joviality.mvp.mvpmodel.SplashModelImp;
import demo.yc.joviality.mvp.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvp.mvpview.SplashView;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class SplashPresenterImp implements BasePresenter
{

    /**
     * Activity上下文
     */
    private Context mContext;

    /**
     * Activity 对应view接口
     */
    private SplashView mSplashView;

    /**
     * Activity 对应数据操作类
     */
    private SplashModelImp splashModelImp;


    /**
     * 构造函数，传入上下文，和Activity对应的view,用于操作
     * @param context
     * @param splashView
     */
    public SplashPresenterImp(Context context, SplashView splashView)
    {
        if(splashView == null)
            throw new IllegalArgumentException("the view can not be null");

        mContext = context;
        mSplashView = splashView;
        splashModelImp = new SplashModelImp();
    }

    /**
     * 初始化MainActivity的UI信息
     */
    @Override
    public void initialized()
    {
        mSplashView.showBackImage(splashModelImp.getBackImageId());
        mSplashView.showDailyMessage(splashModelImp.getDailyMessage(mContext));

        Animation animation = splashModelImp.getImageAnimation(mContext);
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mSplashView.startToHomeActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        mSplashView.showAnimation(animation);
    }
}
