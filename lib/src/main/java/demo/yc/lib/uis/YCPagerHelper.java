package demo.yc.lib.uis;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.yc.lib.R;

/**
 * @author: YC
 * @date: 2017/8/6 0006
 * @time: 15:24
 * @detail:
 */

public class YCPagerHelper
{
    private Context mContext;

    // tab容器(标题)
    private LinearLayout mTabContainer;

    // 用来存放 mTabContainer 和
    private RelativeLayout mContainer;

    // 角标指示器
    private View mIndicatorView;

    // tab 的宽度
    private int mTabWidth = 0;
    // 角标指示器距离左边的margin
    private float mLeftMagrin;


    public YCPagerHelper(Context context) {
        this.mContext = context;
        mContainer = new RelativeLayout(context);
        initIndicatorView();
    }

    public RelativeLayout getContainer() {
        return mContainer;
    }

    /**
     * 清空Tab容器
     */
    public void removeAllViews() {
        if(mTabContainer != null)
            mTabContainer.removeAllViews();
    }

    /**
     * 添加TabView
     */
    public void addTabView(View itemTv) {
        if (mTabContainer == null) {
            initTabContainer();
        }
        mTabContainer.addView(itemTv);
    }

    /**
     * 初始化Tab容器
     */
    private void initTabContainer() {
        mTabContainer = new LinearLayout(mContext);
        mContainer.addView(mTabContainer);
    }

    /**
     * 根据当前index获取TabView
     */
    public View getTabView(int index) {
        return mTabContainer.getChildAt(index);
    }

    /**
     * 滑动的时候滚动角标指示器
     */
    public void scrollIndicator(int position, float offset) {
        if (mTabWidth == 0 || mIndicatorView == null)
            return;
        setLeftMargin((position + offset) * mTabWidth);
    }

    /**
     * 设置角标指示器的left margin
     */
    private void setLeftMargin(float leftMargin) {
        mLeftMagrin = leftMargin;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIndicatorView
                .getLayoutParams();
        params.leftMargin = (int) mLeftMagrin;
        mIndicatorView.setLayoutParams(params);
    }


    /**
     * 设置角标指示器的View
     */
    public void setIndicatorView(int height,int width)
    {
        RelativeLayout.LayoutParams lp =
                (RelativeLayout.LayoutParams) mIndicatorView.getLayoutParams();
        lp.width = width;
        lp.height = height;
    }

    public void initIndicatorView()
    {
        ImageView view = new ImageView(mContext);
        view.setImageResource(R.drawable.indicator_line);
        view.setPadding(20,0,20,0);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        params.addRule(RelativeLayout.CENTER_VERTICAL);
        this.mIndicatorView = view;

        // 先移除再添加
        mContainer.removeView(mIndicatorView);
        mContainer.addView(mIndicatorView);
    }

    /**
     * 设置tab 的 width
     */
    public void setTabWidth(int mTabWidth) {
        this.mTabWidth = mTabWidth;
    }

    /**
     * 处理选中的tab
     * @param position
     */
    public void highLightItem(int position)
    {
        TextView tv = (TextView) getTabView(position);
        tv.setTextColor(Color.WHITE);
        tv.setScaleX(1.5f);
        tv.setScaleY(1.5f);
    }

    /**
     * 恢复之前选中的tag
     * @param position
     */
    public void restoreItem(int position)
    {
        TextView tv = (TextView) getTabView(position);
        tv.setTextColor(Color.BLACK);
        tv.setScaleX(1.0f);
        tv.setScaleY(1.0f);
    }


}
