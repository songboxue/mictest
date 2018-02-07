package com.focustech.mic.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.focustech.mic.common.ClientManager;
import com.focustech.mic.constants.CaseConst;
import com.focustech.mic.constants.ResponseConst;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: songboxue
 * Date: 2018/1/17
 * Description:
 */
public class HttpUtil {

    //无参get方法
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }

    //封装httpclient的get方法
    public static String doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpclient = ClientManager.getClient();
        try {
            HttpGet httpPost = new HttpGet(apiUrl);
            HttpResponse response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("执行状态码 : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送无参 POST 请求（HTTP）
     *
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<String, Object>());
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPost(String apiUrl, Map<String, Object> params) {
        HttpClient httpClient = ClientManager.getClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        HttpResponse response = null;
        HttpEntity entity = null;

        try {
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
            response = httpClient.execute(httpPost);
            System.out.println(response.toString());
            entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    entity.consumeContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPost(String apiUrl, Object json) {
        HttpClient httpClient = ClientManager.getClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        HttpResponse response = null;
        HttpEntity entity = null;

        try {
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    entity.consumeContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    public static String doPost(String apiUrl, String contentType, List<Header> headerList, String body) {
        //首先解析传入的Content-Type
        HttpEntity entity;
        if(contentType.contains(CaseConst.CONTENTTYPE_JSON)){
            entity = jsonEntity(body);
        }else if(contentType.contains(CaseConst.CONTENTTYPE_URLENCODED)){
            entity = formUrlencodedEntity(body);
        }else{
            entity = jsonEntity(body);
        }
        return doPost(apiUrl, headerList, entity);
    }

    /*
    将json字符串转换成httppost需要的entity
     */
    private static HttpEntity formUrlencodedEntity(String body) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();     //定义键值对列表
        try {
            JSONObject jo = JSON.parseObject(body);
            for (Map.Entry<String, Object> entry : jo.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }//向params设置数据
            HttpEntity reqEntity = new UrlEncodedFormEntity(params);//用UrlEncodedFormEntity对象包装请求体数据
            return reqEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    普通json字符串转为entity
     */
    private static HttpEntity jsonEntity(String body) {
        try {
            StringEntity entity = new StringEntity(body.toString());
            return entity;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String doPost(String apiUrl, List<Header> headerList, HttpEntity entity) {
        HttpClient httpClient = ClientManager.getClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        HttpResponse response = null;


        try{
            for(Header header : headerList){
                httpPost.addHeader(header);
            }
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    entity.consumeContent();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    /*
    从一个JSON字符串中根据key获取到对应的value
  */
    public static String getValueByKey(String str, String key) {
        JSONObject jo = JSON.parseObject(str);
        String value = jo.getString(key);
        return value == null ? "" : value;

    }

    /*
    根据不同的content-type将存储的json格式的消息体转换成需要的字符串
     */
    private String resolveBody(String body, String contentType) {
        String bodyStr;
        switch (contentType) {
            case CaseConst.CONTENTTYPE_JSON:
                //默认就是application/json
                bodyStr = body;
                break;
            case CaseConst.CONTENTTYPE_URLENCODED:
                //将json装换成普通表单形式
                bodyStr = JSONToUrlencoded(body);
                break;
            //其他的消息格式日后再写，如form-data
            default:
                bodyStr = body;
                break;
        }
        return bodyStr;
    }

    /*
    将json字符串装换成x-www-form-urlencoded形式的字符串
    因为我现在收集到的表单形式字符串都是key=value的格式，所以不做递归遍历了，能解析成啥样就啥样
     */
    private String JSONToUrlencoded(String jsonStr) {
        JSONObject jo = JSON.parseObject(jsonStr);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : jo.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


}
