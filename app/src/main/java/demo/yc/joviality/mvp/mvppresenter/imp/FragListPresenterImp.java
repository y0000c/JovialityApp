package demo.yc.joviality.mvp.mvppresenter.imp;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import demo.yc.joviality.interfaces.IListDataCallback;
import demo.yc.joviality.mvp.mvpmodel.FragListModelImp;
import demo.yc.joviality.mvp.mvppresenter.base.FragListPresenter;
import demo.yc.joviality.mvp.mvpview.FragListView;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:43
 */

public class FragListPresenterImp<T> implements
        FragListPresenter, IListDataCallback<T>
{
    private Context mContext;

    private FragListView mView;

    private FragListModelImp<T> mModelImp;


    public FragListPresenterImp(Context context, FragListView view)
    {
        this.mContext = context;
        this.mView = view;
        mModelImp = new FragListModelImp<T>(this);
    }


    @Override
    public void loadListData(int item,String requestTag, int page)
    {
        mModelImp.getListData(item,requestTag,page);
    }

    @Override
    public void loadListData(String id, String name, int page,int days)
    {
        mModelImp.getListData(id,name,page,days);
    }

    @Override
    public void onSuccess(final List<T> data)
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
        if(mContext != null)
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
