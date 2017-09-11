package demo.yc.joviality.ui.adapter;

import android.content.Context;
import android.graphics.Color;

import java.util.List;

import demo.yc.joviality.entity.SkinEntity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseAdapter;
import demo.yc.lib.base.ViewHolder;

/**
 * @author: YC
 * @date: 2017/8/18 0018
 * @time: 11:28
 * @detail:
 */

public class SkinListAdapter extends BaseAdapter<SkinEntity>
{

    public SkinListAdapter(Context context, List<SkinEntity> datas, boolean isOpenLoadMore)
    {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void bindItemData(ViewHolder holder, SkinEntity resultsBean)
    {
        holder.setText(R.id.skin_list_name,resultsBean.getName());
        holder.getItemView(R.id.skin_list_icon)
                .setBackgroundColor(Color.parseColor(resultsBean.getColor()));
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.recycler_skin_list;
    }
}
