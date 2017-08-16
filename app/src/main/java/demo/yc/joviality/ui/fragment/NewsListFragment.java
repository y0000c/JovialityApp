package demo.yc.joviality.ui.fragment;


import android.os.Bundle;

import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;


public class NewsListFragment extends SubTypeFragment
{
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

    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_list_news;
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void getData()
    {

    }
}
