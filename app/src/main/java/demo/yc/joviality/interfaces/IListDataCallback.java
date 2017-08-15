package demo.yc.joviality.interfaces;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 23:31
 * @detail:
 */

public interface IListDataCallback<T>
{
    void onSuccess(int eventCode,T data);

    void onError(String msg);

    void onException(String msg);
}
