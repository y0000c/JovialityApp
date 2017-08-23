package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.entity.GankEntity;
import demo.yc.joviality.entity.ResponseGankEntity;
import demo.yc.joviality.mvp.mvppresenter.imp.FragListPresenterImp;
import demo.yc.joviality.mvp.mvpview.FragListView;
import demo.yc.joviality.ui.activity.UrlDetailActivity;
import demo.yc.joviality.ui.adapter.GankListAdapter;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class GanksListFragment extends SubTypeFragment implements FragListView<GankEntity>
{

    public static GanksListFragment newInstance(String type)
    {
        LogUtil.d("fragment",TAG+"--"+type);
        GanksListFragment fragment = new GanksListFragment();
        Bundle args = new Bundle();
        args.putString(SUB_TYPE,type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
       // mainType = ResUtils.resToStr(mContext,R.string.gank);
        mAdapter = new GankListAdapter(getContext(),new ArrayList<GankEntity>(),true);
        mAdapter.showLoadingView();
        mAdapter.setOnCLickListener(new IRecyclerItemClickListener<GankEntity>()
        {
            @Override
            public void onItemClick(ViewHolder holder, GankEntity data, int position)
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
                LogUtil.d("url","load----");
                // 已经没有新的数据了
                if(currentPager == tempPager && !isReload)
                    return;
                // 否则就继续加载新的数据
                isLoadMore = true;
                currentPager = tempPager;
                getData();
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new FragListPresenterImp<ResponseGankEntity>(getContext(),this);
    }

    @Override
    protected void getData()
    {
        mPresenter.loadListData(R.string.gank,mSubType,currentPager);
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
    public void onSuccess(List<GankEntity> gankList)
    {
        if(gankList.size() >=1)
            mRecyclerView.setVisibility(View.VISIBLE);
        mRefreshLayout.setRefreshing(false);
        if(isLoadMore)
        {
            if(gankList.size() == 0)
                mAdapter.showLoadEndView();
            else
            {
                mAdapter.setLoadMoreData(gankList);
                tempPager++;
            }
        }else
        {
            mAdapter.setNewData(gankList);
            mRefreshLayout.setEnabled(false);
        }
    }
}
