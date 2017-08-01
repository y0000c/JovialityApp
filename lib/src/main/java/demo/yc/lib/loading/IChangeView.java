package demo.yc.lib.loading;

import android.content.Context;
import android.view.View;

/**
 * 在替换页面功能中（加载，网络失败等。。）。定义的相关操作方法的接口
 */

public interface IChangeView
{

    //获取正常情况下加载的view
    View getView();

    //映射异常情况下要加载的布局
    View inflate(int layoutId);

    // 用来获取当前界面是正常情况下还是异常情况下的界面布局
    View getCurrentLayout();

    //显示界面（无论是当前的还是异常的）
    void showLayout(View view);

    //获取上下文对象
    Context getContext();

    // 恢复正常情况下的界面
    void restoreView();





}
