package demo.yc.joviality.mvpmodel;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.fragment.partofimage.ImageMainFragment;
import demo.yc.joviality.fragment.partofnews.NewsMainFragment;
import demo.yc.joviality.fragment.partofvideo.VideoMainFragment;
import demo.yc.jovialityyc.R;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

/**
 * HomeActivity 对应的数据操作类
 */
public class HomeModelImp
{
    /**
     * 返回HomeActivity中viewpager所使用的fragments
     * @return
     */
    public List<Fragment> getPagerFragment()
    {
        List<Fragment> list = new ArrayList<>();
        list.add(new NewsMainFragment());
        list.add(new ImageMainFragment());
        list.add(new VideoMainFragment());
        return list;
    }


    /**
     * 获取侧滑菜单图片背景
     * @param context
     * @return
     */
    public int getMainImageId(Context context)
    {
        return R.drawable.morning;
    }
}
