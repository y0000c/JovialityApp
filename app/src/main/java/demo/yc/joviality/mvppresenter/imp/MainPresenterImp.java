package demo.yc.joviality.mvppresenter.imp;

import android.content.Context;
import android.view.animation.Animation;

import demo.yc.joviality.mvpmodel.MainModelImp;
import demo.yc.joviality.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvpview.MainView;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MainPresenterImp implements BasePresenter
{

    /**
     * Activity上下文
     */
    private Context mContext;

    /**
     * Activity 对应view接口
     */
    private MainView mMainView;

    /**
     * Activity 对应数据操作类
     */
    private MainModelImp mainModelImp;


    /**
     * 构造函数，传入上下文，和Activity对应的view,用于操作
     * @param context
     * @param mainView
     */
    public MainPresenterImp(Context context,MainView mainView)
    {
        if(mainView == null)
            throw new IllegalArgumentException("the view can not be null");

        mContext = context;
        mMainView= mainView;
        mainModelImp = new MainModelImp();
    }

    /**
     * 初始化MainActivity的UI信息
     */
    @Override
    public void initialized()
    {
        mMainView.showBackImage(mainModelImp.getBackImageId());
        mMainView.showDailyMessage(mainModelImp.getDailyMessage(mContext));

        Animation animation = mainModelImp.getImageAnimation(mContext);
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mMainView.startToHomeActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        mMainView.showAnimation(animation);
    }
}
