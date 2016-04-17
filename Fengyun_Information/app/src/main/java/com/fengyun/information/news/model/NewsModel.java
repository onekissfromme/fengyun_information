package com.fengyun.information.news.model;

import android.content.Context;

import com.fengyun.information.news.model.NewsModelImpl;

/**
 * ProjectName: Fengyun_Information
 * ClassName: NewsModel
 * User: xfeng
 * <p/>
 * Date: 2016-03-08 17:32
 * <p/>
 * Version : V 1.0.0
 * throws:  新闻列表数据请求model接口
 */
public interface NewsModel {

    void loadingNews(Context context , String url , int type , NewsModelImpl.OnLoadingNewsListListener onLoadingNewsListListener) ;
}
