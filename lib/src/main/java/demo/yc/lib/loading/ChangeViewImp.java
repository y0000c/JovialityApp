package demo.yc.lib.loading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

/**
 *
 * 替换界面的原理
 * （1）获取正常情况界面的父布局    view
 * （2）创建出需要替换的界面       changeView
 * （3）遍历父布局，找到当先的view对应的下标
 * （4）通过父布局remove(index)当前view,再add(index,changeView)即可完成替换
 *
 * 重点就是（3）（4）
 */
public class ChangeViewImp implements IChangeView
{
    /**
     * 正常情况下的view,因为是不会修改的
     */
    private final View view;

    /**
     * 当前显示的view
     */
    private View currentView;

    /**
     * 正常view在父布局中的位置
     */
    private int index;

    /**
     * 正常view的父布局
     */
    private ViewGroup group;

    /**
     * 父布局的页面参数（添加新页面时，需要传递参数）
     */
    private ViewGroup.LayoutParams params;


    public ChangeViewImp(View view)
    {
        this.view = view;
    }

    /**
     * 找到当先界面在父布局的index,和界面的相关参数params
     */
    private void init()
    {
        // 获取view参数
        params = view.getLayoutParams();
        // 获取view父布局，如果没有直接使用contentView作为父布局
        group = (ViewGroup) view.getParent();
        if(group == null)
            group = view.getRootView().findViewById(android.R.id.content);

        //寻找view的下标
        for(int i=0;i<group.getChildCount();i++)
        {
            if(view == group.getChildAt(i))
            {
                index = i;
                break;
            }
        }
        // 因为是初始化，因此当前界面就是view
        currentView = view;
    }


    @Override
    public View getView()
    {
        return view;
    }

    @Override
    public View inflate(int layoutId)
    {
        return LayoutInflater.from(getContext()).inflate(layoutId,null);
    }

    @Override
    public View getCurrentLayout()
    {
        return currentView;
    }

    /**
     * 传入的就是要显示的view
     * @param view
     */
    @Override
    public void showLayout(View view)
    {

        // 没有父布局，说明没有初始化
        if(group == null)
            init();

        this.currentView = view;

        // 如果已经是同一个view ，就不同再次替换了
        if(group.getChildAt(index) != view)
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            // 一个view只能有一个父布局，因此在添加到别的布局前，要先移除
            if(parent != null)
               parent.removeView(view);

            // 添加到指定的布局中，也就是替换（删除指定位置的view，在同样位置插入新的view）
            group.removeViewAt(index);
            group.addView(view,index,params);
        }
    }

    @Override
    public Context getContext()
    {
        return view.getContext();
    }

    /**
     * 恢复正常情况的界面，就是调用showLayout（）传入正常的view
     */
    @Override
    public void restoreView()
    {
        showLayout(view);
    }
}
