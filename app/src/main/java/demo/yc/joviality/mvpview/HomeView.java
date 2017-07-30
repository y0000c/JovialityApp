package demo.yc.joviality.mvpview;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * HomeActivity 对应的view接口
 */
public interface HomeView
{

    /**
     * 显示HomeActivity侧滑菜单的列表信息
     * @param imageId
     * @param frags
     */
    void showNavigationView(int imageId,List<Fragment> frags);


}
