package demo.yc.joviality.ui.fragment.base;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import demo.yc.joviality.mvp.mvppresenter.base.FragListPresenter;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseAdapter;
import demo.yc.lib.base.BaseFragment;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class SubTypeFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener

{

    @BindView(R.id.image_list_recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.image_list_refresh_layout)
    protected SwipeRefreshLayout mRefreshLayout;

    protected String mainType;
    protected String mSubType;
    protected FragListPresenter mPresenter;
    protected BaseAdapter mAdapter;

    protected boolean isLoadMore = false;
    protected int currentPager = 1;
    protected int tempPager = 2;


    protected static final String SUB_TYPE = "subType";
    private boolean isViewInitiated;
    private boolean isVisibleToUser;
    private boolean isDataInitiated;


    @Override
    protected void initData()
    {
        if (getArguments() == null)
            return;
        mSubType = getArguments().getString(SUB_TYPE);
    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_list_type;
    }

    @Override
    protected void initEvents()
    {
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
        mRecyclerView.setVisibility(View.GONE);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.d("visible",TAG+" is visible to User-->"+isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        initGetData();
    }

    // 所有View创建好了，这是onStart前一个生命周期
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        initGetData();
    }

    /**
     * 满足所有条件才去获取数据
     */
    protected void initGetData()
    {
        //LogUtil.d("visible",isVisibleToUser+"--"+isViewInitiated+"--"+isDataInitiated);
        if(isViewInitiated && isVisibleToUser && !isDataInitiated)
        {
            getData();
            isDataInitiated = true;
        }
    }


    /**
     * 获取数据的地方
     */
    protected abstract void getData();


    // 下拉刷新事件
    @Override
    public void onRefresh()
    {
        currentPager = 1;
        isLoadMore = false;
        getData();
    }



}
