package demo.yc.joviality.mvp.mvpmodel;

import java.io.IOException;

import demo.yc.joviality.entity.ResponseGankEntity;
import demo.yc.joviality.entity.ResponseImageEntity;
import demo.yc.joviality.http.GsonUtils;
import demo.yc.joviality.http.URLHelper;
import demo.yc.joviality.interfaces.IListDataCallback;
import demo.yc.jovialityyc.R;
import demo.yc.lib.http.HttpHelper;
import demo.yc.lib.utils.LogUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:46
 */

public class FragListModelImp<T>
{

    private IListDataCallback callback;
    public FragListModelImp(IListDataCallback<T> back)
    {
        callback = back;
    }

    public void getListData(final int item,final String requestTag,int page)
    {
        String url = null;
        switch (item)
        {
            case R.string.gank:
                url = URLHelper.getStudyListUrl(requestTag, page);
                break;
            case R.string.image:
                url = URLHelper.getImagesListUrl(requestTag, page);
                break;
        }

        HttpHelper.requestGet(url, new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                callback.onError("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String json = response.body().string();
                LogUtil.d("result", json);
                if (response.isSuccessful())
                {
                    switch (item)
                    {
                        case R.string.gank:
                            ResponseGankEntity gank = GsonUtils.parseGankEntity(json);
                            if(gank == null)
                                callback.onError("加载失败");
                            callback.onSuccess(gank.getResults());
                            break;
                        case R.string.image:
                            ResponseImageEntity image = GsonUtils.parseImageEntity(json);
                            if(image == null)
                                callback.onError("加载失败");

                            callback.onSuccess(image.getImgs());

                            break;
                    }

                }else
                {
                    callback.onError("加载失败");
                }
            }
        });
    }
}
