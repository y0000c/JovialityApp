package demo.yc.joviality.mvp.mvppresenter.base;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:34
 */

public interface FragListPresenter<T>{
    void loadListData(int item,String requestTag,int page);

    void loadListData(String id,String name,int page,int days);

    void destroy();
}
