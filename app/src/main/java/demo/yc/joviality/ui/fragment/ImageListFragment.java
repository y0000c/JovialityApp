package demo.yc.joviality.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.mvp.mvppresenter.base.ImageListPresenter;
import demo.yc.joviality.mvp.mvppresenter.imp.ImageListPresenterImp;
import demo.yc.joviality.mvp.mvpview.ImageFragListView;
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
public class ImageListFragment extends SubTypeFragment implements SwipeRefreshLayout.OnRefreshListener,ImageFragListView
{

    @BindView(R.id.image_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.image_list_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private ImageListPresenter mPresenter;
    private ImageListAdapter mAdapter;
    private String mSubType ="";
    private boolean isLoadMore = false;
    private int currentPager = 1;
    private int tempPager = 2;

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
    protected void initData()
    {
        if (getArguments() == null)
        {
            LogUtil.d("fragment","images arguments is null");
            return;
        }
        mSubType = getArguments().getString(SUB_TYPE);
        LogUtil.d("fragment","imageFrag--init" +mSubType);
    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_list_image;
    }

    @Override
    protected void initEvents()
    {
        LogUtil.d("life", TAG + " initEvents");

        mRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                mRefreshLayout.setRefreshing(true);
            }
        });


        mAdapter = new ImageListAdapter(getContext(),new ArrayList<ImageEntity>(),true);
        mAdapter.setLoadingView(R.layout.load_loading_layout);
        mAdapter.setOnCLickListener(new IRecyclerItemClickListener<ImageEntity>()
        {
            @Override
            public void onItemClick(ViewHolder holder, ImageEntity data, int position)
            {
                Log.w("click","click---->"+position);
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

        mPresenter = new ImageListPresenterImp(getContext(),this);
    }

    @Override
    protected void getData()
    {
        mPresenter.loadListData(mSubType,currentPager);
    }

    // 下拉刷新事件
    @Override
    public void onRefresh()
    {
        currentPager = 1;
        isLoadMore = false;
        getData();
    }

    @Override
    public void onError(String msg)
    {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
        if(isLoadMore)
        {
            mAdapter.setLoadFailedView(R.layout.load_failed_layout);
        }else
        {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onSuccess(List<ImageEntity> imageList)
    {
        mRefreshLayout.setRefreshing(false);
        if(isLoadMore)
        {
            if(imageList.size() == 0)
                mAdapter.setLoadEndView(R.layout.load_end_layout);
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
