package demo.yc.joviality.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import demo.yc.joviality.mvp.mvppresenter.base.BasePresenter;
import demo.yc.joviality.mvp.mvppresenter.imp.HomePresenterImp;
import demo.yc.joviality.mvp.mvpview.HomeView;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.joviality.ui.fragment.base.MainTypeFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.skin.SkinManager;
import demo.yc.lib.utils.ActivityUtils;
import demo.yc.lib.utils.LogUtil;
import demo.yc.lib.utils.ResUtils;

/**
 * 最主要的Activity。整个项目的主Activity.HomeActivity
 * 侧滑菜单列表信息是各子模块入口
 * viewpage 显示各子模块的界面信息
 */
public class HomeActivity extends BaseAppActivity implements HomeView
{

    @BindView(R.id.home_frame_layout)
    FrameLayout mHomeFrameLayout;
    @BindView(R.id.home_navigation_view)
    NavigationView mHomeNavigationView;
    @BindView(R.id.home_drawer_layout)
    DrawerLayout mHomeDrawerLayout;

    private boolean isFinish = false;

    private Map<String,MainTypeFragment> fragmentMap;

    /**
     * HomeActivity对应的presenter
     */
    private BasePresenter mHomePresenter;

    /**
     * 当前frag 下标
     */
    private String currentTag = "";

    /**
     * 管理frag
     */
    private FragmentManager fm;


    @Override
    protected void getBundleExtras(Bundle extras)
    {
       
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_home;
    }

    @Override
    protected void initEvents()
    {
        fm = getSupportFragmentManager();
        fragmentMap = new HashMap<>();
        mHomePresenter = new HomePresenterImp(this, this);
        mHomePresenter.initialized();
        showCurrentFragment(ResUtils.resToStr(this,R.string.news));
    }

    /**
     * 显示侧滑菜单信息
     *
     * @param imageId
     */
    @Override
    public void showNavigationView(final int imageId)
    {
        // 设置最开始的标题
        setTitle(mHomeNavigationView.getMenu()
                .getItem(0).getTitle());

        // 设置navigationView 的 头布局图片背景
        View headerView = mHomeNavigationView.getHeaderView(0);
        ImageView mainImage = headerView
                .findViewById(R.id.home_navigation_Image);
        mainImage.setImageResource(imageId);

        //设置menu选项图片颜色，null表示图片自身颜色，其他则覆盖图片自身颜色
        mHomeNavigationView.setItemIconTintList(getResources().getColorStateList(R.color.skin_main_color));
        mHomeNavigationView.setItemTextColor(getResources().getColorStateList(R.color.navig_menu_color));

        // menu item 点击事件
        mHomeNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item)
            {
                if(item.getItemId() == R.id.home_setting_item)
                {
                    jumpToActivity(SettingActivity.class,null,false);
                    mHomeDrawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                switch (item.getItemId())
                {
                    case R.id.home_news_item:
                        showCurrentFragment(
                                ResUtils.resToStr(HomeActivity.this,R.string.news));
                        break;
                    case R.id.home_images_item:
                        showCurrentFragment(
                                ResUtils.resToStr(HomeActivity.this,R.string.image));
                        break;
//                    case R.id.home_video_item:
//                        showCurrentFragment(
//                                ResUtils.resToStr(HomeActivity.this,R.string.video));
//                        break;
                    case R.id.home_gank_item:
                        showCurrentFragment(
                                ResUtils.resToStr(HomeActivity.this,R.string.gank));
                        break;
                }
                item.setChecked(true);
                mHomeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //设置drawerLayout 的 toggle控制，就是设置打开和关闭的监听事件，
        // 同时可以绑定到toolbar 的左侧按钮上
        ActionBarDrawerToggle mDrawerToggle =
                new ActionBarDrawerToggle(this,mHomeDrawerLayout,mToolbar
                        ,R.string.open,R.string.close)
                {
                    @Override
                    public void onDrawerOpened(View drawerView)
                    {
                        super.onDrawerOpened(drawerView);
                        setTitle("");
                    }

                    @Override
                    public void onDrawerClosed(View drawerView)
                    {
                        super.onDrawerClosed(drawerView);
                        setTitle(currentTag);
                    }
                };
        mDrawerToggle.syncState();
        // 给drawerLayout 设置上面实现的监听器
        mHomeDrawerLayout.addDrawerListener(mDrawerToggle);


    }

    /**
     * 显示选中的fragment
     * @param targetTag
     */
    private void showCurrentFragment(String targetTag)
    {
        if (!currentTag.equals(targetTag))
        {
            doReplace(currentTag,targetTag);
            currentTag = targetTag;
        }
    }

    /**
     * 完成fragment替换任务
     * @param lastTag
     * @param targetTag
     */
    private void doReplace(String lastTag,String targetTag)
    {
        // 如果当前的frag未创建，则创建一个
        if(fragmentMap.get(targetTag) == null)
        {
            MainTypeFragment fragment =
                    MainTypeFragment.newInstance(targetTag);
            fragmentMap.put(targetTag,fragment);

            fm.beginTransaction().add(R.id.home_frame_layout,fragment).commit();
        } // 如果已经存在，则使用show 和 hide替代
        if(fragmentMap.get(lastTag) != null){
            fm.beginTransaction()
                    .hide(fragmentMap.get(lastTag))
                    .show(fragmentMap.get(targetTag))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.tool_menu_collection:
                LogUtil.d("menu","show collection");
                jumpToActivity(CollectionActivity.class,null,false);
                break;
            case R.id.tool_menu_download:
                LogUtil.d("menu","show download");
                break;
            case R.id.tool_menu_search:
                jumpToActivity(SearchActivity.class,null,false);
                LogUtil.d("menu","show search");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed()
    {
        if(mHomeDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mHomeDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            if(isFinish)
            {
                ActivityUtils.getInstance().clearActivity();
                return;
            }
            isFinish = true;
            Snackbar.make(mHomeDrawerLayout
                    ,"再次点击退出",Snackbar.LENGTH_SHORT).show();
            mHomeDrawerLayout.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    isFinish = false;
                }
            },2000);
        }
    }

    @Override
    public void updateSKin()
    {
        if(mHomeNavigationView != null)
        {
            mHomeNavigationView.setItemIconTintList(SkinManager.getInstance().getColorByPlugin("skin_main_color"));
            mHomeNavigationView.setItemTextColor(SkinManager.getInstance().getColorByPlugin("navig_menu_color"));
        }
    }
}
