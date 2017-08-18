package demo.yc.joviality.mvp.mvppresenter.imp;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.interfaces.IListDataCallback;
import demo.yc.joviality.mvp.mvpmodel.ImageListFragModelImp;
import demo.yc.joviality.mvp.mvppresenter.base.ImageListPresenter;
import demo.yc.joviality.mvp.mvpview.ImageFragListView;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:43
 */

public class ImageListPresenterImp implements
        ImageListPresenter, IListDataCallback<ImageEntity>
{
    private Context mContext;

    private ImageFragListView mView;

    private ImageListFragModelImp mModelImp;


    public ImageListPresenterImp(Context context,ImageFragListView view)
    {
        this.mContext = context;
        this.mView = view;
        mModelImp = new ImageListFragModelImp(this);
    }


    @Override
    public void loadListData(String requestTag, int page)
    {
        mModelImp.getListData(requestTag,page);
    }

    @Override
    public void onItemClick()
    {

    }


    // 请求数据后的三个回调
    @Override
    public void onSuccess(final List<ImageEntity> data)
    {
        ((Activity)mContext).runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mView.onSuccess(data);
            }
        });

    }


    @Override
    public void onError(final String msg)
    {
        ((Activity)mContext).runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mView.onError(msg);
            }
        });
    }

}
