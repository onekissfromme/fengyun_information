package com.fengyun.information.bean;

import com.fengyun.information.util.FengKits;

import java.io.Serializable;
import java.util.List;

/**
 * 新闻对象的实体bean
 *
 * Created by xfeng on 16/3/6.
 */
public class NewsBean implements Serializable {

    /**
     * docid
     */
    public String docid;
    /**
     * 标题
     */
    public String title;
    /**
     * 小内容
     */
    public String digest;
    /**
     * 图片地址
     */
    public String imgsrc;
    /**
     * 来源
     */
    public String source;
    /**
     * 时间
     */
    public String ptime;
    /**
     * TAG
     */
    public String tag;
    /**
     * 类型，判断是否跳过
     */
    public String skipType ;
    /**
     * TAGS
     */
    public String TAGS ;
    /**
     * 图片集合
     */
    public String[] imgextra ;
    /**
     * 子新闻列表 abs
     */
    public List<NewsBeanSubItem> abs ;


    /**
     * jsonArray转对象集合
     *
     * @param jsonString
     * @return
     *     NewsBean 的集合
     */
    public static List<NewsBean> getNewsBeans(String jsonString,String valueId){

        return FengKits.getNewsBeans(jsonString,valueId, NewsBean.class) ;
    }

    /**
     * json对象转实体对象
     * @param jsonString
     * @return
     *      NewsBean
     */
    public static NewsBean jsonObjectToObject(String jsonString){

        return FengKits.jsonObjectToObject(jsonString,NewsBean.class) ;
    }
}
