package demo.yc.joviality;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import demo.yc.joviality.gen.DaoMaster;
import demo.yc.joviality.gen.DaoSession;

/**
 * @author: YC
 * @date: 2017/8/17 0017
 * @time: 12:34
 * @detail:
 */

public class MyApp extends Application
{
    private static Context mContext;

    private SQLiteDatabase db;
    private DaoMaster master;
    private static DaoSession session;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = getApplicationContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"my_db",null);
        db = helper.getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
    }

    public static Context getContext()
    {
        return mContext;
    }
    public static DaoSession getDaoSession()
    {
        return session;
    }
}
