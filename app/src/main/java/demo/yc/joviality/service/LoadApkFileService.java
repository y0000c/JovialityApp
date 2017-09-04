package demo.yc.joviality.service;

import android.app.IntentService;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.SkinEntity;
import demo.yc.joviality.gen.SkinEntityDao;
import demo.yc.lib.skin.config.Const;
import demo.yc.lib.utils.LogUtil;


public class LoadApkFileService extends IntentService
{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public LoadApkFileService(String name)
    {
        super(name);
    }

    public LoadApkFileService()
    {
        this("LoadAPKFile");
    }

    private SkinEntityDao dao = MyApp.getDaoSession().getSkinEntityDao();

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            for(int i=0;i<Const.SKIN_APK_LIST.length;i++)
            {
                File file = getFileStreamPath(Const.SKIN_APK_LIST[i]);
                if(!file.exists())
                    loadFile(file,i);
                else
                    LogUtil.w("file","file is exists--"+file.getAbsolutePath());
            }
        }
    }

    private void loadFile(File file,int index)
    {
        FileOutputStream fos = null;
        InputStream is = null;
        try
        {
            fos = new FileOutputStream(file);
            is = getResources().getAssets().open(Const.SKIN_APK_LIST[index]);
            int len = -1;
            byte[] buf = new byte[1024];
            while((len = is.read(buf))!=-1)
                fos.write(buf,0,len);

            LogUtil.w("file","load apk file is ok"+file.getAbsolutePath());
            dao.insert(new SkinEntity(null
                    ,Const.SKIN_APK_LIST[index]
                    ,Const.SKIN_PCK_LIST[index]
                    ,Const.SKIN_COLOR_LIST[index]
                    ,Const.SKIN_TYPE_LIST[index]));
        }catch (Exception e)
        {
            e.printStackTrace();
            file.delete();
        }finally
        {
            try
            {
                fos.close();
                is.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
