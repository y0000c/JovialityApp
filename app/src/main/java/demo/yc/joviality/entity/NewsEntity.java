package demo.yc.joviality.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/23 0023
 * @time: 17:53
 * @detail:
 */
@Entity(nameInDb = "news_collection")
public class NewsEntity implements Serializable
{
    public static final long serialVersionUID = 1234562;


    /**
     * id : 5fa1854a3c8de6d099e4f99c7e3e6693
     * havePic : true
     * pubDate : 2017-08-23 17:29:41
     * title : 男子台风中徒手撑货车 货车倒下被压当场身亡
     * channelName : 社会焦点
     * imageurls : [{"height":0,"width":0,"url":"http://n.sinaimg.cn/news/crawl/20170823/7L2O-fykcpru9134881.png"}]
     * desc : 　　原标题：天啦！这起悲剧就发生在中山！男子台风中徒手撑货车，被压身亡！　　南都讯今天，一段男子在狂风中拼命撑住货车，但最终却被压在身下的视频通过网络疯传。今天下午，南都记者了解到，这起惨剧发生在中山坦洲镇内，事件中，男子不幸当场身亡。　　视频记录显示，狂....
     * source : 新浪
     * channelId : 5572a109b3cdc86cf39001da
     * link : http://news.sina.com.cn/s/2017-08-23/doc-ifykcppy0768407.shtml
     * hasAll : true
     */


    @Id
    private Long _id;
    @Unique
    private String id;
    private boolean havePic;
    private String pubDate;
    private String title;
    private String source;
    private String link;
    private boolean hasAll;
    @Transient
    private List<ImageurlsBean> imageurls;

    @Generated(hash = 149451843)
    public NewsEntity(Long _id, String id, boolean havePic, String pubDate, String title, String source, String link, boolean hasAll) {
        this._id = _id;
        this.id = id;
        this.havePic = havePic;
        this.pubDate = pubDate;
        this.title = title;
        this.source = source;
        this.link = link;
        this.hasAll = hasAll;
    }

    @Generated(hash = 2121778047)
    public NewsEntity() {
    }

    public Long get_id()
    {
        return _id;
    }

    public void set_id(Long _id)
    {
        this._id = _id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public boolean isHavePic()
    {
        return havePic;
    }

    public void setHavePic(boolean havePic)
    {
        this.havePic = havePic;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public boolean isHasAll()
    {
        return hasAll;
    }

    public void setHasAll(boolean hasAll)
    {
        this.hasAll = hasAll;
    }

    public List<ImageurlsBean> getImageurls()
    {
        return imageurls;
    }

    public void setImageurls(List<ImageurlsBean> imageurls)
    {
        this.imageurls = imageurls;
    }

    public boolean getHavePic() {
        return this.havePic;
    }

    public boolean getHasAll() {
        return this.hasAll;
    }
}
