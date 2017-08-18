package demo.yc.joviality.mvp.mvpmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.http.URLHelper;
import demo.yc.joviality.interfaces.IListDataCallback;
import demo.yc.lib.http.HttpHelper;
import demo.yc.lib.utils.CommonUtil;
import demo.yc.lib.utils.LogUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author: YC
 * @date: 2017/8/3 0003
 * @time: 16:46
 */

public class ImageListFragModelImp
{

    private IListDataCallback callback;
    public ImageListFragModelImp(IListDataCallback<ImageEntity> back)
    {
        callback = back;
    }

    public void getListData(final String requestTag,int page)
    {
        String url = URLHelper.getImagesListUrl(requestTag, page);
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
                String result = response.body().string();
                LogUtil.d("result", result);
                if (response.isSuccessful())
                {
                    try
                    {
                        JSONObject object = new JSONObject(result);
                        JSONArray array = object.getJSONArray("imgs");
                        LogUtil.d("result", array.length() + array.getJSONObject(0).getString("thumbnailUrl"));
                        List<ImageEntity> imageList = new ArrayList<>();

                        for (int i = 0; i < array.length(); i++)
                        {
                            try
                            {
                                JSONObject obj = array.getJSONObject(i);
                                String url = obj.getString("thumbnailUrl");
                                int width = obj.getInt("thumbnailWidth");
                                int height = obj.getInt("thumbnailHeight");
                                if (!CommonUtil.isEmpty(url))
                                {
                                    imageList.add(new ImageEntity(url, width, height));
                                }
                            } catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                        callback.onSuccess(imageList);
                    } catch (JSONException e)
                    {
                        callback.onError("解析异常");
                    }

                }
            }

        });
    }
}
