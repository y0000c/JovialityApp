package demo.yc.lib.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.yc.lib.utils.LogUtil;

/**
 * @author: YC
 * @date: 2017/8/6 0006
 * @time: 14:55
 * @detail:
 */

public class YCPagerIndicator extends HorizontalScrollView implements
        ViewPager.OnPageChangeListener
{


    Context context;

    // 标题数目
    int tabCount = 0;
    // 显示的标题数量
    int showCount = 5;
    // 每个标题的长度（屏幕长/可见的标题数目）
    int tabWidth;

    // 处理tab布局变化的类
    YCPagerHelper mContainGroup;
    // 用来初始化tab属性，如果不这样会发生空指针异常（不知道为什么）
    Runnable mRunnable;

    // 操作的viewpager
    ViewPager mPager;
    // viewPager对应的listener
    ViewPager.OnPageChangeListener listener;
    // 当前pager index
    int currentItem = 0;

    int textWidth = 0;
    int textHeight = 0;

    public YCPagerIndicator(Context context)
    {
        this(context,null);
    }

    public YCPagerIndicator(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public YCPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    /**
     * 初始化tab容器
     */
    private void initView()
    {
        // 添加tab的容器到当前布局中
        mContainGroup = new YCPagerHelper(context);
        addView(mContainGroup.getContainer());

    }

    // 所有的初始化都从设置viewpager 开始
    public void setViewPager(ViewPager pager)
    {
        mPager = pager;
        if(pager == null)
            throw new IllegalArgumentException("viewPager is null...");
        // 设置监听事件
        mPager.addOnPageChangeListener(this);
        tabCount = mPager.getAdapter().getCount();
        // 如果总数目小于可见，那么可见的就是全部数目
        if(tabCount < showCount)
            showCount = tabCount;
        //获取屏幕宽度，然后计算每个tab的宽度
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        tabWidth =  screenWidth / showCount;

        initData();
    }

    /**
     * 初始化tab数据
     */
    private void initData()
    {

        mRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                mContainGroup.setTabWidth(tabWidth);
                Rect r = new Rect();
                Paint p = new Paint();
                for(int i=0;i<tabCount;i++)
                {
                    TextView tv = new TextView(context);
                    String title = (String) mPager.getAdapter().getPageTitle(i);
                    tv.setText(title);
                    tv.setTextColor(Color.BLACK);
                    setItemClickEvent(tv,i);
                    LogUtil.d("tab","title："+tv.getText()+"--"+title);
                    p.getTextBounds(title,0,title.length(),r);
                    textHeight = Math.max(r.height(),textHeight);
                    textWidth = Math.max(r.width(),textWidth);
                    tv.setGravity(Gravity.CENTER);
                    mContainGroup.addTabView(tv);
                }

                setItemParams();

            }
        };
        post(mRunnable);

    }

    // 初始化每个tab的宽高
    private void setItemParams()
    {
        for(int i=0;i<tabCount;i++)
        {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)
                    mContainGroup.getTabView(i).getLayoutParams();
            lp.width = tabWidth;
            lp.height = getHeight();
        }
        mContainGroup.highLightItem(currentItem);
        mContainGroup.setIndicatorView(textHeight*5,textWidth*8);
    }


    // 为每个title设置点击事件
    private void setItemClickEvent(TextView tv,final int position)
    {
        tv.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                jumpToItem(position);
            }
        });
    }

    // 处理title的点击事件
    private void jumpToItem(int position)
    {
        mPager.setCurrentItem(position);
    }

    // 接收外界传入过来的viewPager Listener
    public void setOnPagerChangeListener(ViewPager.OnPageChangeListener listener)
    {
        this.listener = listener;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
        scroll(position,positionOffset);
        if(listener != null)
            listener.onPageScrolled(position,positionOffset,positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position)
    {
        // 先恢复原本的tab，再更新当前的tag
        mContainGroup.restoreItem(currentItem);
        currentItem = position;
        mContainGroup.highLightItem(currentItem);

        if(listener != null)
            listener.onPageSelected(position);

    }

    @Override
    public void onPageScrollStateChanged(int state)
    {
        if(listener != null)
            listener.onPageScrollStateChanged(state);
    }

    /**
     * 滚动tab
     * @param position
     * @param offset
     */
    private void scroll(int position,float offset)
    {
        // 当前tab左边到屏幕左边的距离
        int total = (int) ((position + offset) * tabWidth);

        // 中间的那个tag的起始位置
        // getWidth()/2 =  屏幕宽度中心的点
        // 如果tag 是中间那块，则 tabWidth/2 就是tab左边里屏幕中心的距离
        // getWidth()/ 2 - tabWidth/2 就是屏幕左边到中间tab左边的距离
        final int middle = (getWidth() - tabWidth) / 2;


        // 对于horizontalScrollView来说，只有滚动的值是正数才会移动
        // 因此当前tab是前面的几个（还没到中间），所以也不会移动
        // 大概可以类推，类似这种可以滚动的控件，调用scrollTo(x)，x是正数才有效
        int scroll = total - middle;

        LogUtil.d("tab","item:"+total+"：---："+getWidth()+"：---:"+middle+":---:"+scroll);

            // 是移动到指定的位置
            this.scrollTo(scroll, 0);
            mContainGroup.scrollIndicator(position, offset);
    }
}

