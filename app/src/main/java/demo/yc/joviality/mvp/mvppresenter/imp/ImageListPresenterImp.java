package demo.yc.joviality.mvp.mvppresenter.imp;

import android.app.Activity;
import android.content.Context;

import demo.yc.joviality.entity.ResponseImageListEntity;
import demo.yc.joviality.interfaces.IListDataCallback;
import demo.yc.joviality.mvp.mvpmodel.ImageListFragModelImp;
import demo.yc.joviality.mvp.mvppresenter.base.ImageListPresenter;
import demo.yc.joviality.mvp.mvpview.ImageFragListView;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:43
 */

public class ImageListPresenterImp implements ImageListPresenter, IListDataCallback<ResponseImageListEntity>
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
    public void loadListData(String requestTag, int eventCode, String keywords, int page, boolean isRefresh)
    {
        mView.hideLoading();
        if(!isRefresh)
            mView.showLoading("正在加载图片");

        // 回调的结果在onFailure  onResponse
        mModelImp.getListData(requestTag,eventCode,keywords,page);
    }

    @Override
    public void onItemClick()
    {

    }


    // 请求数据后的三个回调
    @Override
    public void onSuccess(int eventCode, final ResponseImageListEntity data)
    {
        ((Activity)mContext).runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mView.hideLoading();
                mView.refreshListData(data);
            }
        });

    }

    @Override
    public void onError(String msg)
    {

    }

    @Override
    public void onException(String msg)
    {

    }
}
