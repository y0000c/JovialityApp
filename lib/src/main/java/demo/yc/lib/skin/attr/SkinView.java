package demo.yc.lib.skin.attr;

import android.view.View;

import java.util.List;

import demo.yc.lib.utils.LogUtil;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:26
 * @detail:
 */

public class SkinView
{

    private View mView;
    private List<SkinAttr> mAttrs;

    public SkinView(View view,List<SkinAttr> attrs)
    {
        if(view == null)
            throw new IllegalArgumentException("view can not be null");

        if(attrs == null)
            throw new IllegalArgumentException("attrs can not be null");

        mAttrs = attrs;
        mView = view;

        LogUtil.w("attr","attrlist size is "+attrs.size());
    }

    public void apply()
    {

        for(SkinAttr attr:mAttrs)
        {
            LogUtil.w("attr",attr.getResName()+"----"+attr.getResType());
            attr.apply(mView);
        }
    }

    public View getView()
    {
        return mView;
    }

    public void setView(View mView)
    {
        this.mView = mView;
    }

    public List<SkinAttr> getAttrs()
    {
        return mAttrs;
    }

    public void setAttrs(List<SkinAttr> mAttrs)
    {
        this.mAttrs = mAttrs;
    }


}
