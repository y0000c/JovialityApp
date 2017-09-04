package demo.yc.joviality.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import demo.yc.joviality.entity.GankEntity;
import demo.yc.joviality.entity.NewsEntity;
import demo.yc.joviality.entity.ShareEntity;
import demo.yc.joviality.ui.activity.base.BaseDetailActivity;
import demo.yc.jovialityyc.R;

public class UrlDetailActivity extends BaseDetailActivity
{
    @BindView(R.id.url_detail_layout)
    ViewGroup layout;
    @BindView(R.id.url_detail_progress)
    ProgressBar mProgress;
    @BindView(R.id.url_detail_web)
    WebView mWebView;

    private Object item;

    public static final String URL_TAG = "url_detail";
    private static final int OPEN_OTHER = 0x2222;

    private boolean isGank = false;
    private GankEntity gankItem;
    private NewsEntity newsItem;

    private String url;
    private String title;
    private String desc;
    @Override
    protected void getBundleExtras(Bundle extras)
    {
        item = extras.getSerializable(URL_TAG);
        if (item == null)
            finish();
        if(item instanceof GankEntity)
        {
            gankItem = (GankEntity) item;
            title = gankItem.getDescX();
            desc = gankItem.getDescX();
            url = gankItem.getUrlX();
        }else
        {
            newsItem = (NewsEntity) item;
            title = newsItem.getTitle();
            desc = newsItem.getTitle();
            url = newsItem.getLink();

        }
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_url_detail;
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
                    setTitle(title);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                view.loadUrl(request.toString());
                return true;
            }
        });

        mWebView.loadUrl(url);

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
        Intent i = new Intent();
        i.setData(Uri.parse(url));
        i.setAction(Intent.ACTION_VIEW);
        startActivity(i);
    }

    @Override
    public Object getDoneData()
    {
        return item;
    }

    @Override
    public ShareEntity getShareData()
    {
        return new ShareEntity(ShareEntity.TYPE_URL
                ,desc,url,"");
    }

    @Override
    public void finish()
    {
        layout.removeView(mWebView);
        mWebView.destroy();
        super.finish();
    }

}
