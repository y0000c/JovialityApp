package demo.yc.lib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import demo.yc.lib.utils.ActivityUtils;
import demo.yc.lib.utils.LogUtil;

/**
 * 最外层的Activity基类，用来封装一些与业务逻辑无关的代码
 * 将onCreate的生命周期细分为几个部分，便于处理和阅读
 */
public abstract class BaseActivity extends AppCompatActivity
{
    /**
     * 用来打印信息
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        LogUtil.d(TAG,"onCreate");

        // 将当前Activity添加到管理链表中
        ActivityUtils.newInstance().addActivity(this);

        // 如果上一个Activity传递过来的信息不为空
        Bundle extras = getIntent().getExtras();
        if(extras != null)
            getBundleExtras(extras);

        // 显示当前Activity的布局
        if(getLayoutId() != 0)
        {
            setContentView(getLayoutId());

        }
        else
            throw new IllegalArgumentException("invalid layout id");

        //初始化事件
        initEvents();


    }


    @Override
    public void finish()
    {
        super.finish();
        ActivityUtils.newInstance().removeActivity(this);
        LogUtil.d(TAG,"finish");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        LogUtil.d(TAG,"onDestroy");
    }

    @Override
    public void setContentView(int layoutId)
    {
        super.setContentView(layoutId);
        ButterKnife.bind(this);
    }

    /**
     * 接收上一个Activity传递过来的信息
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 获取当前的activity要显示的布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化一些相关操作事件events
     */
    protected abstract void initEvents();

    /**
     * 跳转到指定activity,不带额外信息
     * @param clazz
     */
    protected void jumpToActivity(Class<?> clazz,boolean isKill)
    {
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
        if(isKill)
            finish();
    }

    /**
     * 跳转到指定activity,携带额外信息
     * @param clazz
     */
    protected void jumpToActivity(Class<?> clazz,Bundle extras,boolean isKill)
    {
        Intent intent = new Intent(this,clazz);
        if(extras != null)
            intent.putExtras(extras);
        startActivity(intent);
        if(isKill)
            finish();
    }





}
