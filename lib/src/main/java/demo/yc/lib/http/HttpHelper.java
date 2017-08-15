package demo.yc.lib.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
