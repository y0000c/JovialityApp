package demo.yc.joviality.mvpview;

import android.view.animation.Animation;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public interface MainView
{
    void showBackImage(int backImageId);

    void showDailyMessage(String msg);

    void showAnimation(Animation animation);

    void startToHomeActivity();
}
