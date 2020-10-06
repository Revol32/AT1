package TZ1;

import kong.unirest.*;
import kong.unirest.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RunnerAPI {


    private final String urlAuh = "https://test.uxcrowd.ru:443/api/authentication";
    private final String urlReg = "https://test.uxcrowd.ru:443/api/register";
    private final String login = "brizer@inbox.ru";
    private final String loginCustomer = "brizer@ultra-web.ru";
    private final String passtrue = "7EGDDl";
    private final String passtrueCustomer = "lp1wT2";
    private final String pass = "123412";
    private HttpResponse<JsonNode> response;


    public String genMail () {
        StringBuilder genMail = new StringBuilder();
        genMail.append(genString(8)).append('@').append(genString(6)).append(".ru");
        System.out.println(genMail.toString());
        return genMail.toString();
    }


    public String genString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }


    public HttpResponse<JsonNode> TryLogin (String login, String pwd) throws IOException {

        Map httpHead = new HashMap<String, String>();
        httpHead.put("x-xsrf-token","fae6792d-cc98-4b13-a78f-9edffebd26e4");

        Map request = new HashMap<String, String>();
        request.put("username",login);
        request.put("password","7EGDDl");
        request.put("remember-me","undefined");
        request.put("submit","Login");


        HttpResponse<JsonNode> response = Unirest.post(urlAuh).headers(httpHead)
                .cookie("XSRF-TOKEN", "fae6792d-cc98-4b13-a78f-9edffebd26e4")
                .fields(request)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders());
        System.out.println(response.getCookies());
        System.out.println(response.getBody());
        return response;

    }

    public void SuccessLogin() throws IOException {
        response = TryLogin(login,passtrue);
        Assert.assertEquals(response.getStatus(), 200);
    }

    public void FailLogin() throws IOException {
        TryLogin(login,pass);
        Assert.assertFalse(response.getStatus() == 200, "Test");
    }

    @Test
    public void RegistrationTest () {
        Map<String, String> httpHead = new HashMap<>();
        httpHead.put("Accept", "application/json, text/plain, */*");
        httpHead.put("Content-Type","application/json");
        httpHead.put("x-xsrf-token","eb8111fb-9bb2-4d82-9317-2d717eed4c75");



        /*Map request = new HashMap<String,String>();
        request.put("email",genMail());
        request.put("role","ROLE_NEW_TESTER");
        request.put("tariffType","null");*/



        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email",genMail());
        jsonRequest.put("role","ROLE_NEW_TESTER");
        jsonRequest.put("tariffType","null");
        String json2 = jsonRequest.toString();
        System.out.println(json2);
        /*HttpRequest reqPost = (HttpRequest) Unirest.post(urlReg).headers(httpHead)
                .cookie("XSRF-TOKEN", "eb8111fb-9bb2-4d82-9317-2d717eed4c75")
                .body(jsonRequest);
       // System.out.println("Pfghjc    :  " +reqPost);*/

        HttpResponse<JsonNode> response = Unirest.post(urlReg).headers(httpHead)
                .cookie("XSRF-TOKEN", "eb8111fb-9bb2-4d82-9317-2d717eed4c75")
                .body("{\"email\":\"pvmonazu@vdfyry.ru\",\"role\":\"ROLE_NEW_TESTER\",\"tariffType\":null}")
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders());
        System.out.println(response.getCookies());
        System.out.println(response.getBody());

    }

}
