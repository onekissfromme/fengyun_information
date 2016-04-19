package com.fengyun.information.news.model;

/**
 * ProjectName: Fengyun_Information
 * ClassName: NewsDetailModel
 * User: xfeng
 * <p>
 * Date: 2016-04-19 22:19
 * <p>
 * Version : V 1.0.0
 * throws:
 *      新闻详情的model处理类
 */
public interface NewsDetailModel {

    void loadingNewsDetail(String url , int type,NewsDetailModelImpl.OnLoadingNewsDaitelListener listener) ;
}
