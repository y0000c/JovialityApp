package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.GankEntity;
import demo.yc.joviality.gen.GankEntityDao;
import demo.yc.joviality.ui.activity.GankDetailActivity;
import demo.yc.joviality.ui.adapter.GankListAdapter;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionGankFrag extends SubTypeFragment
{
    private GankEntityDao dao;
    private int offsetNum = 0;
    private boolean isFirst = true;

    public static CollectionGankFrag newInstance()
    {
        CollectionGankFrag fragment = new CollectionGankFrag();
        return fragment;
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
        mAdapter = new GankListAdapter(getContext(),new ArrayList<GankEntity>(),true);
        mAdapter.showLoadingView();
        mAdapter.setOnCLickListener(new IRecyclerItemClickListener<GankEntity>()
        {
            @Override
            public void onItemClick(ViewHolder holder, GankEntity data, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GankDetailActivity.GANK_TAG,data);
                mContext.jumpToActivity(GankDetailActivity.class,bundle,false);
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

        dao = MyApp.getDaoSession().getGankEntityDao();
    }

    @Override
    protected void getData()
    {
        onSuccess(dao.queryBuilder().limit(10).offset(offsetNum).list());
    }


    public void onSuccess(List<GankEntity> gankList)
    {
        offsetNum+=gankList.size();
        if(offsetNum > 0)
        {
            mRecyclerView.setVisibility(View.VISIBLE);
            mRefreshLayout.setEnabled(false);
        }
        if(isFirst)
        {
            isFirst = false;
            mAdapter.setNewData(gankList);
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
            if(gankList.size() == 0)
                mAdapter.showLoadEndView();
            else
                mAdapter.setLoadMoreData(gankList);
        }

    }

    @Override
    public void onRefresh()
    {
        getData();
    }
}
