package com.fengyun.information.util;


import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: Fengyun_Information
 * ClassName: FengKits
 * User: xfeng
 * <p>
 * Date: 2016-03-11 12:09
 * <p>
 * Version : V 1.0.0
 * throws:
 *      徐峰的工具包
 */
public class FengKits {

    /** default join separator **/
    public static final String DEFAULT_JOIN_SEPARATOR = ",";



    /**********************************************集合工具类(List,map,set) *****************************************************/
    /**
     * get size of list
     *
     * <pre>
     * getSize(null)   =   0;
     * getSize({})     =   0;
     * getSize({1})    =   1;
     * </pre>
     *
     * @param <V>
     * @param sourceList
     * @return if list is null or empty, return 0, else return {@link List#size()}.
     */
    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     *
     * is null or its size is 0
     *
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1})    =   false;
     * </pre>
     *
     * @param <V>
     * @param sourceList
     * @return if list is null or its size is 0, return true, else return false.
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * 以默认方式把List内容组字符串，分隔符为：“,”
     * join list to string, separator is ","
     *
     * <pre>
     * join(null)      =   "";
     * join({})        =   "";
     * join({a,b})     =   "a,b";
     * </pre>
     *
     * @param list
     * @return join list to string, separator is ",". if list is empty, return ""
     */
    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * 以制定分隔符把List内的内容组成一个字符串
     * join list to string. if separator is null, use {@link #DEFAULT_JOIN_SEPARATOR}
     *
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     *
     * @param list
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    /********************************************** JSONUtils *****************************************************/

    public static <T> List<T> getNewsBeans(String jsonString,String value , Class<T> clazz){
        List<T> tList = new ArrayList<T>() ;
        if (TextUtils.isEmpty(jsonString)){
            return  null;
        }
        if (TextUtils.isEmpty(value)){
            return jsonArrayToObjectList(jsonString,clazz) ;
        }

        try{

            JsonParser parser = new JsonParser() ;
            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject() ;
            JsonElement jsonElement = jsonObject.get(value) ;
            if (jsonElement == null){
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray() ;
            for (int i=0 ; i < jsonArray.size() ;i++){
                JsonObject jo = jsonArray.get(i).getAsJsonObject() ;
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }
                if (!jo.has("imgextra")) {
                    tList.add(jsonObjectToObject(jo,clazz)) ;
                }
            }

        }catch (Exception e){

        }
        return tList ;
    }

    /**
     *
     * @param jsonString
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrayToObjectList(String jsonString , String value , Class<T> clazz){
        if (TextUtils.isEmpty(jsonString)){
            return  null;
        }
        if (TextUtils.isEmpty(value)){
            return jsonArrayToObjectList(jsonString,clazz) ;
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonString) ;
            JSONArray jsonArray = jsonObject.getJSONArray(value) ;
            return jsonArrayToObjectList(jsonArray,clazz) ;

        }catch (Exception e){
            return null ;
        }
    }

    /**
     * JSONArray 转 ObjectList
     * @param jsonarray
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrayToObjectList(JSONArray jsonarray , Class<T> clazz){
        List<T> tList = new ArrayList<T>() ;
        try {
            for (int i=0; i < jsonarray.length(); i++){
                JSONObject jobj = jsonarray.getJSONObject(i) ;
                tList.add(jsonObjectToObject(jobj.toString(),clazz)) ;
            }

        }catch (Exception e){

        }
        return tList ;
    }
    /**
     * JSonString 转化为ObjectList
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> List<T> jsonArrayToObjectList(String jsonString , Class<T> clazz){
        List<T> tList = new ArrayList<T>() ;
        try {
            JSONArray jsonarray = new JSONArray(jsonString) ;
            for (int i=0; i < jsonarray.length(); i++){
                JSONObject jobj = jsonarray.getJSONObject(i) ;
                tList.add(jsonObjectToObject(jobj.toString(),clazz)) ;
            }

        }catch (Exception e){

        }
        return  tList ;
    }

    /**
     * JSon 转化为object
     *
     * @param jsonString
     * @param clazz
     * @return
     *      object
     */
    public static <T> T jsonObjectToObject(String jsonString , Class<T> clazz){
        T t = null ;

        try {
            Gson gson = new Gson() ;
            t = gson.fromJson(jsonString,clazz) ;
        }catch (Exception e){

        }

        return t ;
    }

    /**
     * JSONObect to Object
     *
     * @param jsonObject
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonObjectToObject(JSONObject jsonObject , Class<T> clazz){
        return jsonObjectToObject(jsonObject.toString(),clazz) ;
    }

    public static <T> T jsonObjectToObject(JsonObject jsonObject , Class<T> clazz){
        T t = null ;
        if (jsonObject == null){
            return null ;
        }
        try {
            Gson gson = new Gson() ;
            t = gson.fromJson(jsonObject,clazz) ;
        }catch (Exception e){

        }
        return t ;
    }
    /********************************************** 屏幕相关处理 *****************************************************/

    public static final int getHeightInPx(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    public static final int getWidthInDp(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        int widthInDp = px2dip(context, width);
        return widthInDp;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

}
