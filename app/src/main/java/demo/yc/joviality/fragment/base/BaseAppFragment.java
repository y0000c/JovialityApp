package demo.yc.joviality.fragment.base;


import android.support.v4.app.Fragment;

import demo.yc.joviality.mvpview.base.BaseAppView;
import demo.yc.lib.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseAppFragment extends BaseFragment implements BaseAppView
{
    @Override
    public void showLoading(String msg)
    {
        toggleShowLoading(true,msg);
    }

    @Override
    public void hideLoading()
    {

        toggleShowLoading(false,"");
    }

    @Override
    public void showException(String msg)
    {
        toggleShowException(true,msg,null);
    }

    @Override
    public void showNetError()
    {
        toggleShowNetError(true,null);
    }
}
