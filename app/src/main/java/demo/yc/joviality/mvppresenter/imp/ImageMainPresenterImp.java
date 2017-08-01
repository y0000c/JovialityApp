package demo.yc.joviality.mvppresenter.imp;

import android.content.Context;

import demo.yc.joviality.mvpmodel.ImageMainFragModelImp;
import demo.yc.joviality.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvpview.FragMainView;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class ImageMainPresenterImp implements BasePresenter
{
    private Context mContext;

    private FragMainView mFragMainView;

    private ImageMainFragModelImp mModel;

    public ImageMainPresenterImp(Context context,FragMainView view)
    {
        if(view == null)
            throw new IllegalArgumentException("the view can no be null");

        mContext = context;
        mFragMainView = view;
        mModel = new ImageMainFragModelImp();

    }
    @Override
    public void initialized()
    {
        mFragMainView.initPagerViews(mModel.getCategoryList());
    }
}
