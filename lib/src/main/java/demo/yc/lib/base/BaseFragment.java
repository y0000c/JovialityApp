package demo.yc.lib.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import demo.yc.lib.loading.ChangeViewController;
import demo.yc.lib.utils.CommonUtil;
import demo.yc.lib.utils.LogUtil;

/**
 * 类似BaseActivity一样，对使用的fragment进行基类的封装,因此某些东西基本是一样的
 * 定义公用的方法和将fragment的生命周期划分为容易理解的阶段,以及高级用法
 * （1）划分阶段：初始化，可见，不可见，销毁
 * （2）公用方法：显示toast,dialog，页面跳转之类的
 * （3）高级用法：封装基本页面（错误时，异常时，网络失败时要显示的页面）
 */
public abstract class BaseFragment extends Fragment
{

    protected  static  String TAG = null;

    /**
     * 上下文对象
     */
    protected Context mContext;
    /**
     * 是否第一次显示
     */
    protected boolean isFisrtVisible = true;
    /**
     * 是否第一次消失
     */
    private boolean isFisrtInvisible = true;
    /**
     * 是否已经完全初始化
     */
    private boolean isPrepared = false;

    /**
     * 控制页面替换
     */
    private ChangeViewController controller;

    public BaseFragment()
    {
        // Required empty public constructor
    }

    // ******************划分生命周期**********************

    //（1）----------------初始化阶段-------------
   @Override
    public void onAttach(Context context)
    {
        LogUtil.d("life","_onAttach");
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    // fragment 创建布局时期的阶段
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        LogUtil.d("life",TAG+"_onCreateView");

        if(getContentLayoutId() != 0)
            return inflater.inflate(getContentLayoutId(),null);
        else
            return super.onCreateView(inflater,container,savedInstanceState);
    }

    // fragment 布局创建成功时生命周期
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        LogUtil.d("life",TAG+"_onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        LogUtil.d("life",view == null ? "view is null":"not  null");
        ButterKnife.bind(this,view);

        if(getLoadingTargetView() != null)
            controller = new ChangeViewController(getLoadingTargetView());

        initEvents();
    }

    /**
     * 获取加载的xml文件
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件和事件
     */
    protected abstract void initEvents();

    // (2)-----------可见阶段------------------

    //frg创建成功时期
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        LogUtil.d("life",TAG+"_onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    /**
     * 判断有没有初始化，如果已经初始化了，那就直接显示界面
     * 否则，就更改为已经初始化了，
     * 因为最开始调用该方法的是onActivityCreated,表示已经初始化完了
     * 一次加载多个frag,需要进行同步操作
     */
    private synchronized void initPrepare()
    {
        LogUtil.d("life",TAG+"_initPrepare");
        if(isPrepared)
            onFirstUserVisible();
        else
            isPrepared = true;
    }

    // 用来设置当前的view是否可见，可以用来判断frag的当前状态
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        LogUtil.d("life",TAG+"_setUserVisibleHint"+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            if(isFisrtVisible)
            {
                isFisrtVisible = false;
                initPrepare();
            }else
                onUserVisible();
        }else
        {
            if(isFisrtInvisible)
            {
                isFisrtInvisible = false;
                onFirstUserVisible();
            }else
                onUserInVisible();
        }
    }

    /**
     * 第一次显示界面，方便进行一些数据初始化化操作
     */
    protected abstract void onFirstUserVisible();

    /**
     * 非第一次显示界面，相当于activity的onresume阶段
     */
    protected abstract void onUserVisible();



    // (3)-----------不可见阶段----------------
    /**
     * 第一次界面不可见（一般是不做什么任何处理的）
     */
    protected abstract void onFirstUserInvisible();

    /**
     * 非第一次界面不可见，相当于是onPause阶段
     */
    protected abstract void onUserInVisible();

    // (4)-----------销毁阶段-------------------

    @Override
    public void onDestroyView()
    {
        LogUtil.d("life",TAG+"_onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        LogUtil.d("life",TAG+"_onDestroy");
        super.onDestroy();
    }

    //********************定义公用方法***********************

    /**
     * 跳转到指定Activity
     * @param clazz     目标Activity
     * @param extras       额外信息
     */
    protected void jumpToActivity(Class<?> clazz,Bundle extras)
    {
        Intent intent = new Intent(getActivity(),clazz);
        if(extras != null)
            intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * 跳转到指定Activity，带返回值
     * @param clazz     目标activity
     * @param extras    额外信息
     * @param requestCode   请求码
     */
    protected void jumpToActivityForResult(Class<?> clazz,Bundle extras,int requestCode)
    {
        Intent intent = new Intent(getActivity(),clazz);
        if(extras != null)
            intent.putExtras(extras);
        startActivityForResult(intent,requestCode);
    }

    /**
     * 显示toast提示信息
     * @param msg
     */
    protected void showToast(String msg)
    {
        if(!CommonUtil.isEmpty(msg))
            Snackbar.make(getActivity().getWindow().getDecorView(),
                    msg,
                    Toast.LENGTH_SHORT).show();
    }


    // *********************高级用法，封装基本页面******************

    protected abstract View getLoadingTargetView();

    /**
     * 显示加载过程的页面
     * @param toggle
     * @param msg
     */
    protected void toggleShowLoading(boolean toggle,String msg)
    {
        if(getLoadingTargetView() == null)
            throw new IllegalArgumentException("there is not target View");
        if(toggle)
            controller.showLoading(msg);
        else
            controller.restore();
    }

    /**
     * 某些异常情况下显示的页面
     * @param toggle
     * @param msg
     * @param listener
     */
    protected void toggleShowException(
            boolean toggle, String msg,View.OnClickListener listener)
    {
        if(getLoadingTargetView() == null)
            throw new IllegalArgumentException("there is not target View");
        if(toggle)
            controller.showException(msg,listener);
        else
            controller.restore();
    }

    /**
     * 网络异常的时候显示页面
     * @param toggle
     * @param listener
     */
    protected void toggleShowNetError(
            boolean toggle,View.OnClickListener listener)
    {
        if(getLoadingTargetView() == null)
            throw new IllegalArgumentException("there is not target View");
        if(toggle)
            controller.showNetError(listener);
        else
            controller.restore();
    }


}
