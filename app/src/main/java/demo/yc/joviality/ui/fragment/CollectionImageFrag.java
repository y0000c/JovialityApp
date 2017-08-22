package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.gen.ImageEntityDao;
import demo.yc.joviality.ui.activity.ImageDetailActivity;
import demo.yc.joviality.ui.adapter.ImageListAdapter;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionImageFrag extends SubTypeFragment
{
    private ImageEntityDao dao ;

    public static CollectionImageFrag newInstance()
    {
        CollectionImageFrag fragment = new CollectionImageFrag();
        return fragment;
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
        mAdapter = new ImageListAdapter(getContext(),new ArrayList<ImageEntity>(),true);
        mAdapter.showLoadingView();
        mAdapter.setOnCLickListener(new IRecyclerItemClickListener<ImageEntity>()
        {
            @Override
            public void onItemClick(ViewHolder holder, ImageEntity data, int position)
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
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        dao = MyApp.getDaoSession().getImageEntityDao();

    }

    @Override
    protected void getData()
    {
        onSuccess(dao.queryBuilder().limit(10).list());
    }

    public void onSuccess(List<ImageEntity> imageList)
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
