package demo.yc.joviality.entity;

import java.io.Serializable;

/**
 * @author: YC
 * @date: 2017/8/23 0023
 * @time: 23:52
 * @detail:
 */

public class ImageurlsBean implements Serializable
{
    public static final long serialVersionUID = 1234563;
    /**
     * height : 0
     * width : 0
     * url : http://n.sinaimg.cn/news/crawl/20170823/7L2O-fykcpru9134881.png
     */
    private String url;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
