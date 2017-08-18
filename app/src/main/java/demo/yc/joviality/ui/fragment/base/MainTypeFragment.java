package demo.yc.joviality.ui.fragment.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.yc.joviality.ui.adapter.MainTypeFragAdapter;
import demo.yc.joviality.ui.fragment.GanksListFragment;
import demo.yc.joviality.ui.fragment.ImageListFragment;
import demo.yc.joviality.ui.fragment.NewsListFragment;
import demo.yc.joviality.ui.fragment.VideoListFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseFragment;
import demo.yc.lib.utils.LogUtil;
import demo.yc.lib.utils.ResUtils;

/**
 *
 */
public class MainTypeFragment extends BaseFragment
{

    /**
     * 参数传入的tag
     */
    private static final String MAIN_TYPE = "type";
    @BindView(R.id.image_main_indicator)
    TabLayout mIndicator;
    Unbinder unbinder;
    /**
     * 记录当前的frag代表的模块 type
     */
    private String currentType;
    /**
     * 当前模块type 下的子Frag
     */
    private List<SubTypeFragment> fragList;
    /**
     * 子Frag对应的title
     */
    private List<String> titleList;

    private MainTypeFragAdapter adapter;

    @BindView(R.id.image_main_pager)
    ViewPager mPager;


    public static MainTypeFragment newInstance(String type)
    {

        MainTypeFragment fragment = new MainTypeFragment();
        Bundle args = new Bundle();
        args.putString(MAIN_TYPE, type);
        fragment.setArguments(args);
        LogUtil.d("fragment",TAG+"--"+type);
        return fragment;
    }

    @Override
    protected void initData()
    {
        if (getArguments() == null)
        {
            LogUtil.d("fragment","arguments is null");
            return;
        }

        currentType = getArguments().getString(MAIN_TYPE);
        fragList = new ArrayList<>();

        LogUtil.d("type","当前type--"+currentType);
        if (currentType.equals(ResUtils.resToStr(mContext,R.string.news)))
        {
            titleList = ResUtils.resToStrList(mContext, R.array.news);
            for (String item : titleList)
                fragList.add(NewsListFragment.newInstance(item));
        } else if (currentType.equals(ResUtils.resToStr(mContext,R.string.image)))
        {
            titleList = ResUtils.resToStrList(mContext, R.array.images);
            for (String item : titleList)
                fragList.add(ImageListFragment.newInstance(item));
        } else if (currentType.equals(ResUtils.resToStr(mContext,R.string.video)))
        {
            titleList = ResUtils.resToStrList(mContext, R.array.videos);
            for (String item : titleList)
                fragList.add(VideoListFragment.newInstance(item));
        }
        else if (currentType.equals(ResUtils.resToStr(mContext,R.string.gank)))
        {
            titleList = ResUtils.resToStrList(mContext, R.array.ganks);
            for (String item : titleList)
                fragList.add(GanksListFragment.newInstance(item));
        }

    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_main_type;
    }


    @Override
    protected void initEvents()
    {
        // 初始化 tabLayout  和 viewPager  ，并绑定
        adapter = new MainTypeFragAdapter(getChildFragmentManager(), fragList,titleList);
        mPager.setOffscreenPageLimit(fragList.size() - 1);
        mPager.setAdapter(adapter);

        mIndicator.setupWithViewPager(mPager);
        mIndicator.setTabMode(TabLayout.MODE_SCROLLABLE);
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }
}
