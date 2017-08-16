package demo.yc.joviality.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import demo.yc.joviality.ui.fragment.ImageListFragment;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class MainTypeFragAdapter extends FragmentPagerAdapter
{
    private List<String> list;

    public MainTypeFragAdapter(FragmentManager fm, List<String> list)
    {
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int position)
    {
        return new ImageListFragment();
    }

    @Override
    public int getCount()
    {
        if(list != null)
            return list.size();
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return list.get(position);
    }
}
