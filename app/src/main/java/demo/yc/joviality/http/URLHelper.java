package demo.yc.joviality.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import demo.yc.joviality.api.APIContent;

/**
 * @author: YC
 * @date: 2017/8/16 0016
 * @time: 0:55
 * @detail:
 */

public class URLHelper
{
    public static final int PAGE_LIMIT = 20;

    public static String getImagesListUrl(String category, int pageNum) {
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
        sb.append(pageNum * PAGE_LIMIT);
        sb.append("&rn=");
        sb.append(PAGE_LIMIT);
        sb.append("&from=1");
        return sb.toString();
    }

}
