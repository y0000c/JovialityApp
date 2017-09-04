package demo.yc.lib.skin.callback;

/**
 * @author: YC
 * @date: 2017/9/3 0003
 * @time: 11:34
 * @detail:
 */

public interface ISkinChangeCallback
{
    void onStart();

    void onError(Exception e);

    void onSuccess();

    ISkinChangeCallback defaultCallBack = new ISkinChangeCallback()
    {
        @Override
        public void onStart()
        {

        }

        @Override
        public void onError(Exception e)
        {

        }

        @Override
        public void onSuccess()
        {

        }
    };
}
