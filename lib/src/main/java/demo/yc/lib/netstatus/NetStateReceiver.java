package demo.yc.lib.netstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.LinkedList;
import java.util.List;

import demo.yc.lib.utils.LogUtil;

/**
 * 处理网络变化的广播接受者
 * 被观察的对象的网络的状态
 * 广播接收者相当于通知其他观察的中介
 * 理解的不是很好
 *
 * 不知道如何注册
 * 暂时发现该方案不可行。。。。暂时忽略
 */

public class NetStateReceiver extends BroadcastReceiver
{
    /**
     * android 网络变化的action
     */
    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";


    /**
     *需要保存所有的观察者
     */
    private static List<NetChangeObserver> observers = new LinkedList<>();

    /**
     * 当前网络是否可用
     */
    private boolean isAvailable = false;

    /**
     * 当前网络的类型
     */
    private NetUtils.NetType mType ;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction().toLowerCase().equals(ANDROID_NET_CHANGE_ACTION))
        {

            if(NetUtils.isNetAvailable(context))
            {
                isAvailable = true;
                mType = NetUtils.getNetType(context);

            }else
            {
                isAvailable = false;
            }
            LogUtil.d("net",isAvailable+"---"+mType);
            notifyObservers();
        }
    }

    /**
     * 注册观察者，添加到观察者列表中
     * @param observer
     */
    public static void registerObserver(NetChangeObserver observer)
    {
        observers.add(observer);
    }

    /**
     * 移除不需要的观察者
     * @param observer
     */
    public static void unRegisterObserver(NetChangeObserver observer)
    {
        observers.remove(observer);
    }

    /**
     * 当被观察的对象发生变化时，就通知所有的观察者
     */
    private void notifyObservers()
    {
        for(NetChangeObserver o:observers)
        {
            LogUtil.d("net",o.toString());
            if(o != null)
            {
                if(isAvailable)
                    o.onNetConnected(mType);
                else
                    o.onNetDisConnected();
            }
        }
    }
}
