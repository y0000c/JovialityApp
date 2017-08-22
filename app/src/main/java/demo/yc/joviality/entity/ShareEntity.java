package demo.yc.joviality.entity;

/**
 * @author: YC
 * @date: 2017/8/22 0022
 * @time: 14:05
 * @detail:
 */

public class ShareEntity
{
    public static final int TYPE_IMAGE = 111;
    public static final int TYPE_URL = 112;

    private int type;
    private String extra;
    private String url;
    private String imagePath;

    public ShareEntity(int type ,String extra, String url, String imagePath)
    {
        this.type = type;
        this.extra = extra;
        this.url = url;
        this.imagePath = imagePath;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getExtra()
    {
        return extra;
    }

    public void setExtra(String extra)
    {
        this.extra = extra;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
}
