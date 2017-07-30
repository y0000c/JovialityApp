package demo.yc.joviality.activity.base;

import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import demo.yc.joviality.mvpview.base.BaseAppView;
import demo.yc.jovialityyc.R;
import demo.yc.lib.activity.BaseActivity;

public abstract class BaseAppActivity extends BaseActivity implements BaseAppView
{

    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutId)
    {
        super.setContentView(layoutId);
        mToolbar = ButterKnife.findById(this, R.id.home_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

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
