package demo.yc.joviality.fragment.partofimage;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.yc.joviality.adapter.ImageListAdapter;
import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.entity.ResponseImageListEntity;
import demo.yc.joviality.fragment.base.BaseAppFragment;
import demo.yc.joviality.interfaces.IFragPagerSelectedListener;
import demo.yc.joviality.interfaces.IRecyclerItemClickListener;
import demo.yc.joviality.mvppresenter.base.ImageListPresenter;
import demo.yc.joviality.mvppresenter.imp.ImageListPresenterImp;
import demo.yc.joviality.mvpview.ImageFragListView;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends BaseAppFragment implements ImageFragListView
        , IFragPagerSelectedListener, IRecyclerItemClickListener, SwipeRefreshLayout.OnRefreshListener
{


    @BindView(R.id.image_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.image_list_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;


    private ImageListPresenter mPresenter;

    private ImageListAdapter mAdapter;

    private List<ImageEntity> mData;


    private int currentPage = 0;

    private String categoryName;


    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_image_list;
    }

    @Override
    protected void initEvents()
    {
        LogUtil.d("life", TAG + " initEvents");

        mData = new ArrayList<>();
        mAdapter = new ImageListAdapter(getContext(), mData);
        mAdapter.setOnItemClickListener(this);

        RecyclerView.LayoutManager manager =
                new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);


        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void onFirstUserVisible()
    {
        LogUtil.d("life", TAG + "_onFirstUserVisible");
        mPresenter = new ImageListPresenterImp(getContext(), this);
        if(mRefreshLayout != null)
        {
            mRefreshLayout.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    mPresenter.loadListData(TAG, 0, categoryName, currentPage, false);
                }
            },100);
        }
    }

    @Override
    protected void onUserVisible()
    {
        LogUtil.d("life", TAG + " onUserVisible");
    }

    @Override
    protected void onFirstUserInvisible()
    {
        LogUtil.d("life", TAG + " onFirstUserInvisible");
    }

    @Override
    protected void onUserInVisible()
    {
        LogUtil.d("life", TAG + "_onUserInVisible");
    }

    @Override
    protected View getLoadingTargetView()
    {
        LogUtil.w(TAG, mRefreshLayout == null ? "null----" : "not null");
        return mRefreshLayout;
    }


    /**
     * 用于第一次更新数据和刷新数据，头部刷新的时候（和addMore区分开）
     */
    @Override
    public void refreshListData(ResponseImageListEntity data)
    {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshing(false);

        if (data != null)
        {
            LogUtil.d(TAG,data.getImageList());
            mAdapter.getDataList().clear();
            mAdapter.getDataList().addAll(data.getImageList());

        }
    }

    /**
     * 用于分页加载的数据，底部刷新的时候
     */
    @Override
    public void addMoreListData()
    {

    }

    /**
     * 外界调用，表示选中第几个fragment,用于切换fragment
     *
     * @param keyWord
     * @param position
     */
    @Override
    public void onPagerSelected(String keyWord, int position)
    {
        categoryName = keyWord;
    }


    /**
     * refreshLayout  下拉刷新事件响应
     */
    @Override
    public void onRefresh()
    {

    }

    /**
     * RecyclerView 点击事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position)
    {
        Log.w(TAG, "onItemClick: " + "item click--" + position);
    }

}
