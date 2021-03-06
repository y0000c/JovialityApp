package demo.yc.joviality.mvp.mvppresenter.imp;

import android.content.Context;

import demo.yc.joviality.mvp.mvpmodel.HomeModelImp;
import demo.yc.joviality.mvp.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvp.mvpview.HomeView;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class HomePresenterImp implements BasePresenter
{
    /**
     * 上下文对象
     */
    private Context mContext;

    /**
     * HomeActivity 对应的view接口
     */
    private HomeView mHomeView;

    /**
     * HomeActivity 对应的数据操作类
     */
    private HomeModelImp mHomeModelImp;

    public HomePresenterImp(Context context, HomeView homeView)
    {
        if(homeView == null)
            throw new IllegalArgumentException("the view can not be null");

        mContext = context;
        mHomeView = homeView;
        mHomeModelImp = new HomeModelImp();

    }

    @Override
    public void initialized()
    {
        mHomeView.showNavigationView(mHomeModelImp.getMainImageId(mContext));
    }
}
