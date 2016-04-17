package com.fengyun.information.util;


import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by xfeng on 16/3/7.
 */
public class ImageLoaderUtils {

    /**
     * 新闻详情的toolbar图片标记
     */
    public static final int NEWSDETAIL_TITLE = 0 ;
    private static DisplayImageOptions mNewsDetailToolBarOption ;

    private static Bitmap.Config defConfig = Bitmap.Config.RGB_565 ;

    private static boolean defIsCacheInMemory = false;
    private static boolean defIsCacheOnDisk = false;



    public static void loadingImage(int flag,String uri,ImageView imageView){
        loadingImage(flag,uri,imageView,0,0,0,defIsCacheInMemory,defIsCacheOnDisk);
    }

    public static void loadingImage(int flag , String uri,ImageView imageView , int loadingImageResouse,int loadingFailImageResonse,int emptyUriResonse,boolean isCacheInMemory,boolean isCacheOnDisk){
        loadingImage(flag,uri,imageView,loadingImageResouse,loadingFailImageResonse,emptyUriResonse,isCacheInMemory,isCacheOnDisk,defConfig,0);
    }

    public static void loadingImage(int flag , String uri,ImageView imageView , int loadingImageResouse,int loadingFailImageResonse,int emptyUriResonse , boolean isCacheInMemory,boolean isCacheOnDisk,Bitmap.Config bitmapConfig,int displayer){
        switch (flag){
            case NEWSDETAIL_TITLE:          //新闻详情
                if (mNewsDetailToolBarOption == null){
                    mNewsDetailToolBarOption = getOptions(loadingImageResouse,loadingFailImageResonse,emptyUriResonse,isCacheInMemory,isCacheOnDisk,bitmapConfig,displayer) ;
                }
                ImageLoader.getInstance().displayImage(uri,imageView,mNewsDetailToolBarOption);
                break;
            default:

                break;
        }
    }


    /**
     * 生成加载图片时的option选项
     *
     * @param loadingImageResouse
     *          加载时所显示的图片
     * @param loadingFailImageResonse
     *          加载失败时显示的图片
     * @param emptyUriResonse
     *          URI为空时所显示的图片
     * @param isCacheInMemory
     *          是否添加到内存缓存
     * @param isCacheOnDisk
     *          是否添加到硬盘缓存
     * @param bitmapConfig
     *          图片质量配置项：Bitmap.Config.RGB_565
     * @param displayer
     *          是否显示圆角
     * @return
     *          DisplayImageOptions
     */
    private static DisplayImageOptions getOptions(int loadingImageResouse,int loadingFailImageResonse,int emptyUriResonse , boolean isCacheInMemory,boolean isCacheOnDisk,Bitmap.Config bitmapConfig,int displayer){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loadingImageResouse)
                .showImageOnFail(loadingFailImageResonse)
                .showImageForEmptyUri(emptyUriResonse)
                .cacheInMemory(isCacheInMemory)
                .cacheOnDisk(isCacheOnDisk)
                .bitmapConfig(bitmapConfig)
                .displayer(new RoundedBitmapDisplayer(displayer))
                .build();

        return options ;

    }
}
