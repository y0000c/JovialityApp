package demo.yc.joviality.http;

import com.google.gson.Gson;

import demo.yc.joviality.entity.ResponseGankEntity;
import demo.yc.joviality.entity.ResponseImageEntity;
import demo.yc.joviality.entity.ResponseNewsEntity;

/**
 * @author: YC
 * @date: 2017/8/18 0018
 * @time: 13:12
 * @detail:
 */

public class GsonUtils
{
    private static Gson gson = new Gson();


    public static ResponseGankEntity parseGankEntity(String json)
    {
        ResponseGankEntity gank = null;
        try
        {
            gank = gson.fromJson(json,ResponseGankEntity.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return gank;
    }

    public static ResponseImageEntity parseImageEntity(String json)
    {
        ResponseImageEntity image = null;
        try
        {
            image = gson.fromJson(json,ResponseImageEntity.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return image;
    }

    public static ResponseNewsEntity parseNewsEntity(String json)
    {
        ResponseNewsEntity news = null;
        try
        {
            news = gson.fromJson(json,ResponseNewsEntity.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return news;
    }



}
