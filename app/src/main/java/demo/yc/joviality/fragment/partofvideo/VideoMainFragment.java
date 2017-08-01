package demo.yc.joviality.fragment.partofvideo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.yc.joviality.fragment.base.BaseAppFragment;
import demo.yc.jovialityyc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoMainFragment extends BaseAppFragment
{


    public VideoMainFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_main, container, false);
    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_video_main;
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    protected void onFirstUserVisible()
    {

    }

    @Override
    protected void onUserVisible()
    {

    }

    @Override
    protected void onFirstUserInvisible()
    {

    }

    @Override
    protected void onUserInVisible()
    {

    }

    @Override
    protected View getLoadingTargetView()
    {
        return null;
    }


}
