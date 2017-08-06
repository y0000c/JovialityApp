package demo.yc.lib.netstatus;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 封装网络状态的工具类
 */
public class NetUtils
{
    /**
     * 枚举网络状态
     */
    public enum NetType{
        WIFI,MOBILE,NONE
    }

    /**
     * 判断当前网络是否连接（连接了不一定可用）
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info != null)
            return true;
        return false;
    }

    /**
     * 判断当前网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info != null && info.isAvailable())
            return true;
        return false;
    }

    /**
     * 返回当前网络的类型
     * @param context
     * @return
     */
    public static NetType getNetType(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info == null)
            return NetType.NONE;
        else if(info.getType() == ConnectivityManager.TYPE_MOBILE)
            return NetType.MOBILE;
        else if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return NetType.WIFI;
        else
            return NetType.NONE;

    }



}
