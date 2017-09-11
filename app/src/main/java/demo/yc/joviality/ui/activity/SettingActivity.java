package demo.yc.joviality.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.joviality.MyApp;
import demo.yc.joviality.conf.FilePath;
import demo.yc.joviality.entity.SkinEntity;
import demo.yc.joviality.gen.SkinEntityDao;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.joviality.ui.adapter.SkinListAdapter;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.listener.IRecyclerItemClickListener;
import demo.yc.lib.skin.SkinManager;
import demo.yc.lib.skin.callback.ISkinChangeCallback;
import demo.yc.lib.skin.config.Const;
import demo.yc.lib.utils.LogUtil;
import demo.yc.lib.utils.ResUtils;

public class SettingActivity extends BaseAppActivity
{


    SkinEntityDao dao = MyApp.getDaoSession().getSkinEntityDao();
    @BindView(R.id.setting_cached_bar)
    ProgressBar mCachedBar;
    @BindView(R.id.setting_cached_text)
    TextView mCachedText;

    ProgressDialog bar;


    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEvents()
    {
        bar = new ProgressDialog(this);
        bar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setTitle(ResUtils.resToStr(this, R.string.setting));
        getCache();
        Log.w("file", "database skin size is " + dao.loadAll().size());
    }

//    public void onClickedGreen()
//    {
//        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_GREEN_APK).getAbsolutePath(), Const.SKIN_GREEN_PACK, new ISkinChangeCallback()
//        {
//            @Override
//            public void onStart()
//            {
//                LogUtil.w("skin", "=========start to change skin========");
//            }
//
//            @Override
//            public void onError(Exception e)
//            {
//                e.printStackTrace();
//                LogUtil.w("skin", "=============change skin error=========== ");
//            }
//
//            @Override
//            public void onSuccess()
//            {
//                LogUtil.w("skin", "============change skin  ok");
//            }
//        });
//    }
//
//    public void onClickedRed()
//    {
//        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_RED_APK).getAbsolutePath(), Const.SKIN_RED_PACK, new ISkinChangeCallback()
//        {
//            @Override
//            public void onStart()
//            {
//                LogUtil.w("skin", "=========start to change skin========");
//            }
//
//            @Override
//            public void onError(Exception e)
//            {
//                e.printStackTrace();
//                LogUtil.w("skin", "=============change skin error=========== ");
//            }
//
//            @Override
//            public void onSuccess()
//            {
//                LogUtil.w("skin", "============change skin  ok");
//            }
//        });
//    }
//
//
//    public void onClickedPurple()
//    {
//        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_PURPLE_APK).getAbsolutePath(), Const.SKIN_PURPLE_PACK, new ISkinChangeCallback()
//        {
//            @Override
//            public void onStart()
//            {
//                LogUtil.w("skin", "=========start to change skin========");
//            }
//
//            @Override
//            public void onError(Exception e)
//            {
//                e.printStackTrace();
//                LogUtil.w("skin", "=============change skin error=========== ");
//            }
//
//            @Override
//            public void onSuccess()
//            {
//                LogUtil.w("skin", "============change skin  ok");
//            }
//        });
//    }
//
//
//    public void onClickedBlack()
//    {
//        SkinManager.getInstance().changeSkin(getFileStreamPath(Const.SKIN_BLACK_APK).getAbsolutePath(), Const.SKIN_BLACK_PACK, new ISkinChangeCallback()
//        {
//            @Override
//            public void onStart()
//            {
//                LogUtil.w("skin", "=========start to change skin========");
//            }
//
//            @Override
//            public void onError(Exception e)
//            {
//                e.printStackTrace();
//                LogUtil.w("skin", "=============change skin error=========== ");
//            }
//
//            @Override
//            public void onSuccess()
//            {
//                LogUtil.w("skin", "============change skin  ok");
//            }
//        });
//    }
//
//
//    public void onClickedReset()
//    {
//        SkinManager.getInstance().reset();
//    }
//

    @OnClick({R.id.setting_skin_layout, R.id.setting_cached_layout})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.setting_skin_layout:
                chooseSkin();
                break;
            case R.id.setting_cached_layout:
                clearCache();
                break;
        }
    }

    private void getCache()
    {
        new AsyncTask<Void, Void, Double>()
        {
            @Override
            protected void onPreExecute()
            {
                mCachedBar.setVisibility(View.VISIBLE);
                mCachedText.setVisibility(View.GONE);
            }

            @Override
            protected Double doInBackground(Void... params)
            {
                File file = new File(FilePath.cachePath);
                Long sum = 0L;
                if(file.exists())
                {
                    for(File file1:file.listFiles())
                    {
                        sum+=file1.length();
                    }
                }
                return sum*1.0/1024/1024;
            }

            @Override
            protected void onPostExecute(Double value)
            {
                DecimalFormat format = new DecimalFormat("0.00");
                mCachedText.setText(format.format(value)+"M");
                mCachedText.setVisibility(View.VISIBLE);
                mCachedBar.setVisibility(View.GONE);
            }
        }.execute();
    }

    private void clearCache()
    {
        new AsyncTask<Void, Void, Boolean>()
        {
            @Override
            protected void onPreExecute()
            {
                mCachedBar.setVisibility(View.VISIBLE);
                mCachedText.setVisibility(View.GONE);
                bar.show();
            }

            @Override
            protected Boolean doInBackground(Void... params)
            {
                File file = new File(FilePath.cachePath);
                if(file.exists())
                {
                    for(File file1:file.listFiles())
                    {
                        file1.delete();
                    }
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean)
            {
                bar.hide();
                mCachedText.setVisibility(View.VISIBLE);
                mCachedBar.setVisibility(View.GONE);
                mCachedText.setText("0.00M");
            }
        }.execute();
    }

    RecyclerView recyclerView;
    SkinListAdapter adapter;
    Dialog dialog;
    boolean isFirst = true;
    private void chooseSkin()
    {
        if(isFirst)
        {
            recyclerView = new RecyclerView(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new SkinListAdapter(this,dao.loadAll(),false);
            recyclerView.setAdapter(adapter);
            adapter.setOnCLickListener(new IRecyclerItemClickListener<SkinEntity>()
            {
                @Override
                public void onItemClick(ViewHolder holder, SkinEntity data, int position)
                {
                    if(data.getType() == Const.SKIN_TYPY_RESET)
                        SkinManager.getInstance().reset();
                    else
                        changeSkin(data.getApkPath(),data.getPckName());
                    dialog.dismiss();
                }
            });

            dialog = new AlertDialog.Builder(this)
                    .setTitle("选择主题")
                    .setView(recyclerView)
                    .create();
            dialog.show();
            isFirst = false;
        }else
        {
            dialog.show();
        }
    }

    private void changeSkin(String apk,String pck)
    {
        SkinManager.getInstance().changeSkin(getFileStreamPath(apk).getAbsolutePath(),pck, new ISkinChangeCallback()
        {
            @Override
            public void onStart()
            {
                LogUtil.w("skin", "=========start to change skin========");
            }

            @Override
            public void onError(Exception e)
            {
                e.printStackTrace();
                LogUtil.w("skin", "=============change skin error=========== ");
            }

            @Override
            public void onSuccess()
            {
                LogUtil.w("skin", "============change skin  ok");
            }
        });
    }
}
