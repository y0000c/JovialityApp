package demo.yc.joviality.mvp.mvpview;

import java.util.List;

import demo.yc.joviality.mvp.mvpview.base.BaseAppView;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:01
 */

public interface FragListView<T> extends BaseAppView
{
    void onSuccess(List<T> imageList);
}
