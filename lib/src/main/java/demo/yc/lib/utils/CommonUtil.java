package demo.yc.lib.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 集合了多种常用的简单实用的工具函数
 */

public class CommonUtil
{
    /**
     * 判断一个字符串是否为空或者是空格之类的
     * @param msg
     * @return
     */
    public static boolean isEmpty(String msg)
    {
        // 利用自带工具类判断非空 然后正则匹配空格
        if(TextUtils.isEmpty(msg) || TextUtils.isEmpty(msg.trim()))
            return true;
        return false;
    }

    /**
     * 返回现在是星期几
     * 数组{1,2,3,4,5,6,7}中用1~7来表示：
     * 星期天，星期一，星期二，星期三，星期四，星期五，星期六
     * 结果对应数组，从0开始，因此结果减去1
     *
     * @return
     */
    public static int getDayOfWeek()
    {
        Calendar calendar = Calendar.getInstance();
        LogUtil.d("day","获取当前星期几:"+(calendar.get(Calendar.DAY_OF_WEEK)-1));
        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }

    public static String getDayTime(int num)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,-num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(calendar.getTimeInMillis());
    }

    public static String getTodayTime()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String getTodayString()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);

    }

}
