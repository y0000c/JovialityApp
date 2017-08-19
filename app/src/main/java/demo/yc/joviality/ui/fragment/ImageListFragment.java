package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.entity.ResponseImageEntity;
import demo.yc.joviality.mvp.mvppresenter.imp.FragListPresenterImp;
import demo.yc.joviality.mvp.mvpview.FragListView;
import demo.yc.joviality.ui.activity.ImageDetailActivity;
import demo.yc.joviality.ui.adapter.ImageListAdapter;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends SubTypeFragment implements FragListView<ResponseImageEntity.ImgsBean>
{
    public static ImageListFragment newInstance(String type)
    {
        LogUtil.d("fragment",TAG+"--"+type);
        ImageListFragment fragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putString(SUB_TYPE,type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
      //  mainType = ResUtils.resToStr(mContext, R.string.image);
        mAdapter = new ImageListAdapter(getContext(),new ArrayList<ResponseImageEntity.ImgsBean>(),true);
        mAdapter.showLoadingView();
        mAdapter.setOnCLickListener(new IRecyclerItemClickListener<ResponseImageEntity.ImgsBean>()
        {
            @Override
            public void onItemClick(ViewHolder holder, ResponseImageEntity.ImgsBean data, int position)
            {
                Log.w("click","click---->"+position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ImageDetailActivity.IMAGE_TAG,data);
                mContext.jumpToActivity(ImageDetailActivity.class,bundle,false);
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

        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new FragListPresenterImp<ImageEntity>(getContext(),this);
    }

    @Override
    protected void getData()
    {
        mPresenter.loadListData(R.string.image,mSubType,currentPager);
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
    public void onSuccess(List<ResponseImageEntity.ImgsBean> imageList)
    {
        if(imageList.size() >=1)
            mRecyclerView.setVisibility(View.VISIBLE);
        mRefreshLayout.setRefreshing(false);
        if(isLoadMore)
        {
            if(imageList.size() == 0)
                mAdapter.showLoadEndView();
            else
            {
                mAdapter.setLoadMoreData(imageList);
                tempPager++;
            }
        }else
        {
            mAdapter.setNewData(imageList);
            mRefreshLayout.setEnabled(false);
        }
    }
}
