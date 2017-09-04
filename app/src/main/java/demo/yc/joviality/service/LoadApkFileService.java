package demo.yc.joviality.service;

import android.app.IntentService;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

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

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            for(String item:Const.SKIN_PLUGIN_APK)
            {
                File file = getFileStreamPath(item);
                if(!file.exists())
                    loadFile(file,item);
                else
                    LogUtil.w("file","file is exists--"+file.getAbsolutePath());
            }
        }
    }

    private void loadFile(File file,String item)
    {
        FileOutputStream fos = null;
        InputStream is = null;
        try
        {
            fos = new FileOutputStream(file);
            is = getResources().getAssets().open(item);
            int len = -1;
            byte[] buf = new byte[1024];
            while((len = is.read(buf))!=-1)
                fos.write(buf,0,len);

            LogUtil.w("file","load apk file is ok"+file.getAbsolutePath());
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
