package demo.yc.joviality.mvp.mvppresenter.base;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:34
 */

public interface ImageListPresenter
{
    void loadListData(String requestTag,int page);

    void onItemClick();
}
