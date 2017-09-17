package demo.yc.lib.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 23:45
 * @detail:
 */

public class HttpHelper
{
    private static OkHttpClient client = new OkHttpClient();

    public static void requestGet(String url,Callback callback)
    {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void cancelAllRequest()
    {
        client.dispatcher().cancelAll();

    }

    public static void startDownLoad(String url, String path, String fileName, final Callback callback)
    {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                callback.onFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                response.body().contentLength();
            }
        });

    }

}
