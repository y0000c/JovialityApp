package demo.yc.joviality.mvpview;

import java.util.List;

/**
 * 提供给各种MainFragment使用，因为他们各自都是使用了ViewPager可以复用
 */

public interface FragMainView
{
    void initPagerViews(List<String> list);
}
