package demo.yc.joviality.entity;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 21:06
 * @detail:
 */

public class ImageEntity
{
    private String small;
    private String large;

    public ImageEntity(){}

    public ImageEntity(String small)
    {
        this.small = small;
    }

    public String getSmall()
    {
        return small;
    }

    public void setSmall(String small)
    {
        this.small = small;
    }

    public String getLarge()
    {
        return large;
    }

    public void setLarge(String large)
    {
        this.large = large;
    }
}
