package com.fengyun.information.news.presenter;

import android.content.Context;
import android.util.Log;

import com.fengyun.information.news.view.NewsFragment;
import com.fengyun.information.bean.NewsBean;
import com.fengyun.information.news.model.NewsModel;
import com.fengyun.information.news.model.NewsModelImpl;
import com.fengyun.information.util.Urls;
import com.fengyun.information.news.view.INewsView;

import java.util.List;

/**
 * ProjectName: Fengyun_Information
 * ClassName: NewsPresenterImpl
 * User: xfeng
 * <p/>
 * Date: 2016-03-08 17:23
 * <p/>
 * Version : V 1.0.0
 * throws: 加载新闻列表
 */
public class NewsPresenterImpl implements NewsPresenter , NewsModelImpl.OnLoadingNewsListListener{
    private static final String TAG = "NewsPresenterImpl" ;

    private Context mContext ;
    private INewsView newsView ;
    private NewsModel newsModel ;


    public NewsPresenterImpl(Context context , INewsView newsView) {
        this.mContext = context ;
        this.newsView = newsView;
        this.newsModel = new NewsModelImpl();
    }


    @Override
    public void newsLoading(int type, int page) {
        String url = getUrl(type, page) ;

        Log.d(TAG, url) ;
        newsModel.loadingNews(mContext,url,type,this);
    }

    /**
     * 根据类别和页面索引创建url
     *
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> newsList) {
        newsView.addViews(newsList);
        newsView.hideProgress();
    }

    @Override
    public void onFailed(String msg, Exception e) {
        newsView.showLoadingFialMsg(msg);
        newsView.hideProgress();
    }
}
