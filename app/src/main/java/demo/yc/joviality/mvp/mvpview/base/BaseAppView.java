package demo.yc.joviality.mvp.mvpview.base;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public interface BaseAppView
{
    /**
     * 显示加载框
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * 显示某些异常框
     * @param msg
     */
    void showException(String msg);

    /**
     * 显示网络异常框
     */
    void showNetError();

}
