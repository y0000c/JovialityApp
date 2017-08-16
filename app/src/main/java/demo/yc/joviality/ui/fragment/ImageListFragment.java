package demo.yc.joviality.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.yc.joviality.ui.adapter.ImageListAdapter;
import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.joviality.mvp.mvppresenter.base.ImageListPresenter;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends SubTypeFragment
{

    @BindView(R.id.image_list_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.image_list_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;


    private ImageListPresenter mPresenter;

    private ImageListAdapter mAdapter;

    private List<ImageEntity> mData;

    private String mSubType ;

    public static ImageListFragment newInstance(String type)
    {
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
            return;
        mSubType = getArguments().getString(SUB_TYPE);
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

        mData = new ArrayList<>();
        mAdapter = new ImageListAdapter(getContext(), mData);
       // mAdapter.setOnItemClickListener(this);

        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

       manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
       // mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                ((StaggeredGridLayoutManager)recyclerView
                        .getLayoutManager()).invalidateSpanAssignments();
            }
        });

    }

    @Override
    protected void getData()
    {

    }
}
