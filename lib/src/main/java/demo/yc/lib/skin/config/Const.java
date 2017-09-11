package demo.yc.lib.skin.config;

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

    public static final String SKIN_PREFIX = "skin_";

    public static final String SKIN_RED_APK = "skin_red.apk";
    public static final String SKIN_RED_PACK = "demo.yc.skinpluginres";

    public static final String SKIN_PURPLE_APK = "skin_purple.apk";
    public static final String SKIN_PURPLE_PACK = "demo.yc.skinpluginpurple";


    public static final String SKIN_GREEN_APK = "skin_green.apk";
    public static final String SKIN_GREEN_PACK = "demo.yc.skinplugingreen";

    public static final String SKIN_BLACK_APK = "skin_black.apk";
    public static final String SKIN_BLACK_PACK = "demo.yc.skinpluginblack";


    public static final int SKIN_TYPY_GREEN = 111;
    public static final int SKIN_TYPY_RED = 112;
    public static final int SKIN_TYPY_PURPLE = 113;
    public static final int SKIN_TYPY_BLACK = 114;
    public static final int SKIN_TYPY_RESET = 115;


    public static final String RED_COLOR = "#e13c3c";
    public static final String GREEN_COLOR = "#5fbbb0";
    public static final String PURPLE_COLOR = "#c256c4";
    public static final String BLACK_COLOR = "#646464";
    public static final String BLUE_COLOR = "#57bfdc";


    public static final String APK_KEY = "apk_path";
    public static final String PCK_KEY = "pck_name";


    public static final String[] SKIN_APK_LIST = {SKIN_RED_APK,SKIN_GREEN_APK,SKIN_PURPLE_APK,SKIN_BLACK_APK};
    public static final String[] SKIN_PCK_LIST = {SKIN_RED_PACK,SKIN_GREEN_PACK,SKIN_PURPLE_PACK,SKIN_BLACK_PACK};
    public static final Integer[] SKIN_TYPE_LIST = {SKIN_TYPY_RED,SKIN_TYPY_GREEN,SKIN_TYPY_PURPLE,SKIN_TYPY_BLACK};
    public static final String[] SKIN_COLOR_LIST = {RED_COLOR,GREEN_COLOR,PURPLE_COLOR,BLACK_COLOR};
    private static final String RED_NAME = "火焰红";
    private static final String GREEN_NAME = "草原绿";
    private static final String PURPLE_NAME = "炫酷紫";
    private static final String BLACK_NAME = "夜间模式";
    public static final String RESET_NAME ="天空蓝（默认）";
    public static final String[] SKIN_NAME_LIST = {RED_NAME,GREEN_NAME,PURPLE_NAME,BLACK_NAME};
}
