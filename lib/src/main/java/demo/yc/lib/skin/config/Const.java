package demo.yc.lib.skin.config;

import android.os.Environment;

import java.io.File;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 10:30
 * @detail:
 */

//private String skinPluginApkPath =
//        Environment.getExternalStorageDirectory()+
//        File.separator+"skin_plugin.apk";
//private String skinPluginPackName = "demo.yc.skinpluginres";
public class Const
{
    private static final String ROOT =
            Environment.getExternalStorageDirectory().getAbsolutePath();



    public static final String SKIN_PREFIX = "skin_";

    public static final String SKIN_RED_APK = ROOT + File.separator + "skin_red.apk";
    public static final String SKIN_RED_PACK = "demo.yc.skinpluginres";

    public static final String SKIN_GREEN_APK = ROOT + File.separator + "skin_green.apk";
    public static final String SKIN_GREEN_PACK = "demo.yc.skinplugingreen";

    public static final int SKIN_TYPY_RESET = 110;
    public static final int SKIN_TYPY_GREEN = 111;
    public static final int SKIN_TYPY_RED = 112;

}
