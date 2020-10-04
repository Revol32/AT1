package TZ1;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunnerAPI {


    private final String url = "https://test.uxcrowd.ru:443/api/authentication";


    @Test
    public void HttpTest () throws IOException {
/*
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("accept", "application/json, text/plain, ");
        httpPost.addHeader("accept-encoding","gzip, deflate, br");
        httpPost.addHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Host","test.uxcrowd.ru");
        httpPost.addHeader("origin","https://test.uxcrowd.ru");
        httpPost.addHeader("Referer",	"https://test.uxcrowd.ru/");
        httpPost.addHeader("sec-fetch-dest","empty");
        httpPost.addHeader("sec-fetch-mode","cors");
        httpPost.addHeader("sec-fetch-site","same-origin");
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        httpPost.addHeader("x-xsrf-token","fae6792d-cc98-4b13-a78f-9edffebd26e4");

        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("XSRF-TOKEN", "fae6792d-cc98-4b13-a78f-9edffebd26e4");
        cookie.setDomain("https://test.uxcrowd.ru");
        cookie.setPath("/api/authentication");
        cookieStore.addCookie(cookie);
        List<NameValuePair> request = new ArrayList<>();
        request.add(new BasicNameValuePair("username","brizer@inbox.ru"));
        request.add(new BasicNameValuePair("password","7EGDDl"));
        request.add(new BasicNameValuePair("remember-me","undefined"));
        request.add(new BasicNameValuePair("submit","Login"));
        HttpEntity send = new UrlEncodedFormEntity(request, "UTF-8");
        HttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        System.out.println(httpPost);
        httpPost.setEntity(send);
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println(response);*/


        Map httpHead = new HashMap<String,String>();
        httpHead.put("accept", "application/json, text/plain, ");
        httpHead.put("accept-encoding","gzip, deflate, br");
        httpHead.put("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        httpHead.put("content-type", "application/x-www-form-urlencoded");
        httpHead.put("Host","test.uxcrowd.ru");
        httpHead.put("origin","https://test.uxcrowd.ru");
        httpHead.put("Referer",	"https://test.uxcrowd.ru/");
        httpHead.put("sec-fetch-dest","empty");
        httpHead.put("sec-fetch-mode","cors");
        httpHead.put("sec-fetch-site","same-origin");
        httpHead.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        httpHead.put("x-xsrf-token","fae6792d-cc98-4b13-a78f-9edffebd26e4");

        Map request = new HashMap<String,String>();
        request.put("username","brizer@inbox.ru");
        request.put("password","7EGDDl");
        request.put("remember-me","undefined");
        request.put("submit","Login");


        HttpResponse<JsonNode> response = Unirest.post(url).headers(httpHead).cookie("XSRF-TOKEN", "fae6792d-cc98-4b13-a78f-9edffebd26e4").fields(request).asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders());
        System.out.println(response.getCookies());
        System.out.println(response.getBody());
        System.out.println(response);



    }
}
