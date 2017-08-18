package demo.yc.joviality.entity;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 21:06
 * @detail:
 */

public class ImageEntity
{
    // 列表
    //thumbnailUrl
            // detail
       //     thumbLargeUrl
    private String small;
    private String large;
    //downloadUrl
         //   imageUrl
    //imageWidth
        //    imageHeight
    private int width;
    private int height;

    public ImageEntity(){}

    public ImageEntity(String small,int width,int height)
    {
        this.small = small;
        this.width = width;
        this.height = height;
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

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
