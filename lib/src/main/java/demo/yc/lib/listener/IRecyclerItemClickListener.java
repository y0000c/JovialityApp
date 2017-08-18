package demo.yc.lib.listener;

import demo.yc.lib.base.ViewHolder;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 21:21
 * @detail:
 * 给RecyclerView添加的点击事件
 */

public interface IRecyclerItemClickListener<T>
{
    void onItemClick(ViewHolder holder,T data, int position);
}
