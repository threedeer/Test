package com.example.okhttp.okhttpdemo;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import java.util.concurrent.TimeUnit;

/**
 * Created by hasee-pc on 2016/7/1.
 */
public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    static {
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * 不会开启异步线程
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     * @param request
     * @param responseCallback
     */
    public static void enequen(Request request,Callback responseCallback){
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    public static void enquen(Request request){
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    /**
     * 得到服务端返回
     * @param url
     * @return
     * @throws IOException
     */
    public static String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()){
            String responseUrl = response.body().toString();
            return responseUrl;
        }else {
            throw new IOException("Unexpected code" + response);
        }
    }


    private static final String CHARSET_NAME="UTF-8";
    public String formatParams (List<BasicNameValuePair> params ){

    }



}
