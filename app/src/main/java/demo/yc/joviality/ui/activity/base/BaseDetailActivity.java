package demo.yc.joviality.ui.activity.base;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.GankEntity;
import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.entity.ShareEntity;
import demo.yc.joviality.gen.GankEntityDao;
import demo.yc.joviality.gen.ImageEntityDao;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.LogUtil;
import demo.yc.lib.utils.ToastUtil;

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
        if(getShareData() != null)
        {
            ShareEntity item = getShareData();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if(item.getType() == ShareEntity.TYPE_IMAGE)
            {
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,
                        Uri.fromFile(new File(item.getImagePath())));
            }else if(item.getType() == ShareEntity.TYPE_URL)
            {
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,item.getExtra()+"\r\n"
                        +item.getUrl());
            }else
            {
                return;
            }
            startActivity(Intent.createChooser(intent,"分享到"));
        }
    }

    private void collect()
    {
        LogUtil.d("menu","collect----");
        Object obj = getDoneData();

        if(getDoneData() == null)
            return;

        if(obj instanceof ImageEntity)
        {
            ImageEntity item = (ImageEntity) obj;
            ImageEntityDao dao = MyApp.getDaoSession().getImageEntityDao();
            long count = dao.queryBuilder()
                    .where(ImageEntityDao.Properties.ThumbnailUrl.eq(item.getThumbnailUrl()))
                    .count();
            if(count > 0)
                ToastUtil.getBar(getWindow().getDecorView(),"已经收藏").show();
            else
            {
                if (dao.insert(item) > 0)
                    ToastUtil.getBar(getWindow().getDecorView(), "收藏成功").show();
                else
                    ToastUtil.getBar(getWindow().getDecorView(), "收藏失败").show();
            }
        }else if(obj instanceof GankEntity)
        {
            GankEntity item = (GankEntity) obj;
            GankEntityDao dao = MyApp.getDaoSession().getGankEntityDao();
            long count = dao.queryBuilder()
                    .where(GankEntityDao.Properties.UrlX.eq(item.getUrlX()))
                    .count();
            if(count > 0)
                ToastUtil.getBar(getWindow().getDecorView(),"已经收藏").show();
            else
            {
                if (dao.insert(item) > 0)
                    ToastUtil.getBar(getWindow().getDecorView(), "收藏成功").show();
                else
                    ToastUtil.getBar(getWindow().getDecorView(), "收藏失败").show();
            }
        }
    }

    protected void download()
    {
        LogUtil.d("menu","download----");
    }

    public abstract Object getDoneData();

    public abstract ShareEntity getShareData();
}


