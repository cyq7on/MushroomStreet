package com.cyq7on.mushroomstreet;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;

import com.cyq7on.mushrommstreet.bean.ShoppingDetailVo;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class AppConfig extends Application{
	
	//装入购物车的商品
	public static List<ShoppingDetailVo> goodsList =
			new ArrayList<ShoppingDetailVo>();
	
	public static WindowManager wm;
	//一些图片url
	public static String[] urlImage = {
			"http://img.my.csdn.net/uploads/201409/13/1410596120_3038.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596120_5547.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596120_3004.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596119_9776.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596104_5488.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596104_8745.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596103_3957.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596103_8317.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410596103_9998.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410597589_5515.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410597589_9693.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410597589_6918.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410597873_8942.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410597872_6670.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410597873_1547.jpg",
			"http://img.my.csdn.net/uploads/201409/13/1410598242_8578.jpg"};
	//显示图片的配置  
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
