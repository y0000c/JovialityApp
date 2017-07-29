package demo.yc.lib.utils;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理每个activity的链表。用于安全退出
 *
 */
public class ActivityUtils
{
    /**
     * 静态变量，还没实例化
     */
    private static ActivityUtils instance= null;

    /**
     * 用来存储每个Activity的链表
     */
    private List<Activity> list = new ArrayList<>();

    /**
     * 利用单例模式，二层判断的同步单例模式
     * @return
     */
    public static ActivityUtils newInstance()
    {
        if(instance == null)
        {
            synchronized (ActivityUtils.class)
            {
                if(instance == null)
                    instance = new ActivityUtils();
            }
        }
        return instance;
    }

    /**
     * 添加新的Activity到链表中
     * @param a
     */
    public synchronized void addActivity(Activity a)
    {
        list.add(a);
    }

    /**
     * 移除某个Activity
     * @param a
     */
    public synchronized void removeActivity(Activity a)
    {
        if(list.contains(a))
            list.remove(a);
    }

    /**
     * 清除所有的activity
     */
    public synchronized void clearActivity()
    {
        for(Activity a:list)
        {
            if(!a.isFinishing())
                a.finish();
        }
        list.clear();
    }

    /**
     * 返回当前Activity个数
     * @return
     */
    public int size()
    {
        return list.size();
    }
}
