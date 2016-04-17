package com.fengyun.information.bean;

import com.fengyun.information.util.FengKits;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: Fengyun_Information
 * ClassName: ImageBean
 * User: xfeng
 * <p>
 * Date: 2016-03-20 15:44
 * <p>
 * Version : V 1.0.0
 * throws:
 */
public class ImageBean implements Serializable {

    public String title;
    public String thumburl;
    public String sourceurl;
    public int height;
    public int width;


    public static List<ImageBean> getImageBeans(String jsonString){
        return FengKits.jsonArrayToObjectList(jsonString,ImageBean.class) ;
    }
}
