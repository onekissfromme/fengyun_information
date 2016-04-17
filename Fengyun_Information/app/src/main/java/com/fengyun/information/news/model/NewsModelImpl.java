package com.fengyun.information.news.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fengyun.information.news.view.NewsFragment;
import com.fengyun.information.bean.NewsBean;
import com.fengyun.information.util.Urls;

import java.util.List;

/**
 * ProjectName: Fengyun_Information
 * ClassName: NewsModelImpl
 * User: xfeng
 * <p/>
 * Date: 2016-03-08 17:34
 * <p/>
 * Version : V 1.0.0
 * throws:
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadingNews(Context context , String url, int type, OnLoadingNewsListListener onLoadingNewsListListener) {
        post(context,url,type,onLoadingNewsListListener);
    }

    private void post(Context context , String url, final int type, final OnLoadingNewsListListener onLoadingNewsListListener){
        Log.i("Test",url) ;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String contentid = getID(type) ;
                List<NewsBean> newsBeans = NewsBean.getNewsBeans(response, contentid) ;
                onLoadingNewsListListener.onSuccess(newsBeans);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onLoadingNewsListListener.onFailed(error.getMessage(),error);
            }
        });

        requestQueue.add(stringRequest) ;

    }

//    private void post(Context context , String url, int type, final OnLoadingNewsListListener onLoadingNewsListListener){
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                //TODO 处理请求成功时的返回结果
//                List<NewsBean> newsBeans = FengKits.jsonArrayToObjectList(response, NewsBean.class) ;
//                onLoadingNewsListListener.onSuccess(newsBeans);
//                Log.e("Test","Response Success");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //TODO 处理请求失败
//                Log.e("Test",error.getMessage()) ;
//                onLoadingNewsListListener.onFailed(error.getMessage(),error);
//            }
//        }) ;
//
//        requestQueue.add(jsonArrayRequest) ;
//
//    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    /**
     * 新闻列表的请求状态接口
     */
    public interface OnLoadingNewsListListener{
        void onSuccess(List<NewsBean> newsList) ;
        void onFailed(String msg , Exception e) ;
    }

    /**
     * 新闻详情的请求状态接口
     */
    public interface OnLoadingNewsDaitelListener{
        void onSuccess(NewsBean newsBean) ;
        void onFailed(String msg , Exception e) ;
    }
}
