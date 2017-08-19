package demo.yc.lib.utils;

import android.util.Log;

/**
 * 这个是封装打印日志log的工具类
 */

public class LogUtil
{
    /**
     * 判断是否显示log的标识
     */
    private static boolean isShow = true;

    /**
     * 屏蔽log信息
     */
    public static void dismissLog()
    {
        isShow = false;
    }

    /**
     * 显示log信息
     */
    public static void showLog()
    {
        isShow = true;
    }

    /**
     * 用于封装要显示的信息，更加方便，直观
     * @param msg
     * @return
     */
    private static String packagingMessage(
            StackTraceElement stackTraceElement,Object msg)
    {

        StringBuffer sb = new StringBuffer();
        sb.append("[ "+stackTraceElement.getFileName());
        sb.append(" ( ");
        sb.append(stackTraceElement.getLineNumber());
        sb.append(" ) ");
        sb.append(stackTraceElement.getMethodName()+" ]");
        sb.append(" : ");

        if( msg != null && !(msg instanceof String))
            msg.toString();
         sb.append(msg);
        return sb.toString();


    }

    /**
     * 对应 log 的  verbose(详细信息，任何信息。很冗长)
     * @param tag
     * @param msg
     */
    public static void v(String tag,Object msg)
    {
        if(isShow)
        {
            StackTraceElement element =
                    java.lang.Thread.currentThread().getStackTrace()[3];
            Log.v(tag, packagingMessage(element, msg));
        }

    }

    /**
     * 对应 log 的  debug (调试信息)
     * @param tag
     * @param msg
     */
    public static void d(String tag,Object msg)
    {
        if(isShow)
        {
            StackTraceElement element =
                    java.lang.Thread.currentThread().getStackTrace()[3];
            Log.d(tag, packagingMessage(element, msg));
        }
    }

    /**
     * 对应 log 的  info (提示信息)
     * @param tag
     * @param msg
     */
    public static void i(String tag,Object msg)
    {
        if(isShow)
        {
            StackTraceElement element =
                    java.lang.Thread.currentThread().getStackTrace()[3];
            Log.i(tag, packagingMessage(element, msg));
        }
    }

    /**
     * 对应 log 的  warn (警告信息)
     * @param tag
     * @param msg
     */
    public static void w(String tag,Object msg)
    {

        if(isShow)
        {
            StackTraceElement element =
                    java.lang.Thread.currentThread().getStackTrace()[3];
            Log.w(tag, packagingMessage(element, msg));
        }
    }

    /**
     * 对应 log 的  error (错误信息)
     * @param tag
     * @param msg
     */
    public static void e(String tag,Object msg)
    {
        if(isShow)
        {
            StackTraceElement element =
                    java.lang.Thread.currentThread().getStackTrace()[3];
            Log.e(tag, packagingMessage(element, msg));
        }
    }




}
