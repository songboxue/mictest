package com.focustech.mic;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * User: msi
 * Date: 2018/1/16 21:09
 * Description:
 */
public class JTest {
    @org.junit.Test
    public void loginByCookie(){
        String url = "http://membercenter.cn.made-in-china.com/member/main/";
        String result = null;
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie","pid=jIzLjExMi4xNi43ODIwMTgwMTE2MjA1NDAyNTMwMjI2NDI2MzAM; JSESSIONID=abcgQSGB2JVkbf9Vjc-dw; __utmt=1; ccid=jUyMjc2NjgyOjAwN; lg_pn=WljY24b; _membership_session=eyJjX2wiOiIwIiwiaV9sIjoiMSIsImZfciI6IjEiLCJpX2JfdCI6IjAifQ==; lg_sts=QM; cn_cat=ERffjE4MzcwNTAwMDAUiEsfERffjM0MTUwNTAwMDAUiEsfERffjM0MDUwNjAwMDAUiEsfERffjE1NjYwMDAwMDAUiEsfGRffjE3MDgwMDAwMDAciEsfGRffjI3MzAwMDAwMDAciEsf2JffjI2MTAwMDAwMDAbiEsf2JffjMwMDUwNTAwMDAbiEsf2JffjE0MDMwMTAwMDAb; cn_pd=jkwNzQ2NTcyNiEsfjc1MDY0NDY1NiEsfjk2NjAyMjMyNiEsfjk0ODczNzIyNiEsfjkxMTIyMDk1NiEsfjcyOTMwMjc1NiEsfjY0OTA5NTg1NiEsfzA3ODQxMTc1NiEsfjg2NjU2Nzc1NiEsfjc2NTg3NzQ1NiEsfjc3MDI4MTEyNiEsfjc2OTg5NzcyN; cn_ob=zQ4MjkyMjIyNiEsfzM3NjEwMzE1NiEsfzQyNjc2NDA1NiEsfzQyNTQxMTc1NiEsfzE4NzMzODgyNiEsfzE4NzMzODkyNiEsfzQxNDA3NDY1NiEsfzIwMTg2OTc1NiEsfzQwMTY0MzI1NiEsfzI2NjM1Mjc1NiEsfzM3NTg3MDc1NiEsfzM4OTc2MDk1N; cn_kwd=com_%7E%E5%B0%81%E9%97%AD%E5%89%82%7E%21%2Coffer_%7E%E7%B3%BB%E7%BB%9F%E9%9B%86%E6%88%90%7E%21%2Ccom_%7E%E6%B2%A7%E9%BE%99%E6%95%B0%E7%A0%81%7E%21%2Ccom_%7E%E5%88%9B%E9%93%AD%E6%A0%87%E7%89%8C%7E%21%2Cprod_%7E%E8%BD%B4%E6%89%BF%7E%21%2Ccom_%7E%E9%9F%B6%E5%A4%A7%E8%BD%B4%E6%89%BF%7E%21%2Coffer_%7E%E6%B0%B4%E9%81%93%E5%9B%9E%E8%BD%AC%E7%81%AB%E9%94%85%7E%21%2Coffer_%7E%E5%8D%95%E9%92%88%E6%9C%BA%E9%92%88%E6%A2%AD%E9%85%8D%E4%BB%B6%7E%21%2Coffer_%7E%E5%B9%BF%E5%91%8A%E5%88%B6%E4%BD%9C%7E%21%2Coffer_%7Emp3%7E%21%2Cprod_%7Eled%7E%21%2Ccom_%7E%E5%B1%B1%E4%B8%9C%E4%B8%AD%E7%85%A4%E5%B7%A5%E7%9F%BF%E7%89%A9%E8%B5%84%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%7E%21%2Cprod_%7E0FPC%E9%94%99%E8%84%9A%E4%BD%8D%E8%BF%9E%E6%8E%A5%E5%99%A8%7E%21%2Cprod_%7E1.0FPC%E9%94%99%E8%84%9A%E4%BD%8D%E8%BF%9E%E6%8E%A5%E5%99%A8%7E%21%2Cprod_%7E%E6%B1%BD%E8%BD%A6%E5%BA%A7%E6%A4%85%7E%21%2Cprod_%7Emp3%7E%21%2Cprod_%7E%E9%A2%91%E7%8E%87%E5%85%83%E4%BB%B6%7E%21%2Cprod_%7E%E6%8D%86%E6%89%8E%E6%9C%BA%7E%21%2Cprod_%7E%E5%B9%BF%E5%91%8A%E5%99%A8%E6%9D%90%7E%21%2Cprod_%7E%E7%AD%96%E5%88%92; cn_com=jUzNzgxNTgyNiEsfjc4MDQwOTE1NiEsfjg0MzY4OTkyNiEsfjg0NjE1ODYyNiEsfjUyMjc2NjgyNiEsfTAzMjE0MTU0MiEsfjc5NDAxMDEyNiEsfzQxNQMiEsfjc5MjEzOTk1NiEsfjc2MTg0MDY1N; cn_tm=jAxOC0wMS0xNiAyMDo1NAM; _cn_aeps_ck_=miccn=yulikui2011=1DA863E5236E409F53E1EE94DDA97A6D; _cn_aeps_ln_=yulikui2011; _cn_display_ln_=yulikui2011; com_identity=jUyMjc2NjgyXzEN; key_cookie_logonstatus=G9GaXJzdExvZ2luU2VydmljZQZ; sf_img=AM; Hm_lvt_dcd77103e55fb7327c5d9c24690a3d89=1516107246; Hm_lpvt_dcd77103e55fb7327c5d9c24690a3d89=1516107356; __utma=144487465.2124231534.1516107246.1516107246.1516107246.1; __utmb=144487465.17.10.1516107246; __utmc=144487465; __utmz=144487465.1516107246.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); cid=jAxODAxMTYyMDU0MTIyNzMwMDAwOTY5MTY3MzUzMTEyMzY2MjMzOQM; sid=TA4MzgwNjEwMDg2NTIyNzg2OjIyMy4xMTIuMTYuNzg6NjUyMjc2NjgyOjAwM");
        try{
            HttpResponse resp = client.execute(httpGet);
            HttpEntity httpEntity = resp.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
