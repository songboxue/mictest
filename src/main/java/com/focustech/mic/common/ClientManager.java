package com.focustech.mic.common;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * User: songboxue
 * Date: 2018/1/17
 * Description:
 */
public class ClientManager {

    //todo 使用单例模式实现安全的httpclient工具类
//    private static ThreadLocal<HttpClient> clientHolder = new ThreadLocal<HttpClient>();
//
//    public static HttpClient getClient() {
//        HttpClient client = clientHolder.get();
//        if(client == null){
//            client = new DefaultHttpClient();
//            clientHolder.set(client);
//        }
//        return client;
//    }
//
//    public static void setClient(HttpClient client){
//        if(clientHolder.get() == null){
//            clientHolder.set(client);
//        }else{
//            closeClient();
//            clientHolder.remove();
//            clientHolder.set(client);
//        }
//    }
//
//    public static void closeClient(){
//        HttpClient client = clientHolder.get();
//        if(client != null){
//            client.getConnectionManager().shutdown();
//            clientHolder.remove();
//        }
//    }
    /*
    根据当前登录的用户名，管理当前httpclient对象，可在程序其他地方调用
     */
    private static Map<String,HttpClient> clientHolder = new HashMap<>();

    private static String username;

    private static HttpHost proxy = new HttpHost("192.168.16.67",8080);

    public static HttpClient getClient() {
        HttpClient client = clientHolder.get(username);
        if(client == null){
            client = new DefaultHttpClient();
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
            username = "inituser";
            clientHolder.put(username,client);
        }
        return client;
    }

    public static void setClient(String s,HttpClient client){
        if(clientHolder.get(s) == null){
            clientHolder.put(s,client);
        }else{
            closeClient();
            clientHolder.remove(s);
            clientHolder.put(s,client);
        }
        username = s;
    }

    public static void closeClient(){
        HttpClient client = clientHolder.get(username);
        if(client != null){
            client.getConnectionManager().shutdown();
            clientHolder.remove(username);
        }
    }
}
