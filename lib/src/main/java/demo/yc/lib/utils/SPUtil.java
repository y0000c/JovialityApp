package demo.yc.lib.utils;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 这个是封装sharePreferences的工具类
 */
public class SPUtil
{
    /**
     * 文件名称
     */
    private static final String FILE_NAME = "my_sp";

    /**
     * 获取sp
     *
     * @param context
     * @return
     */
    private static SharedPreferences initSp(Context context)
    {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND);
    }

    // =================这是 put 相关函数=============================

    /**
     * 实现插入的方法
     *
     * @param editor
     * @param key
     * @param value
     */
    private static void putValue(SharedPreferences.Editor editor, String key, Object value)
    {
        if (value instanceof Integer)
            editor.putInt(key, (Integer) value);
        else if (value instanceof Float)
            editor.putFloat(key, (Float) value);
        else if (value instanceof Long)
            editor.putLong(key, (Long) value);
        else if (value instanceof String)
            editor.putString(key, (String) value);
        else if (value instanceof Boolean)
            editor.putBoolean(key, (Boolean) value);
        else if (value instanceof Set)
            editor.putStringSet(key, (Set) value);
        else
            throw new RuntimeException("sp : insertd value type is error");

    }

    /**
     * 外界调用，适用于单个value值的情况
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     * @return 提交是否成功
     */
    public static boolean putSingle(Context context, String key, Object value)
    {
        SharedPreferences.Editor editor = initSp(context).edit();
        putValue(editor, key, value);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 外界调用，适用于多个值的情况
     *
     * @param context 上下文
     * @param map     HashMap<>   传入多个参数
     * @return
     */
    public static boolean putList(Context context, HashMap<String, Object> map)
    {
        SharedPreferences.Editor editor = initSp(context).edit();
        for (Map.Entry<String, Object> item : map.entrySet())
        {
            putValue(editor, item.getKey(), item.getValue());
        }

        boolean result = editor.commit();

        return result;
    }

    // =================这是 get 相关函数=============================

    private static Object getValue(SharedPreferences sp,String key,Object defValue)
    {
        if (defValue instanceof Integer)
            return sp.getInt(key,(Integer) defValue);
        else if (defValue instanceof Float)
            return sp.getFloat(key, (Float) defValue);
        else if (defValue instanceof Long)
            return sp.getLong(key, (Long) defValue);
        else if (defValue instanceof String)
            return sp.getString(key, (String) defValue);
        else if (defValue instanceof Boolean)
            return sp.getBoolean(key, (Boolean) defValue);
        else if (defValue instanceof Set)
            return sp.getStringSet(key, (Set) defValue);
        else
            throw new RuntimeException("sp : get defvalue type is error");
    }

    /**
     * 外界调用，获取单个值
     * @param context       上下文
     * @param key          键
     * @param defValue  默认值
     * @return
     */
    public static Object getSingle(Context context,String key,Object defValue)
    {
        SharedPreferences sp = initSp(context);
        return getValue(sp,key,defValue);

    }

    /**
     * 外界调用，适用于获取多个值
     * @param context
     * @param map        传入的键和对应的默认值
     * @return 返回HashMap
     */
    public static HashMap<String,Object> getList(Context context, HashMap<String,Object> map)
    {
        SharedPreferences sp = initSp(context);
        HashMap<String,Object> resultMap = new HashMap<>();
        for(Map.Entry<String,Object> item:map.entrySet())
        {
            resultMap.put(item.getKey(),getValue(sp,item.getKey(),item.getValue()));
        }
        return resultMap;
    }

    /**
     * 外界调用，适用于获取所有的键值对情况
     * @param context
     * @return
     */
    public static Map<String,?> getALL(Context context)
    {
        return initSp(context).getAll();
    }


    // =================这是 remove 相关函数=============================

    /**
     * 移除键值对的具体实现
     * @param editor
     * @param key
     */
    private static void remove(SharedPreferences.Editor editor,String key)
    {
        editor.remove(key);
    }

    /**
     * 外界调用，适用于移除单个键值对的情况
     * @param context
     * @param key  要移除的键
     * @return
     */
    public static boolean removeSingle(Context context,String key)
    {
        SharedPreferences.Editor editor = initSp(context).edit();
        remove(editor,key);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 外界调用，用于移除多个键值对
     * @param context
     * @param list   多个 key  的值
     * @return
     */
    public static boolean removeList(Context context, List<String> list)
    {
        SharedPreferences.Editor editor = initSp(context).edit();
        for(String key:list)
            remove(editor,key);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 删除所有键值对
     * @param context
     * @return
     */
    public static boolean clearAll(Context context)
    {
        boolean result = initSp(context).edit().clear().commit();

        return result;
    }

    /**
     * 外界调用，用于判断是否存在该键
     * @param context
     * @param key
     * @return
     */
    public static boolean containsKey(Context context,String key)
    {
        return initSp(context).contains(key);
    }


}
