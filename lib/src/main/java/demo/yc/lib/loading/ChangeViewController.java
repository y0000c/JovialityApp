package demo.yc.lib.loading;

import android.view.View;
import android.widget.TextView;

import demo.yc.lib.R;
import demo.yc.lib.utils.CommonUtil;

/**
 * 给各种frag,activity调用方法
 * 用来表示各种不用情况下所要替换的布局内容
 * （网络问题，加载过程，程序错误等情况的界面）
 * 实质上就是利用ChangeViewImp来实现的
 * 不知道这是不是某种设计模式的思想
 */

public class ChangeViewController
{
    /**
     *  实质上就是完成页面替换的帮助类
     */
    private IChangeView helper;

    public ChangeViewController(View view)
    {
        this(new ChangeViewImp(view));
    }

    public ChangeViewController(IChangeView helper)
    {
        this.helper = helper;
    }

    /**
     * 显示加载过程的页面
     * @param msg
     */
    public void showLoading(String msg)
    {
        View layout = helper.inflate(R.layout.common_loading);
        if(!CommonUtil.isEmpty(msg))
        {
            TextView textView = layout.findViewById(R.id.loading_text);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }

    /**
     * 显示其他异常情况下的页面
     * @param msg
     */
    public void showException(String msg,View.OnClickListener listener)
    {
        View layout = helper.inflate(R.layout.common_exception);
        if(listener != null)
            layout.setOnClickListener(listener);
        helper.showLayout(layout);
    }

    /**
     * 显示网络错误页面
     * @param listener
     */
    public void showNetError(View.OnClickListener listener)
    {
        showException("",listener);
    }

    /**
     * 恢复原本界面
     */
    public void restore()
    {
        helper.restoreView();
    }
}
