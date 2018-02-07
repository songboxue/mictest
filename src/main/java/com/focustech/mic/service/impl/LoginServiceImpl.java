package com.focustech.mic.service.impl;

import com.focustech.mic.common.ClientManager;
import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.service.ILoginService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: msi
 * Date: 2018/1/16 22:12
 * Description:
 */
@Service("iLoginService")
public class LoginServiceImpl implements ILoginService {
    @Override
    public ServerResponse setCookie(String cookie) {

    //接收客户端提交的cookie字符串，并以此创建httpclient对象
    //httpclient存储在当前线程中，做是否存在判断；
    //创建完成后，检查是否登录成功，返回对应的success or error
        return null;
    }

    @Override
    public ServerResponse loginVOByUsername(String username, String password) throws IOException {
        String sso;//发到sso 的响应结果
        String cdResult = loginCD(username,password); //先发送请求到CD
        //正则拼接新url登录vo
        String regex = "createHiddenIframe\\(\\\"(http://membercenter.+?)\\\"";
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cdResult);
        while(matcher.find()){
            sb.append(matcher.group(1)).append("&callback=focusSSOController.doCrossDomainCallBack&scriptId=ssoscript0");
        }
        sso = loginNewUrl(sb.toString());
        if(sso.contains("ssoscript0")){
            return ServerResponse.success();
        }
        return ServerResponse.error(1,"登录VO失败");
    }

    @Override
    public String getVoIndexPage(){
        HttpClient client = null;
        try {
            client = ClientManager.getClient();
        } catch (Exception e) {
            e.printStackTrace();
            return "Get client error";
        }
        String indexUrl = "http://membercenter.cn.made-in-china.com/member/main/";
        String respstr;
        HttpGet get = new HttpGet(indexUrl);
        HttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            respstr = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return "get resp error";
        }
        return respstr;
    }

    private String loginCD(String username,String password) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpHost proxy = new HttpHost("192.168.16.67", 8080);
        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        String cdUrl = "http://cd.abiz.com/logon/gbk";
        HttpPost post = new HttpPost(cdUrl);
        //添加所需要的post内容
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("pn", "miccn"));
        nvps.add(new BasicNameValuePair("tab", "miccn"));
        nvps.add(new BasicNameValuePair("callback", "parent.focusSSOController.callFeedback"));
        nvps.add(new BasicNameValuePair("rememberLogUserNameFlag", "0"));
        nvps.add(new BasicNameValuePair("encoding", "GBK"));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps);
        post.setEntity(formEntity);   //UrlEncodedFormEntity类创建的对象可以模拟传统的HTML表单传送POST请求中的参数

        HttpResponse response = client.execute(post);//执行post请求
        HttpEntity entity = response.getEntity();
        String cdResult = EntityUtils.toString(entity);

        ClientManager.setClient(username,client);//把当前client放到ThreadLocal

        entity.consumeContent();//关闭流

        return cdResult;
    }

    private String loginNewUrl(String newUrl) throws IOException {
        HttpClient client = null;
        String result;
        try {
            client = ClientManager.getClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpGet get = new HttpGet(newUrl);
        HttpResponse response = client.execute(get);

        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity);
        entity.consumeContent();
        return result;
    }

}
