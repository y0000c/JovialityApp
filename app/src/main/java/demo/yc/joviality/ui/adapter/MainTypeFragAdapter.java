package demo.yc.joviality.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import demo.yc.joviality.ui.fragment.base.SubTypeFragment;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class MainTypeFragAdapter extends FragmentPagerAdapter
{
    private List<String> titleList;
    private List<SubTypeFragment> fragList;
    public MainTypeFragAdapter(FragmentManager fm
            , List<SubTypeFragment>fragList,List<String> titleList)
    {
        super(fm);
        this.titleList = titleList;
        this.fragList = fragList;
    }
    @Override
    public Fragment getItem(int position)
    {
        return fragList.get(position);
    }

    @Override
    public int getCount()
    {
        if(titleList != null)
            return titleList.size();
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titleList.get(position);
    }
}
