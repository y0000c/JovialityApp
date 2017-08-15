package demo.yc.joviality.mvpmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.yc.joviality.entity.ImageEntity;
import demo.yc.joviality.entity.ResponseImageListEntity;
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
    public ImageListFragModelImp(IListDataCallback back)
    {
        callback = back;
    }


    public void getListData(final String requestTag, int eventCode, String keyWords, int page)
    {

        String url = URLHelper.getImagesListUrl("明星",1);
        HttpHelper.requestGet(url,new Callback(){
            @Override
            public void onFailure(Call call, IOException e)
            {
                callback.onError("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String result = response.body().string();
                if(response.isSuccessful())
                {
                    LogUtil.d("result",result);
                    try
                    {
                        JSONObject object = new JSONObject(result);
                        JSONArray array = object.getJSONArray("imgs");
                        LogUtil.d("result",array.length()+array.getJSONObject(0).getString("imageUrl"));
                        ResponseImageListEntity data = new ResponseImageListEntity();
                        List<ImageEntity> imageList = new ArrayList<>();

                        for(int i=0;i<array.length();i++)
                        {
                            try
                            {
                                String url = array.getJSONObject(i).getString("imageUrl");
                                if (!CommonUtil.isEmpty(url))
                                    imageList.add(new ImageEntity(url));
                            } catch (Exception e)
                            {

                            }
                        }

                        data.setImageList(imageList);

                        for(int i=0;i<imageList.size();i++)
                        {
                            LogUtil.w("result",imageList.get(i).getSmall());
                        }
                        callback.onSuccess(1,data);
                    } catch (JSONException e)
                    {

                    }

                }
            }
        });

//        ResponseImageListEntity data = new ResponseImageListEntity();
//        List<ImageEntity> imageList = new ArrayList<>();
//        for(int i=0;i<10;i++)
//            imageList.add(new ImageEntity("http://img04.sogoucdn.com/app/a/100520076/ae30ad2e44cb87ebaa00235b42f47a4d"));
//        data.setImageList(imageList);
//        callback.onSuccess(eventCode,data);
    }
}
