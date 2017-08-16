package demo.yc.joviality.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.interfaces.IRecyclerItemClickListener;
import demo.yc.jovialityyc.R;
import demo.yc.lib.uis.ScaleImageView;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 20:56
 * @detail:
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>
{
    private List<ImageEntity> mData ;

    private Context mContext;

    private IRecyclerItemClickListener listener;

    public ImageListAdapter(Context context,List<ImageEntity> mData)
    {
        this.mData = mData;
        this.mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final ImageViewHolder holder;
        final View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_image_list,parent,false);

        holder = new ImageViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(listener != null)
                    listener.onItemClick(itemView,holder.getAdapterPosition());
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        ScaleImageView imageView = (ScaleImageView) holder.image;
        imageView.setInitSize
                (mData.get(position).getWidth(),mData.get(position).getWidth());
        Glide.with(mContext)
             .load(mData.get(position).getSmall())
             .into(imageView);
    }

    @Override
    public int getItemCount()
    {
        if(mData != null)
            return mData.size();
        return 0;
    }

    public void setOnItemClickListener(IRecyclerItemClickListener listener)
    {
        this.listener = listener;
    }

    public List<ImageEntity> getDataList()
    {
        return mData;
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image ;
        public ImageViewHolder(View itemView)
        {
            super(itemView);
            image = itemView.findViewById(R.id.recycler_image_item);
        }
    }
}
