package demo.yc.lib.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.yc.lib.R;
import demo.yc.lib.loading.ChangeViewController;
import demo.yc.lib.utils.ActivityUtils;
import demo.yc.lib.utils.LogUtil;

/**
 * 最外层的Activity基类，用来封装一些与业务逻辑无关的代码
 * 将onCreate的生命周期细分为几个部分，便于处理和阅读
 */
public abstract class BaseActivity extends BaseSkinActivity
{

    /**
     * ButterKnife 绑定
     */
    protected Unbinder unbinder;
    /**
     * 用来打印信息
     */
    protected static String TAG ;

    /**
     * 控制页面替换
     */
    protected ChangeViewController controller;

    // *******************划分生命周期阶段**************
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();
        LogUtil.d(TAG,"onCreate");

        //状态栏
        initWindow();

        // 将当前Activity添加到管理链表中
        ActivityUtils.getInstance().addActivity(this);

        // 如果上一个Activity传递过来的信息不为空
        Bundle extras = getIntent().getExtras();
        getBundleExtras(extras);

        // 显示当前Activity的布局
        if(getLayoutId() != 0)
            setContentView(getLayoutId());
        else
            throw new IllegalArgumentException("invalid layout id");

        //初始化事件
        initEvents();

    }

    /**
     * 4.4及以上透明状态栏
     */
    private void initWindow(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//        {//5.0 全透明状态栏
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }else
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {//4.4 全透明状态栏
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    public void setContentView(int layoutId)
    {
        super.setContentView(layoutId);
        unbinder = ButterKnife.bind(this);
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


    @Override
    public void finish()
    {
        super.finish();
        ActivityUtils.getInstance().removeActivity(this);
        LogUtil.d("life","finish--"+TAG+"--"+this.toString());
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind();
        LogUtil.d("life","onDestroy--"+TAG+"--"+this.toString());
    }
    // ***********************定义公用方法阶段*****************
    /**
     * 跳转到指定activity
     * @param clazz         目标activity
     * @param extras       额外信息
     * @param isKill      是否结束当前activity
     */
    public void jumpToActivity(Class<?> clazz,Bundle extras,boolean isKill)
    {
        Intent intent = new Intent(this,clazz);
        if(extras != null)
            intent.putExtras(extras);

        startActivity(intent);
        overridePendingTransition(R.anim.open,0);
        if(isKill)
            finish();
    }

    /**
     * 跳转到指定的Activity，带返回值
     * @param clazz     目标activity
     * @param extras       额外信息
     * @param requestCode      请求码
     */
    protected void jumpToActivityForResult(Class<?> clazz,Bundle extras,int requestCode)
    {
        Intent intent = new Intent(this,clazz);
        if(extras != null)
            intent.putExtras(extras);
        startActivityForResult(intent,requestCode);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
