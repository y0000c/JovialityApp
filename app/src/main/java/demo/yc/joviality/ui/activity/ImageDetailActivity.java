package demo.yc.joviality.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.joviality.entity.ResponseImageEntity;
import demo.yc.joviality.ui.activity.base.BaseDetailActivity;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.CommonUtil;
import demo.yc.lib.utils.LogUtil;

public class ImageDetailActivity extends BaseDetailActivity
{


    @BindView(R.id.image_detail_item)
    ImageView mImageView;

    public static final String IMAGE_TAG = "image_detail";
    @BindView(R.id.image_detail_prepare_small)
    ImageView mSmallView;
    @BindView(R.id.image_detail_prepare_gif)
    ImageView mLodingView;
    @BindView(R.id.image_detail_prepare_layout)
    RelativeLayout mLoadingLayout;
    private ResponseImageEntity.ImgsBean imageData;


    private ObjectAnimator moveUp;
    private ObjectAnimator moveDown;
    private boolean isShow = false;
    private static final int SAVE_ITEM = 0x111;
    private boolean isSuccess;

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        imageData = (ResponseImageEntity.ImgsBean) extras.getSerializable(IMAGE_TAG);
        if (imageData == null)
        {
            finish();
        }
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void initEvents()
    {
        mToolbar.setVisibility(View.GONE);
        setTitle("图片详情");

        moveDown = ObjectAnimator.ofFloat(mToolbar, View.ALPHA, 0, 1);
        moveUp = ObjectAnimator.ofFloat(mToolbar, View.ALPHA, 1, 0);
        moveDown.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {
                mToolbar.setVisibility(View.VISIBLE);
                isShow = true;
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {

            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });
        moveUp.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation)
            {
                isShow = false;
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                mToolbar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {

            }
        });
        moveDown.setDuration(500);
        moveUp.setDuration(500);

        if (CommonUtil.isEmpty(imageData.getImageUrl()))
            imageData.setImageUrl(imageData.getThumbnailUrl());
        LogUtil.d("glide", imageData.getImageUrl());

        Glide.with(this).load(R.drawable.loading).into(mLodingView);
        Glide.with(this).asBitmap().load(imageData.getThumbnailUrl()).into(mSmallView);

        Glide.with(this)
                .applyDefaultRequestOptions(new RequestOptions().fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .load(imageData.getImageUrl())
                .listener(new RequestListener<Drawable>()
                {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource)
                    {
                        LogUtil.w("glide", "loading is failed---");
                        isSuccess = false;
                        mLoadingLayout.setVisibility(View.GONE);
                        showErrorDialog();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource)
                    {
                        LogUtil.w("glide", "loading is ok---");
                        isSuccess = true;
                        mLoadingLayout.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mImageView);
    }


    @OnClick(R.id.image_detail_item)
    public void onViewClicked()
    {
        if (isSuccess)
        {
            if (isShow)
                moveUp.start();
            else
                moveDown.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, SAVE_ITEM, 1, "保存");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == SAVE_ITEM)
        {
            download();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Object getShareData()
    {
        return imageData;
    }

    @Override
    public Object getCollectData()
    {
        return imageData;
    }

    @Override
    public Object getDownloadData()
    {
        return imageData;
    }

    private void showErrorDialog()
    {
        new AlertDialog.Builder(this)
                .setMessage("图片加载失败")
                .setCancelable(false)
                .setPositiveButton("返回", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                }).create().show();
    }

}
