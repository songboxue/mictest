package com.focustech.mic.common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * User: songboxue
 * Date: 2018/1/17
 * Description:
 */
public class ClientManager {

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
    //todo username只在set时传入，需要修改这边的实现方式和业务方法。
    private static String username;

    public static HttpClient getClient(String username) {
        HttpClient client = clientHolder.get(username);
        if(client == null){
            client = new DefaultHttpClient();
            clientHolder.put(username,client);
        }
        return client;
    }

    public static void setClient(String username,HttpClient client){
        if(clientHolder.get(username) == null){
            clientHolder.put(username,client);
        }else{
            closeClient(username);
            clientHolder.remove(username);
            clientHolder.put(username,client);
        }
    }

    public static void closeClient(String username){
        HttpClient client = clientHolder.get(username);
        if(client != null){
            client.getConnectionManager().shutdown();
            clientHolder.remove(username);
        }
    }
}
