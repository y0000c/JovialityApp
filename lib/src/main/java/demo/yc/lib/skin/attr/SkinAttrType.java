package demo.yc.lib.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.yc.lib.skin.SkinManager;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:27
 * @detail:
 */

public enum SkinAttrType
{
    BACKGROUND("background"){
        @Override
        public void apply(View view, String mResName)
        {
//            if(view instanceof Toolbar ||
//                    view instanceof TabLayout || view instanceof LinearLayout)
//            {
//                ColorStateList list = SkinManager.getInstance().getColorByPlugin(mResName);
//                if(list != null)
//                    view.setBackgroundColor(list.getDefaultColor());
//            }

            if(view instanceof ImageView)
            {
                Drawable draw = SkinManager.getInstance().getDrawableByPlugin(mResName);
                if(draw != null)
                    view.setBackground(draw);
            }else
            {
                ColorStateList list = SkinManager.getInstance().getColorByPlugin(mResName);
                if(list != null)
                    view.setBackgroundColor(list.getDefaultColor());
            }



        }
    },
    SRC("src"){
        @Override
        public void apply(View view, String mResName)
        {
            Drawable draw = SkinManager.getInstance().getDrawableByPlugin(mResName);
            if(draw != null)
                if(view instanceof ImageView)
                {
                    ((ImageView)view).setImageDrawable(draw);
                }
        }
    },
    TEXT_COLOR("textColor"){
        @Override
        public void apply(View view, String mResName)
        {
            ColorStateList list = SkinManager.getInstance().getColorByPlugin(mResName);
            if(list != null)
                if(view instanceof TextView)
                {
                    ((TextView)view).setTextColor(list);
                }
        }
    };

    private String mResType;
    SkinAttrType(String type)
    {
        mResType = type;
    }

    public String getResType()
    {
        return mResType;
    }
    public abstract void apply(View view, String mResName);
}
