package demo.yc.joviality.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/15 0015
 * @time: 23:37
 * @detail:
 */

public class ResponseImageEntity
{

    private List<ImgsBean> imgs;

    public List<ImgsBean> getImgs()
    {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs)
    {
        this.imgs = imgs;
    }

    public static class ImgsBean implements Serializable
    {
        /**
         * downloadUrl : http://c.hiphotos.baidu.com/image/pic/item/1e30e924b899a9014edb777e1f950a7b0308f58f.jpg
         * imageUrl : http://c.hiphotos.baidu.com/image/pic/item/1e30e924b899a9014edb777e1f950a7b0308f58f.jpg
         * imageWidth : 740
         * imageHeight : 562
         * thumbnailUrl : http://imgt6.bdstatic.com/it/u=2,2014958338&fm=25&gp=0.jpg
         * thumbnailWidth : 230
         * thumbnailHeight : 174
         * thumbLargeWidth : 310
         * thumbLargeHeight : 235
         * thumbLargeUrl : http://c.hiphotos.baidu.com/image/w%3D310/sign=587cbd7749fbfbeddc59307e48f1f78e/1e30e924b899a9014edb777e1f950a7b0308f58f.jpg
         * thumbLargeTnWidth : 400
         * thumbLargeTnHeight : 303
         * thumbLargeTnUrl : http://c.hiphotos.baidu.com/image/w%3D400/sign=a6dc5283f9edab6474724cc0c737af81/1e30e924b899a9014edb777e1f950a7b0308f58f.jpg
         */

        private String downloadUrl;
        private String imageUrl;
        private int imageWidth;
        private int imageHeight;
        private String thumbnailUrl;
        private int thumbnailWidth;
        private int thumbnailHeight;

        public String getDownloadUrl()
        {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl)
        {
            this.downloadUrl = downloadUrl;
        }

        public String getImageUrl()
        {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl)
        {
            this.imageUrl = imageUrl;
        }

        public int getImageWidth()
        {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth)
        {
            this.imageWidth = imageWidth;
        }

        public int getImageHeight()
        {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight)
        {
            this.imageHeight = imageHeight;
        }

        public String getThumbnailUrl()
        {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl)
        {
            this.thumbnailUrl = thumbnailUrl;
        }

        public int getThumbnailWidth()
        {
            return thumbnailWidth;
        }

        public void setThumbnailWidth(int thumbnailWidth)
        {
            this.thumbnailWidth = thumbnailWidth;
        }

        public int getThumbnailHeight()
        {
            return thumbnailHeight;
        }

        public void setThumbnailHeight(int thumbnailHeight)
        {
            this.thumbnailHeight = thumbnailHeight;
        }
    }
}
