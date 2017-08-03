package demo.yc.joviality.fragment.partofimage;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import demo.yc.joviality.fragment.base.BaseAppFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends BaseAppFragment
{

    @BindView(R.id.image_list_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.image_list_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_image_list;
    }

    @Override
    protected void initEvents()
    {
        LogUtil.d("life", TAG + " initEvents");
    }

    @Override
    protected void onFirstUserVisible()
    {
        LogUtil.d("life", TAG + "_onFirstUserVisible");
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
        return mRefreshLayout;
    }

}
