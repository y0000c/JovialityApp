package demo.yc.joviality.fragment.partofimage;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import demo.yc.joviality.adapter.ImageMainFragAdapter;
import demo.yc.joviality.fragment.base.BaseAppFragment;
import demo.yc.joviality.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvppresenter.imp.ImageMainPresenterImp;
import demo.yc.joviality.mvpview.FragMainView;
import demo.yc.jovialityyc.R;
import demo.yc.lib.uis.YCPagerIndicator;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageMainFragment extends BaseAppFragment implements FragMainView
{

    @BindView(R.id.image_main_pager)
    ViewPager mPager;

    @BindView(R.id.image_main_indicator)
    YCPagerIndicator mIndicator;


    private ImageMainFragAdapter adapter;

    private BasePresenter mPresenter;

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_image_main;
    }

    @Override
    protected void initEvents()
    {
        LogUtil.d("life", "initEvents");
        mPresenter = new ImageMainPresenterImp(getContext(), this);
        mPresenter.initialized();
    }

    @Override
    protected void onFirstUserVisible()
    {
        LogUtil.d("life", "first visible");
    }

    @Override
    protected void onUserVisible()
    {
        LogUtil.d("life", "visible");
    }

    @Override
    protected void onFirstUserInvisible()
    {
        LogUtil.d("life", "first Invisible");
    }

    @Override
    protected void onUserInVisible()
    {
        LogUtil.d("life", "Invisible");
    }

    @Override
    protected View getLoadingTargetView()
    {
        return null;
    }

    @Override
    public void initPagerViews(final List<String> list)
    {

        // 初始化 tabLayout  和 viewPager  ，并绑定
        adapter = new ImageMainFragAdapter(getChildFragmentManager(), list);
        mPager.setOffscreenPageLimit(list.size());
        mPager.setAdapter(adapter);

        // 为 tab 布局绑定viewPager 和 监听事件
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPagerChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                ImageListFragment fragment = (ImageListFragment)
                        mPager.getAdapter().instantiateItem(mPager,position);
                fragment.onPagerSelected(list.get(position),position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

}
