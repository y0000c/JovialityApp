package demo.yc.joviality.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author: YC
 * @date: 2017/8/23 0023
 * @time: 18:16
 * @detail:
 */

public class ResponseNewsEntity
{
    @SerializedName("showapi_res_code")
    private int resCode;

    @SerializedName("showapi_res_error")
    private String resMessage;

    @SerializedName("showapi_res_body")
    private ResBody body;

    public int getResCode()
    {
        return resCode;
    }

    public void setResCode(int resCode)
    {
        this.resCode = resCode;
    }

    public String getResMessage()
    {
        return resMessage;
    }

    public void setResMessage(String resMessage)
    {
        this.resMessage = resMessage;
    }

    public ResBody getBody()
    {
        return body;
    }

    public void setBody(ResBody body)
    {
        this.body = body;
    }

    public static class ResBody
    {
        private int resCode ;

        private Bean pagebean;

        public int getResCode()
        {
            return resCode;
        }

        public void setResCode(int resCode)
        {
            this.resCode = resCode;
        }

        public Bean getPagebean()
        {
            return pagebean;
        }

        public void setPagebean(Bean pagebean)
        {
            this.pagebean = pagebean;
        }
    }

    public static class Bean
    {
        private int allPages;

        private List<NewsEntity> contentlist;

        public List<NewsEntity> getContentlist()
        {
            return contentlist;
        }

        public void setContentlist(List<NewsEntity> contentlist)
        {
            this.contentlist = contentlist;
        }
    }

}
