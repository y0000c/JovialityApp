package demo.yc.joviality.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import demo.yc.joviality.fragment.partofimage.ImageListFragment;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class ImageMainFragAdapter extends FragmentPagerAdapter
{
    private List<String> list;

    public ImageMainFragAdapter(FragmentManager fm,List<String> list)
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
