package demo.yc.joviality.activity.base;

import demo.yc.joviality.mvpview.base.BaseAppView;
import demo.yc.lib.activity.BaseActivity;

public abstract class BaseAppActivity extends BaseActivity implements BaseAppView
{

//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public void setContentView(int layoutResID)
//    {
//        super.setContentView(layoutResID);
//    }



    @Override
    public void showLoading(String msg)
    {

    }

    @Override
    public void hideLoading()
    {

    }

    @Override
    public void showException(String msg)
    {

    }

    @Override
    public void showNetError()
    {

    }
}
