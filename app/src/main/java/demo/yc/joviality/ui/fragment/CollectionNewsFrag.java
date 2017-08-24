package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.NewsEntity;
import demo.yc.joviality.gen.NewsEntityDao;
import demo.yc.joviality.ui.activity.UrlDetailActivity;
import demo.yc.joviality.ui.adapter.NewsListAdapter;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionNewsFrag extends SubTypeFragment
{
    private NewsEntityDao dao;
    private int offsetNum = 0;
    private boolean isFirst = true;

    public static CollectionNewsFrag newInstance()
    {
        CollectionNewsFrag fragment = new CollectionNewsFrag();
        return fragment;
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
        mAdapter = new NewsListAdapter(getContext(),new ArrayList<NewsEntity>(),true);
        mAdapter.showLoadingView();
        mAdapter.setOnCLickListener(new IRecyclerItemClickListener<NewsEntity>()
        {
            @Override
            public void onItemClick(ViewHolder holder, NewsEntity data, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable(UrlDetailActivity.URL_TAG,data);
                mContext.jumpToActivity(UrlDetailActivity.class,bundle,false);
            }
        });
        mAdapter.setOnLoadMoreListener(new IRecyclerLoadMoreListener()
        {
            @Override
            public void onLoadMore(boolean isReload)
            {
                getData();
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        dao = MyApp.getDaoSession().getNewsEntityDao();
    }

    @Override
    protected void getData()
    {
        onSuccess(dao.queryBuilder().limit(10).offset(offsetNum).list());
    }


    public void onSuccess(List<NewsEntity> newsList)
    {
        offsetNum+=newsList.size();
        mRefreshLayout.setRefreshing(false);
        if(offsetNum > 0)
        {
            mRecyclerView.setVisibility(View.VISIBLE);
            mRefreshLayout.setEnabled(false);
        }
        if(isFirst)
        {
            isFirst = false;
            mAdapter.setNewData(newsList);
            mRefreshLayout.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mRefreshLayout.setRefreshing(false);
                }
            });
        }else
        {
            if(newsList.size() == 0)
                mAdapter.showLoadEndView();
            else
                mAdapter.setLoadMoreData(newsList);
        }

    }

    @Override
    public void onRefresh()
    {
        getData();
    }
}
