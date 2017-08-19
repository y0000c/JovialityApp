package demo.yc.joviality.ui.activity.base;

import android.view.Menu;
import android.view.MenuItem;

import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;

public abstract class BaseDetailActivity extends BaseAppActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.detail_menu_share:
                share();
                break;
            case R.id.detail_menu_collect:
                collect();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share()
    {
        LogUtil.d("menu","share----");
    }

    private void collect()
    {
        LogUtil.d("menu","collect----");
    }

    protected void download()
    {
        LogUtil.d("menu","download----");
    }

    public abstract Object getDoneData();

}


