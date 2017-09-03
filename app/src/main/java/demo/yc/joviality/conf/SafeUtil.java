package demo.yc.joviality.conf;

import demo.yc.lib.utils.CommonUtil;

/**
 * @author: YC
 * @date: 2017/8/30 0030
 * @time: 18:20
 * @detail:
 */

public class SafeUtil
{
    private static SafeUtil util;
    static
    {
        System.loadLibrary("native-lib");
        util = new SafeUtil();
    }

    private String password;

    public  synchronized String getPassword()
    {
        if(CommonUtil.isEmpty(password))
        {
            password = getURLPassword();
        }
        return password;
    }


    public static SafeUtil getInstance()
    {
        return util;
    }

    public native String getURLPassword();
}
