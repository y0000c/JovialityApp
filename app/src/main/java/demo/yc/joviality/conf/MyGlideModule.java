package demo.yc.joviality.conf;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author: YC
 * @date: 2017/8/24 0024
 * @time: 10:15
 * @detail:
 */

@GlideModule
public class MyGlideModule extends AppGlideModule
{
    @Override
    public boolean isManifestParsingEnabled()
    {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder)
    {
        int cacheSize = 1024*1024*100;
        FilePath.createDir(FilePath.cachePath);
        builder.setDiskCache(new DiskLruCacheFactory(FilePath.cachePath, cacheSize));
    }
}
