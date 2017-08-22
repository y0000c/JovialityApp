package demo.yc.joviality.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.joviality.ui.adapter.MainTypeFragAdapter;
import demo.yc.joviality.ui.fragment.CollectionGankFrag;
import demo.yc.joviality.ui.fragment.CollectionImageFrag;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.ResUtils;

public class CollectionActivity extends BaseAppActivity
{

    @BindView(R.id.image_main_indicator)
    TabLayout mIndicator;

    @BindView(R.id.image_main_pager)
    ViewPager mPager;

    private List<SubTypeFragment> fragList;
    /**
     * 子Frag对应的title
     */
    private List<String> titleList;

    private MainTypeFragAdapter adapter;

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        fragList = new ArrayList<>();
        fragList.add(CollectionGankFrag.newInstance());
        fragList.add(CollectionGankFrag.newInstance());
        fragList.add(CollectionImageFrag.newInstance());
        fragList.add(CollectionImageFrag.newInstance());
        titleList = ResUtils.resToStrList(this,R.array.part);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_collection;
    }

    @Override
    protected void initEvents()
    {
        setTitle("收藏");
        // 初始化 tabLayout  和 viewPager  ，并绑定
        adapter = new MainTypeFragAdapter(getSupportFragmentManager(), fragList,titleList);
        mPager.setOffscreenPageLimit(fragList.size() - 1);
        mPager.setAdapter(adapter);

        mIndicator.setupWithViewPager(mPager);
        mIndicator.setTabMode(TabLayout.MODE_FIXED);
        mIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }



}
