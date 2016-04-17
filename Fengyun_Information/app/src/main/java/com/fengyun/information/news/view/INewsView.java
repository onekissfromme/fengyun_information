package com.fengyun.information.news.view;

import com.fengyun.information.bean.NewsBean;

import java.util.List;

/**
 * Created by xfeng on 16/3/6.
 */
public interface INewsView {

    /** 显示进度条 **/
    public void showProgress() ;

    /** 添加新闻列表 **/
    public void addViews(List<NewsBean> newList) ;

    /** 隐藏进度条 **/
    public void hideProgress() ;

    /** 显示新闻加载失败提示信息 **/
    public void showLoadingFialMsg(String errorMsg) ;
}
