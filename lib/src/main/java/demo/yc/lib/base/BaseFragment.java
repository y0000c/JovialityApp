package demo.yc.lib.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
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

    protected  static String TAG = null;
    /**
     * 上下文对象
     */
    protected BaseActivity mContext;

    private Unbinder unBind;

    public BaseFragment()
    {
        // Required empty public constructor
    }

    //（1）----------------初始化阶段-------------
   @Override
    public void onAttach(Context context)
    {
        LogUtil.d("life","_onAttach");
        super.onAttach(context);
        mContext = (BaseActivity) context;
    }

    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        initData();
    }

    /**
     * 用来处理getArgument（）传入的参数
     */
    protected abstract void initData();


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

    /**
     * 获取加载的xml文件
     */
    protected abstract int getContentLayoutId();


    // fragment 布局创建成功时生命周期
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        unBind = ButterKnife.bind(this,view);
        initEvents();
    }

    /**
     * 初始化控件和事件
     */
    protected abstract void initEvents();


    @Override
    public void onDestroy()
    {
        LogUtil.d("life",TAG+"_onDestroy");
        endEvent();
        unBind.unbind();
        super.onDestroy();
    }

    protected abstract void endEvent();


















    //********************定义公用方法***********************
//
//    /**
//     * 跳转到指定Activity
//     * @param clazz     目标Activity
//     * @param extras       额外信息
//     */
//    protected void jumpToActivity(Class<?> clazz,Bundle extras)
//    {
//        Intent intent = new Intent(mContext,clazz);
//        if(extras != null)
//            intent.putExtras(extras);
//        startActivity(intent);
//    }
//
//    /**
//     * 跳转到指定Activity，带返回值
//     * @param clazz     目标activity
//     * @param extras    额外信息
//     * @param requestCode   请求码
//     */
//    protected void jumpToActivityForResult(Class<?> clazz,Bundle extras,int requestCode)
//    {
//        Intent intent = new Intent(getActivity(),clazz);
//        if(extras != null)
//            intent.putExtras(extras);
//        startActivityForResult(intent,requestCode);
//    }

    /**
     * 显示toast提示信息
     * @param msg
     */
    protected void showToast(String msg)
    {
        if(!CommonUtil.isEmpty(msg))
            Snackbar.make(mContext.getWindow().getDecorView(),
                    msg,
                    Toast.LENGTH_SHORT).show();
    }


//    // *********************高级用法，封装基本页面******************
//
//    protected abstract View getLoadingTargetView();
//
//    /**
//     * 显示加载过程的页面
//     * @param toggle
//     * @param msg
//     */
//    protected void toggleShowLoading(boolean toggle,String msg)
//    {
//        if(getLoadingTargetView() == null)
//            throw new IllegalArgumentException("there is not target View");
//        if(toggle)
//            controller.showLoading(msg);
//        else
//            controller.restore();
//    }
//
//    /**
//     * 某些异常情况下显示的页面
//     * @param toggle
//     * @param msg
//     * @param listener
//     */
//    protected void toggleShowException(
//            boolean toggle, String msg,View.OnClickListener listener)
//    {
//        if(getLoadingTargetView() == null)
//            throw new IllegalArgumentException("there is not target View");
//        if(toggle)
//            controller.showException(msg,listener);
//        else
//            controller.restore();
//    }
//
//    /**
//     * 网络异常的时候显示页面
//     * @param toggle
//     * @param listener
//     */
//    protected void toggleShowNetError(
//            boolean toggle,View.OnClickListener listener)
//    {
//        if(getLoadingTargetView() == null)
//            throw new IllegalArgumentException("there is not target View");
//        if(toggle)
//            controller.showNetError(listener);
//        else
//            controller.restore();
//    }


}
