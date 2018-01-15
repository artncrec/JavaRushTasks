package com.javarush.task.task40.task4002;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://requestb.in/s7oo6ks7", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("User-Agent", "Mozilla/5.0");

        String[] params = urlParameters.split("&");
        List<NameValuePair> list = new ArrayList<>();
        for (String str : params) {
            String[] keyvalue = str.split("=");
            NameValuePair pair = keyvalue.length == 2 ? new BasicNameValuePair(keyvalue[0], keyvalue[1]) : new BasicNameValuePair(keyvalue[0], "");
            list.add(pair);
        }
        HttpEntity entity = new UrlEncodedFormEntity(list, Charset.defaultCharset());
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }
        bufferedReader.close();
        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
