package com.focustech.mic.common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * User: songboxue
 * Date: 2018/1/17
 * Description:
 */
public class ClientManager {

    private static ThreadLocal<HttpClient> clientHolder = new ThreadLocal<HttpClient>();

    public static HttpClient getClient() {
        HttpClient client = clientHolder.get();
        if(client == null){
            client = new DefaultHttpClient();
            clientHolder.set(client);
        }
        return client;
    }

    public static void setClient(HttpClient client){
        if(clientHolder.get() == null){
            clientHolder.set(client);
        }else{
            closeClient();
            clientHolder.remove();
            clientHolder.set(client);
        }
    }

    public static void closeClient(){
        HttpClient client = clientHolder.get();
        if(client != null){
            client.getConnectionManager().shutdown();
            clientHolder.remove();
        }
    }
}
