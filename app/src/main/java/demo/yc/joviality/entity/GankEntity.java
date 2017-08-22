package demo.yc.joviality.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/21 0021
 * @time: 21:13
 * @detail:
 */

@Entity(nameInDb = "gank_collection")
public class GankEntity implements Serializable
{
    public static final long serialVersionUID = 1234560;
    /**
     * _id : 5992ad85421aa9672cdf0810
     * createdAt : 2017-08-15T16:15:01.769Z
     * desc : 致Android开发 —— 灵活的Class替换插件
     * images : ["http://img.gank.io/25efa54d-1baf-4e8c-8bde-260c476b4990"]
     * publishedAt : 2017-08-17T11:36:42.967Z
     * source : web
     * type : Android
     * url : https://github.com/dinuscxj/ClassPlugin
     * used : true
     * who : dinus_developer
     */
    @Id
    private Long id;
    @SerializedName("desc")
    private String descX;
    @SerializedName("publishedAt")
    private String publishedAtX;
    @SerializedName("url")
    private String urlX;
    @SerializedName("who")
    private String whoX;
    @SerializedName("images")
    @Transient
    private List<String> imagesX;

    @Generated(hash = 253414853)
    public GankEntity(Long id, String descX, String publishedAtX, String urlX,
            String whoX) {
        this.id = id;
        this.descX = descX;
        this.publishedAtX = publishedAtX;
        this.urlX = urlX;
        this.whoX = whoX;
    }

    @Generated(hash = 598526695)
    public GankEntity() {
    }

    public String getDescX()
    {
        return descX;
    }

    public void setDescX(String descX)
    {
        this.descX = descX;
    }

    public String getPublishedAtX()
    {
        return publishedAtX;
    }

    public void setPublishedAtX(String publishedAtX)
    {
        this.publishedAtX = publishedAtX;
    }

    public String getUrlX()
    {
        return urlX;
    }

    public void setUrlX(String urlX)
    {
        this.urlX = urlX;
    }

    public String getWhoX()
    {
        return whoX;
    }

    public void setWhoX(String whoX)
    {
        this.whoX = whoX;
    }

    public List<String> getImagesX()
    {
        return imagesX;
    }

    public void setImagesX(List<String> imagesX)
    {
        this.imagesX = imagesX;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
