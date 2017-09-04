package demo.yc.lib.skin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.yc.lib.skin.attr.SkinView;
import demo.yc.lib.skin.callback.ISkinChangeCallback;
import demo.yc.lib.skin.callback.ISkinChangedListener;
import demo.yc.lib.skin.config.Const;
import demo.yc.lib.utils.CommonUtil;
import demo.yc.lib.utils.LogUtil;
import demo.yc.lib.utils.SPUtil;

/**
 * @author: YC
 * @date: 2017/9/3 0003
 * @time: 11:17
 * @detail:
 *  功能：利用插件apk的资源，完成换肤
 *  问题：（1）如何加载并且获取资源
 *       （2）多个界面需要换肤，如何保存多个界面，并且如何通知多个界面换肤
 *       (3)一个view有多个属性值需要换肤，如何保存多个属性
 *       （4）一个界面有多个view需要换肤如何保存多个view
 *
 *       1、第一个问题，通过apkName,packName,和反射机制
 *       2、定义换肤接口，需要换肤的ui继承这个接口，
 *          然后在crete，destroy时候进行注册和反注册
 *          因此需要利用一个List<>来保存所有需要换肤的ui
 *       3、这里需要判断哪些属性需要换肤（通过资源的名称，符合自定义的名称就表示需要换肤）
 *          拥有需要换肤的属性的view才需要保存，否则不需要保存
 *          定义一个SkinView类，在SkinView内部利用list<></>用来保存所有需要换肤的属性
 *          同时还需要保存每个属性的资源类型（color还是drawable）
 *       4、一个界面有多个换肤的view,因此利用map来实现，界面和list<SkinView>映射
 *          因为界面注册在这里实现，因此这个功能也在这里实现
 *
 */

public class SkinManager
{
    private static SkinManager mInstance ;
    private Context mContext;
    private  AssetManager manager;
    private Resources superRes;
    private Resources mResource;

    private String currentApkPath;
    private String currentPckName;

    private List<ISkinChangedListener> listenerList ;
    private Map<ISkinChangedListener,List<SkinView>> mSkinViewMaps = new HashMap<>();



    private SkinManager(){
        listenerList = new ArrayList<>();
    }
    // 单例
    public static SkinManager getInstance()
    {
        if(mInstance == null)
        {
            synchronized (SkinManager.class)
            {
                if(mInstance == null)
                    mInstance = new SkinManager();
            }
        }
        return mInstance;
    }

    /**
     *  需要传入context进行资源获取
     * @param context
     */
    public void init(Context context)
    {
        mContext = context.getApplicationContext();
        getLocalData();
    }


    /**
     *  根据apk路径和pkg包名进行获取插件资源
     * @param apkPath
     * @param pkgName
     * @throws Exception
     */
    private void loginPlugin(String apkPath,String pkgName) throws Exception
    {
        LogUtil.w("skin",apkPath+"----"+pkgName);
        if(CommonUtil.isEmpty(apkPath) || CommonUtil.isEmpty(pkgName))
            return;
        if(apkPath.equals(currentApkPath) && pkgName.equals(currentPckName))
            return;

        manager = AssetManager.class.newInstance();
        Method method = manager.getClass().getMethod("addAssetPath",String.class);
        method.invoke(manager,apkPath);
        if(superRes == null)
            superRes = mContext.getResources();
        mResource = new Resources(manager,superRes.getDisplayMetrics()
                ,superRes.getConfiguration());

        currentApkPath = apkPath;
        currentPckName = pkgName;
        setLocalData();
    }

    /**
     *   获取插件的图片资源
     * @param resName  图片资源名称
     * @return
     */
    public Drawable getDrawableByPlugin(String resName)
    {
        try
        {
            return mResource.getDrawable(mResource.getIdentifier(
                    resName, "drawable", currentPckName));
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 获取插件的color资源
     * @param resName  颜色资源名称
     * @return
     */
    public ColorStateList getColorByPlugin(String resName)
    {
        try
        {
            return mResource.getColorStateList(mResource.getIdentifier(
                    resName,"color",currentPckName));
        }catch (Exception e)
        {
            return null;
        }
    }


    /**
     * 保存那些需要换肤的界面
     * @param listener
     */
    public void registerSkinChangeListener(ISkinChangedListener listener)
    {
        listenerList.add(listener);
    }

    /**
     * 去除那些已经不需要换肤的界面
     * @param listener
     */
    public void unregisterSkinChangeListener(ISkinChangedListener listener)
    {
        listenerList.remove(listener);
    }

    public List<SkinView> getSkinViews(ISkinChangedListener listener)
    {
        return mSkinViewMaps.get(listener);
    }

    public void addSkinViews(ISkinChangedListener listener,List<SkinView> views)
    {
        mSkinViewMaps.put(listener,views);
    }


    /**
     * 外界要换肤的时候，就是调用这个函数，也就是换肤的入口
     * 换肤成功需要通知所有需要换肤的ui(activity/fragment)
     * @param apkPath
     * @param pckName
     */
    public void changeSkin(final String apkPath, final String pckName,  ISkinChangeCallback callback)
    {
        if(callback == null)
            callback = ISkinChangeCallback.defaultCallBack;
        final ISkinChangeCallback call = callback;

        new AsyncTask<Void, Void, Integer>()
        {
            @Override
            protected void onPreExecute()
            {
                call.onStart();
            }
            @Override
            protected Integer doInBackground(Void... params)
            {
                int value = 1;
                try
                {
                    loginPlugin(apkPath,pckName);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    value = -1;
                }
                return value;
            }

            @Override
            protected void onPostExecute(Integer value)
            {
               if(value == 1)
               {
                   notifyAllListener();
                   call.onSuccess();
               }
                else
                   call.onError(new Exception("插件加载失败"));
            }
        }.execute();

    }

    /**
     * 换肤涉及到很多的界面
     * 因此需要通知所有的界面进行换肤
     */
    private void notifyAllListener()
    {
       for(ISkinChangedListener listener:listenerList)
       {
           changeItemSkin(listener);
           listener.updateSKin();
       }
    }

    public void changeItemSkin(ISkinChangedListener listener)
    {
        List<SkinView> skinViews = mSkinViewMaps.get(listener);
        if(mSkinViewMaps.get(listener) != null)
        {
            LogUtil.w("plugin",listener.toString()+"更新view的个数"+skinViews.size());
            for(SkinView view: skinViews)
            {
                view.apply();
            }
        }else
        {
            LogUtil.w("plugin",listener.toString()+"---views is null");
        }
    }

    public boolean isNeedLoadPlugin()
    {

        if(CommonUtil.isEmpty(currentApkPath))
            return false;

        if(CommonUtil.isEmpty(currentPckName))
            return false;

        return true;
    }


    public void getLocalData()
    {
        String apk  = (String) SPUtil.getSingle(mContext, Const.APK_KEY,"");
        String pck = (String) SPUtil.getSingle(mContext, Const.PCK_KEY,"");
        try
        {
            loginPlugin(apk,pck);
        }catch (Exception e)
        {
            e.printStackTrace();
            SPUtil.clearAll(mContext);
        }

        Log.w("file","get data==="+apk+"--"+pck);


    }

    public void setLocalData()
    {
        Log.w("file","set data==="+currentApkPath+"--"+currentPckName);
        SPUtil.putSingle(mContext, Const.APK_KEY,currentApkPath);
        SPUtil.putSingle(mContext,Const.PCK_KEY,currentPckName);
    }

   public void reset()
   {
       currentPckName = mContext.getPackageName();
       currentApkPath = null;
       SPUtil.clearAll(mContext);
       mResource = mContext.getResources();
       notifyAllListener();
   }
}
