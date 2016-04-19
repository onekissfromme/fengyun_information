package com.fengyun.information.news.model;

import android.content.Context;

import com.fengyun.information.bean.NewsBean;

/**
 * ProjectName: Fengyun_Information
 * ClassName: NewsDetailModelImpl
 * User: xfeng
 * <p>
 * Date: 2016-04-19 22:22
 * <p>
 * Version : V 1.0.0
 * throws:
 *      新闻详情的model实现类
 */
public class NewsDetailModelImpl implements NewsDetailModel{
    private Context mContext ;

    public NewsDetailModelImpl(Context context) {
       this.mContext = context ;
    }

    @Override
    public void loadingNewsDetail(String url, int type, OnLoadingNewsDaitelListener listener) {

    }

    /**
     * 新闻详情的请求状态接口
     */
    public interface OnLoadingNewsDaitelListener{
        void onSuccess(NewsBean newsBean) ;
        void onFailed(String msg , Exception e) ;
    }
}
