package demo.yc.joviality.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/18 0018
 * @time: 11:26
 * @detail:
 */

public class ResponseGankEntity
{
    /**
     * error : false
     * results : [{"_id":"5992ad85421aa9672cdf0810","createdAt":"2017-08-15T16:15:01.769Z","desc":"致Android开发 \u2014\u2014 灵活的Class替换插件","images":["http://img.gank.io/25efa54d-1baf-4e8c-8bde-260c476b4990"],"publishedAt":"2017-08-17T11:36:42.967Z","source":"web","type":"Android","url":"https://github.com/dinuscxj/ClassPlugin","used":true,"who":"dinus_developer"},{"_id":"599386fe421aa9672cdf0812","createdAt":"2017-08-16T07:42:54.135Z","desc":"8-16","publishedAt":"2017-08-17T11:36:42.967Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fil82i7zsmj20u011hwja.jpg","used":true,"who":"daimajia"},{"_id":"59940f6e421aa96729c5724e","createdAt":"2017-08-16T17:25:02.191Z","desc":"前端每周清单半年盘点之 React 与 ReactNative 篇","publishedAt":"2017-08-17T11:36:42.967Z","source":"web","type":"前端","url":"https://zhuanlan.zhihu.com/p/28560073","used":true,"who":"王下邀月熊(Chevalier)"},{"_id":"599416bc421aa967262e1c32","createdAt":"2017-08-16T17:56:12.125Z","desc":"让你的IDE支持聊天","images":["http://img.gank.io/7b77d403-72eb-48b5-afdd-38ce2983d02d"],"publishedAt":"2017-08-17T11:36:42.967Z","source":"web","type":"拓展资源","url":"http://www.jianshu.com/p/ec71d7b03a7f","used":true,"who":"liuzheng"},{"_id":"59945873421aa9672f354dd3","createdAt":"2017-08-16T22:36:35.753Z","desc":"Android 中东阿拉伯语适配，看这一篇够了","images":["http://img.gank.io/1abbc894-96d2-4bca-8239-a29d81ddbd1b"],"publishedAt":"2017-08-17T11:36:42.967Z","source":"web","type":"Android","url":"https://github.com/Freelander/Blog/blob/master/201708/01.md","used":true,"who":"Paul General"},{"_id":"59948b0d421aa9672cdf0818","createdAt":"2017-08-17T02:12:29.526Z","desc":"捕获键盘输入和输入的组合快捷键的JS库","publishedAt":"2017-08-17T11:36:42.967Z","source":"web","type":"前端","url":"https://github.com/jaywcjlove/hotkeys/blob/master/README.md","used":true,"who":"小弟调调™"},{"_id":"5994e223421aa967262e1c34","createdAt":"2017-08-17T08:24:03.687Z","desc":"欲善其事先利其器，学到的不仅是 TODO 技巧，提升的不仅是效率，更有实际项目开发经验、设置代码模版等","publishedAt":"2017-08-17T11:36:42.967Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIxOTU1MDg5Ng==&mid=2247484097&idx=1&sn=e893157f24d7f8c5b59c40afb1fef3c8&chksm=97d8c71ea0af4e08f0f72dc948c69590a70e9586c21559a5d84fc2389c4b71ab62d34a6a3e98#rd","used":true,"who":"ruicbAndroid"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public List<ResultsBean> getResults()
    {
        return results;
    }

    public void setResults(List<ResultsBean> results)
    {
        this.results = results;
    }

    public static class ResultsBean
    {
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
        @SerializedName("_id")
        private String _idX;
        @SerializedName("createdAt")
        private String createdAtX;
        @SerializedName("desc")
        private String descX;
        @SerializedName("publishedAt")
        private String publishedAtX;
        @SerializedName("source")
        private String sourceX;
        @SerializedName("type")
        private String typeX;
        @SerializedName("url")
        private String urlX;
        @SerializedName("used")
        private boolean usedX;
        @SerializedName("who")
        private String whoX;
        @SerializedName("images")
        private List<String> imagesX;

        public String get_idX()
        {
            return _idX;
        }

        public void set_idX(String _idX)
        {
            this._idX = _idX;
        }

        public String getCreatedAtX()
        {
            return createdAtX;
        }

        public void setCreatedAtX(String createdAtX)
        {
            this.createdAtX = createdAtX;
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

        public String getSourceX()
        {
            return sourceX;
        }

        public void setSourceX(String sourceX)
        {
            this.sourceX = sourceX;
        }

        public String getTypeX()
        {
            return typeX;
        }

        public void setTypeX(String typeX)
        {
            this.typeX = typeX;
        }

        public String getUrlX()
        {
            return urlX;
        }

        public void setUrlX(String urlX)
        {
            this.urlX = urlX;
        }

        public boolean isUsedX()
        {
            return usedX;
        }

        public void setUsedX(boolean usedX)
        {
            this.usedX = usedX;
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
    }
}
