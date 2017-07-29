package demo.yc.joviality.mvpmodel;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.CommonUtil;
import demo.yc.lib.utils.LogUtil;

/**
 * MainActivity的数据操作层
 */
public class MainModelImp
{
    /**
     * 获取MainActivity的背景图片们，根据一周七天来决定
     * @return
     */
    public int getBackImageId()
    {
        // 七天不同的背景图片
        int[] allImageID = {
                R.drawable.night,
                R.drawable.night,
                R.drawable.night,
                R.drawable.night,
                R.drawable.night,
                R.drawable.night,
                R.drawable.night
        };

        int day = CommonUtil.getDayOfWeek();
        if (day >= allImageID.length || day < 0)
            return allImageID[0];
        return allImageID[day];

    }

    /**
     * 获取每天的寄语，根据不同的星期几来决定
     * @param context
     * @return
     */
    public String getDailyMessage(Context context)
    {
        // 获取七天不同的寄语
        String[] allMessages = context.getResources().getStringArray(R.array.main_daily_message);

        LogUtil.d("day","获取所有寄语长度:"+allMessages.length);
        int day = CommonUtil.getDayOfWeek();
        if (day >= allMessages.length || day < 0)
            return "happy every day";
        return allMessages[day];

    }

    /**
     * 获取MainActivity 的背景动画
     * @param context
     * @return
     */
    public Animation getImageAnimation(Context context)
    {
        return AnimationUtils.loadAnimation(context,R.anim.main);
    }
}
