package demo.yc.joviality.activity;

import android.os.Bundle;
import android.view.View;

import demo.yc.joviality.activity.base.BaseAppActivity;
import demo.yc.jovialityyc.R;

public class CollectionActivity extends BaseAppActivity
{


    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_collection;
    }

    @Override
    protected void initEvents()
    {
        setTitle("收藏");
    }

    @Override
    protected View getLoadingTargetView()
    {
        return null;
    }
}
