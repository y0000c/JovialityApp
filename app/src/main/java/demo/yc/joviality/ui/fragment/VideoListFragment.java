package demo.yc.joviality.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoListFragment extends SubTypeFragment
{
    public static VideoListFragment newInstance(String type)
    {
        VideoListFragment fragment = new VideoListFragment();
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
        return R.layout.fragment_list_video;
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
