package demo.yc.joviality.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;

import java.util.List;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.ImageEntity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseAdapter;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.uis.ScaleImageView;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 20:56
 * @detail:
 */

public class ImageListAdapter extends BaseAdapter<ImageEntity>
{
    public ImageListAdapter(Context context, List<ImageEntity> datas, boolean isOpenLoadMore)
    {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void bindItemData(ViewHolder holder, ImageEntity imageEntity)
    {
        ScaleImageView imageView = holder.getItemView(R.id.recycler_image_item);
        imageView.setInitSize(imageEntity.getWidth(),imageEntity.getHeight());
        Glide.with(MyApp.getContext()).load(imageEntity.getSmall()).into(imageView);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.recycler_image_list;
    }


}
