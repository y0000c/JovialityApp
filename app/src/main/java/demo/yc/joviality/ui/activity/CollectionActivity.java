package demo.yc.joviality.ui.activity;

import android.os.Bundle;

import demo.yc.joviality.ui.activity.base.BaseAppActivity;
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



}
