package demo.yc.joviality.mvp.mvpview;

import demo.yc.joviality.entity.ResponseImageListEntity;
import demo.yc.joviality.mvp.mvpview.base.BaseAppView;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:01
 */

public interface ImageFragListView extends BaseAppView
{
    void refreshListData(ResponseImageListEntity data);

    void addMoreListData();


}
