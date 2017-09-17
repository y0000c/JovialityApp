package demo.yc.joviality.mvp.mvppresenter.imp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import demo.yc.joviality.interfaces.IListDataCallback;
import demo.yc.joviality.mvp.mvpmodel.FragListModelImp;
import demo.yc.joviality.mvp.mvppresenter.base.FragListPresenter;
import demo.yc.joviality.mvp.mvpview.FragListView;
import demo.yc.lib.utils.LogUtil;

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

    private Handler handler;
    private static final int OK = 111;
    private static final int ERROR = 112;

    public FragListPresenterImp(Context context, FragListView view)
    {
        this.mContext = context;
        this.mView = view;
        mModelImp = new FragListModelImp<T>(this);
        handler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what == OK)
                {
                    mView.onSuccess((List<T>)msg.obj);
                }else
                {
                    mView.onError((String)msg.obj);
                }
            }
        };
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
    public void destroy()
    {
        LogUtil.d("handler",mContext.getClass().getSimpleName()+"----进入handler销毁阶段");
        if(handler != null)
        {
            handler.removeMessages(ERROR);
            handler.removeMessages(OK);
            LogUtil.d("handler",mContext.getClass().getSimpleName()+"-----handler已径移除了");

        }
    }

    @Override
    public void onSuccess(final List<T> data)
    {
        Message msg = handler.obtainMessage();
        msg.what = OK;
        msg.obj = data;
        handler.sendMessage(msg);
    }

    @Override
    public void onError(final String msg)
    {
        Message message = handler.obtainMessage();
        message.what = ERROR;
        message.obj = msg;
        handler.sendMessage(message);
    }





}
