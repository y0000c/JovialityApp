package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.entity.NewsEntity;
import demo.yc.joviality.mvp.mvppresenter.imp.FragListPresenterImp;
import demo.yc.joviality.mvp.mvpview.FragListView;
import demo.yc.joviality.ui.activity.UrlDetailActivity;
import demo.yc.joviality.ui.adapter.NewsListAdapter;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;
import demo.yc.lib.utils.LogUtil;


public class NewsListFragment extends SubTypeFragment implements FragListView<NewsEntity>
{
    private static final String SUB_TYPE_ID = "typeId";
    public  String mSubId;
    private int dayCount = 0;
    private boolean isEnd = false;
    public static NewsListFragment newInstance(String id,String type)
    {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(SUB_TYPE,type);
        args.putString(SUB_TYPE_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData()
    {
        super.initData();
        mSubId = getArguments().getString(SUB_TYPE_ID);
        LogUtil.d("fragment",TAG+"---"+mSubId+"---"+mSubType);
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
        // mainType = ResUtils.resToStr(mContext,R.string.gank);
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
                isLoadMore = true;
                getData();
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new FragListPresenterImp<NewsEntity>(getContext(),this);
    }

    @Override
    protected void getData()
    {
        mPresenter.loadListData(mSubId,mSubType,currentPager,dayCount);
    }

    @Override
    public void onError(String msg)
    {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();

        if(isLoadMore)
            mAdapter.showLoadFiledView();
        else
            mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(List<NewsEntity> newsList)
    {
        currentPager++;
        if(newsList.size() >=1)
            mRecyclerView.setVisibility(View.VISIBLE);
        mRefreshLayout.setRefreshing(false);
        if(isLoadMore)
        {
            if(newsList.size() == 0)
            {
               mAdapter.showLoadEndView();
//                if(isEnd)
//                {
//                    mAdapter.showLoadingView();
//                }else
//                {
//                    dayCount++;
//                    currentPager = 1;
//                    isEnd = true;
//                    getData();
//                }
            }
            else
            {
                isEnd = false;
                mAdapter.setLoadMoreData(newsList);
                tempPager++;
            }
        }else
        {
            mAdapter.setNewData(newsList);
            mRefreshLayout.setEnabled(false);
        }
    }
}
