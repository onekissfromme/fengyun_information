package com.fengyun.information.news.presenter;

import android.content.Context;

import com.fengyun.information.news.model.NewsDetailModel;
import com.fengyun.information.news.model.NewsDetailModelImpl;
import com.fengyun.information.news.view.INewsDetailView;

/**
 * ProjectName: Fengyun_Information
 * ClassName: NewsDetailPresenterImpl
 * User: xfeng
 * <p>
 * Date: 2016-04-19 22:09
 * <p>
 * Version : V 1.0.0
 * throws:
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter {

    private Context mContext ;
    private INewsDetailView newsDetailView ;
    private NewsDetailModel newsDetailModel ;

    public NewsDetailPresenterImpl(Context context , INewsDetailView newsDetailView) {
        this.mContext = context ;
        this.newsDetailView = newsDetailView;
        this.newsDetailModel = new NewsDetailModelImpl(context) ;
    }

    @Override
    public void newsDetailLoad() {
    }
}
