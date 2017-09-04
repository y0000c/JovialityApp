package demo.yc.lib.skin.attr;

import android.view.View;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:27
 * @detail:
 */

public class SkinAttr
{
    private String mResName;

    private SkinAttrType mResType;

    public SkinAttr(String mResName, SkinAttrType mResType)
    {
        this.mResName = mResName;
        this.mResType = mResType;
    }

    public void apply(View view)
    {
        mResType.apply(view,mResName);
    }

    public String getResName()
    {
        return mResName;
    }

    public void setResName(String mResName)
    {
        this.mResName = mResName;
    }

    public SkinAttrType getResType()
    {
        return mResType;
    }

    public void setResType(SkinAttrType mResType)
    {
        this.mResType = mResType;
    }
}
