package com.fengyun.information.news.view;

/**
 * ProjectName: Fengyun_Information
 * ClassName: INewsDetailView
 * User: xfeng
 * <p>
 * Date: 2016-03-27 08:28
 * <p>
 * Version : V 1.0.0
 * throws:
 *
 * 新闻详情页的接口标准类
 */
public interface INewsDetailView {

    /**
     * 展示新闻详情
     */
    public void showNewsDetail() ;

    /** 显示进度条 **/
    public void showProgress() ;

    /** 隐藏进度条 **/
    public void hideProgress() ;

    /** 显示新闻加载失败提示信息 **/
    public void showLoadingFialMsg(String errorMsg) ;


}
