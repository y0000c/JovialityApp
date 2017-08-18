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
        StringBuffer  sb =  new StringBuffer();
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
}
