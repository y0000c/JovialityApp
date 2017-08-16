package demo.yc.lib.utils;

import android.content.Context;

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

}
