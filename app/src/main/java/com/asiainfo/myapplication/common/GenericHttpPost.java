package com.asiainfo.myapplication.common;

import android.content.Context;
import android.util.Log;


import com.asiainfo.myapplication.entity.HttpResponseEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.net.Proxy.Type.HTTP;


public class GenericHttpPost {
    
    HttpPost httpPost;
    HttpParams httpParameters;
    HttpResponse httpResponse;
    List<NameValuePair> params;
    DefaultHttpClient client;
    Context ctx;
    
    public GenericHttpPost(String url, List<NameValuePair> params, Context ctx) {
    	this.ctx = ctx;
        httpPost = new HttpPost(url);
        httpParameters = new BasicHttpParams();
        if (null == params) {
            this.params = new ArrayList<NameValuePair>();
        } else {
            this.params = params;
        }
        HttpConnectionParams.setConnectionTimeout(httpParameters, Const.HTTP_TIMEOUT);
        client = new DefaultHttpClient(httpParameters);
    }
    
    public void cancel() {
        Log.e("cancel", "user cancel");
        client.getConnectionManager().shutdown();
    }

    public HttpResponseEntity excute() {
        HttpResponseEntity entity = new HttpResponseEntity();

        String code = "";
        String content = "";
        try {
//        	httpPost.setHeader("Cookie", ((MyApplication)(this.ctx.getApplicationContext())).getSessionId());
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            httpResponse = client.execute(httpPost);
            
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 取得返回结果
            	 
                JSONObject json = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
                code = "0";
                content = json.toString();
                
                if (code.equals(Const.HTTP_CODE_PHONE_ERROR)) {
                    throw new Exception("服务器错误，请联系管理员");
                }

                // 保存cookie
//                List<Cookie> cookies = client.getCookieStore().getCookies();
//                if (!cookies.isEmpty()) {
//                    for (int i = 0; i < cookies.size(); i++) {
//                        CookieUtil.cookie = cookies.get(i);
//                    }
//                }
            }
        } catch (ClientProtocolException e) {
            code = "801";
            content = "ClientProtocolException:" + e.getMessage();
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            code = "802";
            content = "无法连接到服务器，请稍候重试";
            e.printStackTrace();
        } catch (IOException e) {
            code = "803";
            content = "IOException" + e.getMessage();
            e.printStackTrace();
        } catch (ParseException e) {
            code = "804";
            content = "IOException" + e.getMessage();
            e.printStackTrace();
        } catch (JSONException e) {
            code = "805";
            content = "JSONException" + e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            code = "899";
            content = "Exception" + e.getMessage();
            e.printStackTrace();
        } finally {
            entity.setCode(code);
            entity.setContent(content);
        }

        return entity;
    }
}