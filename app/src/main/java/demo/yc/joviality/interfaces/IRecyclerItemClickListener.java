package demo.yc.joviality.interfaces;

import android.view.View;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 21:21
 * @detail:
 * 给RecyclerView添加的点击事件
 */

public interface IRecyclerItemClickListener
{
    void onItemClick(View view,int position);
}
