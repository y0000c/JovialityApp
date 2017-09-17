package demo.yc.lib.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import demo.yc.lib.R;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.listener.IRecyclerLoadMoreListener;
import demo.yc.lib.utils.ResUtils;

/**
 * @author: YC
 * @date: 2017/8/17 0017
 * @time: 10:36
 * @detail:
 *  完成通用的处理，具体到某个adapter只要实现抽象方法进行处理即可
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int TYPE_COMMON_VIEW = 0x000001;
    private static final int TYPE_FOOTER_VIEW = 0x000002;

    private Context mContext;
    private List<T> mDatas;
    private boolean mIsOpenLoadMore;
    private boolean isAutoLoadMore = true;

    private RelativeLayout mFooterLayout;
    // 这三个充当footerView的子View,不同情况下使用不同的view
    private View mLoadingView;
    private View mLoadEndView;
    private View mLoadFailedView;

    private IRecyclerItemClickListener clickListener;
    private IRecyclerLoadMoreListener loadMoreListener;


    public BaseAdapter(Context context, List<T> datas, boolean isOpenLoadMore)
    {
        mContext = context;
        mIsOpenLoadMore = isOpenLoadMore;
        if(datas == null)
            datas = new ArrayList<>();
        mDatas = datas;

        setLoadingView(R.layout.base_load_loading_layout);
        setLoadEndView(R.layout.base_load_end_layout);
        setLoadFailedView(R.layout.base_load_failed_layout);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder holder = null;
        switch (viewType)
        {
            case TYPE_COMMON_VIEW:
                holder = ViewHolder.create(mContext,getLayoutId(),parent);
                break;
            case TYPE_FOOTER_VIEW:
                if(mFooterLayout == null)
                    mFooterLayout = new RelativeLayout(mContext);
                holder = ViewHolder.create(mFooterLayout);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        switch (holder.getItemViewType())
        {
            case TYPE_COMMON_VIEW:
                bindItem(holder,position);
                break;
        }
    }



    @Override
    public int getItemCount()
    {
        return mDatas.size() + getFooterViewCount();
    }


    @Override
    public int getItemViewType(int position)
    {
        if(isFooterView(position))
            return TYPE_FOOTER_VIEW;

        return TYPE_COMMON_VIEW;
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder)
    {
        super.onViewAttachedToWindow(holder);
        if(isFooterView(holder.getLayoutPosition()))
        {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if(lp instanceof StaggeredGridLayoutManager.LayoutParams)
            {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        initLoadMore(recyclerView,manager);
    }

    private void initLoadMore(RecyclerView recyclerView, final RecyclerView.LayoutManager manager)
    {
        if(!mIsOpenLoadMore || loadMoreListener == null)
            return;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);

                // 避免顶部留出空白
                if(manager instanceof StaggeredGridLayoutManager)
                {
                    ((StaggeredGridLayoutManager)manager).invalidateSpanAssignments();
                }

                // 避免一开始就底部加载
                if(getItemCount() > 1)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (!isAutoLoadMore && findLastPosition(manager) + 1 == getItemCount()) {
                            startLoadMore();
                        }
                    }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if(getItemCount() > 1)
                    if(isAutoLoadMore && findLastPosition(manager)+1 == getItemCount())
                        startLoadMore();
                    else if(isAutoLoadMore)
                        isAutoLoadMore = false;

            }
        });
    }

    private int findLastPosition(RecyclerView.LayoutManager manager)
    {
        if (manager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) manager).findLastVisibleItemPosition();
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) manager).findLastVisibleItemPositions(null);
            return findMax(lastVisibleItemPositions);
        }
        return -1;
    }

    private int findMax(int[] array)
    {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void startLoadMore()
    {
        if (mFooterLayout.getChildAt(0) == mLoadingView)
            loadMoreListener.onLoadMore(false);

    }
    /**
     * 绑定布局和设置点击事件
     * @param holder
     * @param position
     */
    private void bindItem(RecyclerView.ViewHolder holder, final int position)
    {
        if(mDatas.size() == 0)
            return;
        final ViewHolder h = (ViewHolder) holder;
        bindItemData(h,mDatas.get(position));
        h.getConvertView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(clickListener != null)
                {
                    clickListener.onItemClick(h,mDatas.get(position),position);
                }
            }
        });
    }

    /**
     * 返回底部view的个数
     * @return
     */
    public int getFooterViewCount()
    {
        return mIsOpenLoadMore ? 1 : 0;
    }

    private boolean isFooterView(int position)
    {
        if(mIsOpenLoadMore && getItemCount() > 1
                && position >= getItemCount() -1)
            return true;
        return false;
    }

    // 绑定底部布局 和 根据具体情况显示

    public void setLoadingView(int resId)
    {
        if(resId <= 0)
            return;
        mLoadingView = ResUtils.inflate(mContext,resId,null);
    }

    public void setLoadFailedView(int resId)
    {
        if(resId <= 0)
            return;
        mLoadFailedView = ResUtils.inflate(mContext,resId,null);
        if(mLoadFailedView == null)
            return;
        mLoadFailedView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addFooterView(mLoadingView);
                loadMoreListener.onLoadMore(true);
            }
        });

    }

    public void setLoadEndView(int resId)
    {
        if(resId <= 0)
            return;
        mLoadEndView = ResUtils.inflate(mContext,resId,null);

    }

    public void showLoadingView()
    {
        addFooterView(mLoadingView);
    }

    public void showLoadFailedView()
    {
        addFooterView(mLoadFailedView);
    }

    public void showLoadEndView()
    {
        addFooterView(mLoadEndView);
    }

    private void addFooterView(View footerView)
    {
        RelativeLayout.LayoutParams lp = null;
        if(footerView == null)
            return;
        if(mFooterLayout != null)
        {
            mFooterLayout.removeAllViews();

            // 这边可能会遇到一个bug,当图片没有完全加载完，
            // 网络关了，此时滑到最底部
            // 会发现添加的footerlayout的lp类型是StaggeredGridLayoutManager的
            // 导致转换错误
            if(mFooterLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)
                lp = (RelativeLayout.LayoutParams) mFooterLayout.getLayoutParams();
        }
        else
            mFooterLayout = new RelativeLayout(mContext);

        if(lp == null)
            lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT
                    ,RelativeLayout.LayoutParams.WRAP_CONTENT);

        mFooterLayout.addView(footerView,lp);
    }


    // 刷新数据

    public void setNewData(List<T> data)
    {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void setLoadMoreData(List<T> data)
    {
        int size = mDatas.size();
        mDatas.addAll(data);
        if(size > 0)
            notifyDataSetChanged();
    }


    // 绑定事件

    /**
     * 添加底部加载事件
     */
    public void setOnLoadMoreListener(IRecyclerLoadMoreListener loadMoreListener)
    {
        this.loadMoreListener = loadMoreListener;
    }

    /**
     * 添加点击事件
     */
    public void setOnCLickListener(IRecyclerItemClickListener clickListener)
    {
        this.clickListener = clickListener;
    }


    // 子类复写方法

    /**
     * 绑定具体的数据
     * @param holder
     * @param t
     */
    protected abstract void bindItemData(ViewHolder holder, T t);

    /**
     * 获取具体的view布局
     * @return
     */
    public abstract int getLayoutId();

}
