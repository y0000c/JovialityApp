package demo.yc.lib.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * @author: YC
 * @date: 2017/8/21 0021
 * @time: 22:32
 * @detail:
 */

public class ToastUtil
{
    private static Toast t = null;
    private static Snackbar bar  = null;
    public static Toast getToast(Context c, String con)
    {
        if(c == null)
            throw new IllegalArgumentException("context is non-null");
        if(t != null )
            t.setText(con);
        else
            t = Toast.makeText(c,con,Toast.LENGTH_SHORT);
        return t;
    }

    public static Snackbar getBar(View view, String msg)
    {
        if(view == null)
            throw new IllegalArgumentException("View is non-null");

        return Snackbar.make(view,msg,Toast.LENGTH_SHORT);
    }

}