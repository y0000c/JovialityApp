package demo.yc.joviality.interfaces;

import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 23:31
 * @detail:
 */

public interface IListDataCallback<T>
{
    void onSuccess(List<T> data);

    void onError(String msg);

}
