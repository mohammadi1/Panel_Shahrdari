package com.ipa.Panel_Shahrdari;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;

import com.ipa.Tools.ValueKeeper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

public class Panel_shahrdariapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Create global configuration and initialize ImageLoader with this
        // configuration
        File cacheDir = new File(ValueKeeper.DataBasePath + "/CatchedImages/");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(480, 800) // default
                // =
                // device
                // screen
                // dimensions
                .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
                .denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024).discCache(new UnlimitedDiscCache(cacheDir)) // default
                .discCacheSize(50 * 1024 * 1024).discCacheFileCount(100).discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
            // .showStubImage(R.drawable.ic_stub)
            .showImageForEmptyUri(R.drawable.camera1).showImageOnFail(R.drawable.animated_loading_blue)
                    // .resetViewBeforeLoading(false) // default
                    // .delayBeforeLoading(1000)
            .cacheInMemory(true) // default
            .cacheOnDisc(true) // default
            .build();


}
