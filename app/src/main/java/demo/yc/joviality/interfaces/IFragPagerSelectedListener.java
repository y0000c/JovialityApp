package demo.yc.joviality.interfaces;

/**
 * @author: YC
 * @date: 2017/8/6 0006
 * @time: 17:36
 * @detail:
 */

public interface IFragPagerSelectedListener
{
    /**
     * 选中的fragment,以及传递的参数
     * @param keyWord
     * @param position
     */
    void onPagerSelected(String keyWord,int position);
}
