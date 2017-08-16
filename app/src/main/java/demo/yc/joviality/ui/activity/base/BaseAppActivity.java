package demo.yc.joviality.ui.activity.base;

import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseActivity;

public abstract class BaseAppActivity extends BaseActivity
{

    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutId)
    {
        super.setContentView(layoutId);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
