package demo.yc.joviality.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import demo.yc.joviality.MyApp;
import demo.yc.joviality.entity.ResponseGankEntity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.base.BaseAdapter;
import demo.yc.lib.base.ViewHolder;
import demo.yc.lib.utils.CommonUtil;
import demo.yc.lib.utils.LogUtil;

/**
 * @author: YC
 * @date: 2017/8/18 0018
 * @time: 11:28
 * @detail:
 */

public class GankListAdapter extends BaseAdapter<ResponseGankEntity.ResultsBean>
{

    public GankListAdapter(Context context, List<ResponseGankEntity.ResultsBean> datas, boolean isOpenLoadMore)
    {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void bindItemData(ViewHolder holder, ResponseGankEntity.ResultsBean resultsBean)
    {
        String who = resultsBean.getWhoX();
        String title = resultsBean.getDescX();
        String date = resultsBean.getPublishedAtX();

        LogUtil.d("gank",who+"-----"+title+"-------"+date);
        if(CommonUtil.isEmpty(who))
            who = "null";

        if(CommonUtil.isEmpty(title))
            title = "暂无标题";

        if(CommonUtil.isEmpty(date))
            date = CommonUtil.getTodayString();
        else
            date = date.substring(0,10);


       holder.setText(R.id.gank_item_title,title)
             .setText(R.id.gank_item_date,date)
             .setText(R.id.gank_item_who,who);

       ImageView imageView = holder.getItemView(R.id.gank_item_icon);
       List<String> list =  resultsBean.getImagesX();
        if(list != null && list.size() >=1)
            Glide.with(MyApp.getContext()).asBitmap().load(list.get(0)+"?imageView2/0/w/60").into(imageView);
        else
        {
            String url = resultsBean.getUrlX().toLowerCase();
            int iconID = 0;
            if(url.contains("weixin"))
                iconID = R.drawable.weixin;
            else if(url.contains("csdn"))
                iconID = R.drawable.csdn;
            else if(url.contains("github"))
                iconID = R.drawable.github;
            else if(url.contains("jianshu"))
                iconID = R.drawable.jianshu;
            else
                iconID = R.drawable.web;
            Glide.with(MyApp.getContext()).load(iconID).into(imageView);
        }
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.recycler_gank_list;
    }
}
