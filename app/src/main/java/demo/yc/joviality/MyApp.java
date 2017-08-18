package demo.yc.joviality;

import android.app.Application;
import android.content.Context;

/**
 * @author: YC
 * @date: 2017/8/17 0017
 * @time: 12:34
 * @detail:
 */

public class MyApp extends Application
{
    private static Context mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext()
    {
        return mContext;
    }
}
