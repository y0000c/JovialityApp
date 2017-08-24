package demo.yc.joviality.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.conf.GlideApp;
import demo.yc.joviality.entity.NewsEntity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseAdapter;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.utils.LogUtil;

/**
 * @author: YC
 * @date: 2017/8/18 0018
 * @time: 11:28
 * @detail:
 */

public class NewsListAdapter extends BaseAdapter<NewsEntity>
{

    public NewsListAdapter(Context context, List<NewsEntity> datas, boolean isOpenLoadMore)
    {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void bindItemData(ViewHolder holder, NewsEntity resultsBean)
    {
        holder.setText(R.id.news_layout_time,resultsBean.getPubDate().substring(0,10));
        holder.setText(R.id.news_layout_source,resultsBean.getSource());

        if(resultsBean.getImageurls() == null
                || resultsBean.getImageurls().size() == 0)
        {
            holder.getItemView(R.id.news_layout_1).setVisibility(View.GONE);
            holder.getItemView(R.id.news_layout_3).setVisibility(View.GONE);
            holder.getItemView(R.id.news_layout_0_title).setVisibility(View.VISIBLE);
            holder.setText(R.id.news_layout_0_title,resultsBean.getTitle());
        }else
        {
            LogUtil.d("result","news image--"+resultsBean.getImageurls().get(0).getUrl());
            if(resultsBean.getImageurls().size() >=3)
            {
                holder.getItemView(R.id.news_layout_1).setVisibility(View.GONE);
                holder.getItemView(R.id.news_layout_3).setVisibility(View.VISIBLE);
                holder.getItemView(R.id.news_layout_0_title).setVisibility(View.GONE);
                holder.setText(R.id.layout_3_title,resultsBean.getTitle());
                GlideApp.with(MyApp.getContext())
                        .applyDefaultRequestOptions(new RequestOptions()
                                .priority(Priority.LOW).placeholder(R.drawable.loading))
                        .asBitmap()
                        .load(resultsBean.getImageurls().get(0).getUrl())
                        .into((ImageView) holder.getItemView(R.id.layout_3_image1));
                GlideApp.with(MyApp.getContext())
                        .applyDefaultRequestOptions(new RequestOptions()
                                .priority(Priority.LOW).placeholder(Color.GRAY))
                        .asBitmap()
                        .load(resultsBean.getImageurls().get(1).getUrl())
                        .into((ImageView) holder.getItemView(R.id.layout_3_image2));
                GlideApp.with(MyApp.getContext())
                        .applyDefaultRequestOptions(new RequestOptions()
                                .priority(Priority.LOW).placeholder(Color.GRAY))
                        .asBitmap()
                        .load(resultsBean.getImageurls().get(2).getUrl())

                        .into((ImageView) holder.getItemView(R.id.layout_3_image3));
            }else
            {
                holder.getItemView(R.id.news_layout_1).setVisibility(View.VISIBLE);
                holder.getItemView(R.id.news_layout_3).setVisibility(View.GONE);
                holder.getItemView(R.id.news_layout_0_title).setVisibility(View.GONE);
                holder.setText(R.id.layout_1_title,resultsBean.getTitle());
                GlideApp.with(MyApp.getContext())
                        .applyDefaultRequestOptions(new RequestOptions()
                                .priority(Priority.LOW).placeholder(Color.GRAY))
                        .asBitmap()
                        .load(resultsBean.getImageurls().get(0).getUrl())
                        .into((ImageView) holder.getItemView(R.id.layout_1_image));
            }
        }
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.recycler_news_list;
    }
}
