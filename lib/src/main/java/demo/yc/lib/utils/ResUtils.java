package demo.yc.lib.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/16 0016
 * @time: 22:35
 * @detail:
 */

public class ResUtils
{
    /**
     * 字符串id 到 字符串
     * @param context
     * @param resId
     * @return
     */
    public static String resToStr(Context context,int resId)
    {
        return context.getString(resId);
    }

    /**
     * 字符串集合id  到 字符串集合
     * @param context
     * @param resId
     * @return
     */
    public static List<String> resToStrList(Context context,int resId)
    {
        return Arrays.asList(context.getResources().getStringArray(resId));

    }

    /**
     * 映射xml文件
     * @param context
     * @param layoutId
     * @param parent
     * @return
     */
    public static View inflate(Context context, int layoutId, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(layoutId,parent,false);
    }

}
