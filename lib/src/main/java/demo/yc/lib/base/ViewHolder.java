package demo.yc.lib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import demo.yc.lib.utils.ResUtils;

/**
 * @author: YC
 * @date: 2017/8/17 0017
 * @time: 11:24
 * @detail:
 */

public class ViewHolder extends RecyclerView.ViewHolder
{
    /**
     * 当前的itemView
     */
    private View mConvertView;
    /**
     * 当前ItemView布局的子控件
     */
    private SparseArray<View> mViews;

    private  ViewHolder(View itemView)
    {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static  ViewHolder create(Context context, int layoutId, ViewGroup parent)
    {
        View itemView = ResUtils.inflate(context,layoutId,parent);
        return new ViewHolder(itemView);
    }

    public static ViewHolder create(View itemView)
    {
        return new ViewHolder(itemView);
    }

    /**
     * 获取子控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getItemView(int viewId)
    {
        View view = mViews.get(viewId);
        if(view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * 获取当前的布局View
     * @return
     */
    public View getConvertView()
    {
        return mConvertView;
    }


}
