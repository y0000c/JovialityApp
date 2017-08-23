package demo.yc.joviality.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import demo.yc.joviality.api.APIContent;
import demo.yc.lib.utils.LogUtil;

/**
 * @author: YC
 * @date: 2017/8/16 0016
 * @time: 0:55
 * @detail:
 */

public class URLHelper
{
    public static final int PAGE_LIMIT = 20;

    /**
     * 获取百度图片url
     * @param category
     * @param pageCount
     * @return
     */
    public static String getImagesListUrl(String category, int pageCount) {
        StringBuffer sb = new StringBuffer();
        sb.append(APIContent.BAIDU_IMAGES_URLS);
        sb.append("?col=");
        try {
            sb.append(URLEncoder.encode(category, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&tag=");
        try {
            sb.append(URLEncoder.encode("全部", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&pn=");
        sb.append(pageCount * PAGE_LIMIT);
        sb.append("&rn=");
        sb.append(PAGE_LIMIT);
        sb.append("&from=1");

        LogUtil.d("url",category+"--"+pageCount);
        LogUtil.d("url",sb.toString());
        return sb.toString();
    }

    /**
     * 获取it学习文章URl
     * @param category
     * @param pageCount
     * @return
     */
    public static String getStudyListUrl(String category,int pageCount)
    {
        StringBuffer sb =  new StringBuffer();
        sb.append(APIContent.IT_STUDY_URLS)
          .append(category)
          .append("/")
          .append(PAGE_LIMIT)
          .append("/")
          .append(pageCount);

        LogUtil.d("url",category+"--"+pageCount);
        LogUtil.d("url",sb.toString());

        return sb.toString();
    }


    public static String getImageTestUrl(String category,int pageCount)
    {

//        StringBuffer sb = new StringBuffer();
//        sb.append("http://image.baidu.com/i?tn=baiduimagejson&word=");
//        try
//        {
//            sb.append(URLEncoder.encode(" 周杰伦", "UTF-8"));
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        sb.append("&pn=10&rn=10&ie=utf8");
//        Log.d("url",sb.toString());
 //       return sb.toString();
      //  return  "https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111111&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E5%AE%B6%E5%85%B7&oq=jiaju&rsp=0";
   // return "http://image.baidu.com/i?tn=baiduimagejson&word=周杰伦&pn=10&rn=10&ie=utf8";

        StringBuffer sb = new StringBuffer();
        sb.append(APIContent.BAIDU_IMAGES_URLS);
        sb.append("?col=");
        try {
            sb.append(URLEncoder.encode("所有", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&tag=");
        try {
            sb.append(URLEncoder.encode(category, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&pn=");
        sb.append(pageCount * PAGE_LIMIT);
        sb.append("&rn=");
        sb.append(PAGE_LIMIT);
        sb.append("&from=1");

        LogUtil.d("url",category+"--"+pageCount);
        LogUtil.d("url",sb.toString());
        return sb.toString();
    }

    public static String getNewsListUrl(String id,String name,int pageCount,int days)
    {
    //&showapi_appid=19409&showapi_test_draft=false&showapi_timestamp=20170823171005&title=&showapi_sign=b3ee62b8c6014c678e978b12efffcb9a
        StringBuffer sb = new StringBuffer();
        sb.append(APIContent.NEWS_URLS);
        sb.append("channelId="+id);
        try {
            sb.append("&channelName"+URLEncoder.encode(name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&maxResult="+PAGE_LIMIT);
        sb.append("&needAllList=0&needContent=0&needHtml=0");
        sb.append("&page="+pageCount);
        sb.append("&showapi_appid=19409");
       // sb.append("&showapi_timestamp="+ CommonUtil.getDayTime(days));
        sb.append("&showapi_sign="+"292936aa62644f0f83b76a33959e1e62");

        LogUtil.d("url",name+"--"+pageCount);
        LogUtil.d("url",sb.toString());
        return sb.toString();
    }
}
