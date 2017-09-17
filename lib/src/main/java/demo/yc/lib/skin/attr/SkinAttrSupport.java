package demo.yc.lib.skin.attr;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import demo.yc.lib.skin.config.Const;


/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:51
 * @detail:
 */

public class SkinAttrSupport
{
    public static List<SkinAttr> getSkinAttrs(AttributeSet attrs, Context context)
    {
        List<SkinAttr> list = new ArrayList<>();
        SkinAttr attrItem = null;
        SkinAttrType attrType = null;

        for(int i=0,n=attrs.getAttributeCount();i<n;i++)
        {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);

      //      LogUtil.w("skin",attrName+"============="+attrValue);

            if (attrValue.startsWith("@"))
            {
                int id = -1;
                try
                {
                    id = Integer.parseInt(attrValue.substring(1));
                } catch (Exception e)
                {
                  //  e.printStackTrace();
                }
                if (id == -1)
                    continue;
                String resName = "";
                try
                {
                    resName = context.getResources().getResourceEntryName(id);
                }catch (Exception e)
                {
                    continue;
                }

                if (resName.startsWith(Const.SKIN_PREFIX))
                {
                    attrType = getSupportAttrType(attrName);

                 //   LogUtil.w("skin", "attrType-----------"+attrType);
                    if (attrType == null)
                        continue;
                    attrItem = new SkinAttr(resName, attrType);
                    list.add(attrItem);
                }
            }
        }
            return list;
    }

    private static SkinAttrType getSupportAttrType(String resName)
    {
        for(SkinAttrType type : SkinAttrType.values())
        {
            if(type.getResType().equals(resName))
                return type;
        }
        return null;
    }
}
