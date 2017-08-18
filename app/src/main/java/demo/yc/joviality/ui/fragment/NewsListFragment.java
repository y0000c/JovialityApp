package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;


public class NewsListFragment extends SubTypeFragment
{
    private String mSubType ;
    public static NewsListFragment newInstance(String type)
    {
        NewsListFragment fragment = new NewsListFragment();
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
            LogUtil.d("fragment","news arguments is null");
            return;
        }
        mSubType = getArguments().getString(SUB_TYPE);
        LogUtil.d("fragment","newFrag--init"+mSubType);
    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_list_type;
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void getData()
    {

    }

    @Override
    public void onRefresh()
    {

    }
}
