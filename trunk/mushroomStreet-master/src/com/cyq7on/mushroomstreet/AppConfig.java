package com.cyq7on.mushroomstreet;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class AppConfig extends Application{
	
	public static WindowManager wm;
	
	//��ʾͼƬ������  
    public static DisplayImageOptions options = 
    		new DisplayImageOptions.Builder()  
	//      .showImageOnLoading(R.drawable.ic_launcher)  
	//      .showImageOnFail(R.drawable.ic_launcher)  
	      .cacheInMemory(true)  
	      .cacheOnDisk(true)  
	      .bitmapConfig(Bitmap.Config.RGB_565)  
	      .build();
	
	public static void initConfig(Context context){
		initImageLoader(context);
		wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
	}
	
	public static void initImageLoader(Context context) {
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.denyCacheImageMultipleSizesInMemory()
			.diskCacheFileNameGenerator(new Md5FileNameGenerator())
			.diskCacheSize(100 * 1024 * 1024) // 100 Mb
			.tasksProcessingOrder(QueueProcessingType.LIFO)
			.writeDebugLogs() // Remove for release app
			.build();
		ImageLoader.getInstance().init(config);
	}
}
