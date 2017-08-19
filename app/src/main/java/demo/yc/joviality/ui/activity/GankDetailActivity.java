package demo.yc.joviality.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import demo.yc.joviality.entity.ResponseGankEntity;
import demo.yc.joviality.ui.activity.base.BaseDetailActivity;
import demo.yc.jovialityyc.R;

public class GankDetailActivity extends BaseDetailActivity
{

    @BindView(R.id.gank_detail_layout)
    ViewGroup layout;
    @BindView(R.id.gank_detail_progress)
    ProgressBar mProgress;
    @BindView(R.id.gank_detail_web)
    WebView mWebView;

    private ResponseGankEntity.ResultsBean gankItem;

    public static final String GANK_TAG = "gank_detail";
    private static final int OPEN_OTHER = 0x2222;

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        gankItem = (ResponseGankEntity.ResultsBean) extras.getSerializable(GANK_TAG);
        if (gankItem == null)
            finish();
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_gank_detail;
    }

    @Override
    protected void initEvents()
    {
        setTitle("加载中...");

        final WebSettings settings = mWebView.getSettings();
        // 支持JavaScript
        settings.setJavaScriptEnabled(true);
//        //窗口大小
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        // 自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 不禁止加载图片
        settings.setBlockNetworkImage(false);
        // 启动dom缓存
        settings.setDomStorageEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress)
            {
                super.onProgressChanged(view, newProgress);
                mProgress.setProgress(newProgress);
                if(newProgress >= 100)
                {
                    mProgress.setVisibility(View.GONE);
                    setTitle(gankItem.getDescX());
                }
            }

        });


        mWebView.loadUrl(gankItem.getUrlX());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, OPEN_OTHER, 1, "其他应用打开");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == OPEN_OTHER)
        {
            otherOpen();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void otherOpen()
    {

    }

    @Override
    public Object getDoneData()
    {
        return gankItem;
    }

    @Override
    public void finish()
    {
        layout.removeView(mWebView);
        mWebView.destroy();
        super.finish();
    }

}
