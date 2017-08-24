package demo.yc.joviality.conf;

import android.os.Environment;

import java.io.File;

import demo.yc.lib.utils.LogUtil;

/**
 * @author: YC
 * @date: 2017/8/24 0024
 * @time: 10:26
 * @detail:
 */

public class FilePath
{
    public static String rootPath =
            Environment.getExternalStorageDirectory().getAbsolutePath();

    public static String appPath = rootPath+ File.separator+"YCJoviality";

    public static String downloadPath = appPath+File.separator+"download";

    public static String cachePath = appPath+File.separator+"cache";

    public static void createDir(String path)
    {
        File file = new File(path);
        if(file.exists())
        {
            LogUtil.d("file",file.getAbsolutePath()+"  - is exists");
            return;
        }
        else
        {
            LogUtil.d("file",file.getAbsolutePath()+"  - is not  exists");
            file.mkdirs();
            LogUtil.d("file",file.getAbsolutePath()+"  - is mkdirs");
        }
    }

}
