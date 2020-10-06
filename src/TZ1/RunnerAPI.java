package TZ1;

import kong.unirest.*;
import kong.unirest.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class RunnerAPI {

    private final String urlToken = "https://test.uxcrowd.ru/api/account";
    private final String urlAuh = "https://test.uxcrowd.ru:443/api/authentication";
    private final String urlReg = "https://test.uxcrowd.ru:443/api/register";
    private final String urlChange = "https://test.uxcrowd.ru:443/api/account/password/change";
    private final String login = "brizer@inbox.ru";
    private final String loginCustomer = "brizer@ultra-web.ru";
    private final String passtrue = "7EGDDl";
    private final String passtrueCustomer = "lp1wT2";
    private final String pass = "123412";
    private final String loginChange = "brizer7@gmail.com";
    private String passChange;
    private HttpResponse<JsonNode> response;
    private HttpResponse<JsonNode> loginResponse;


    public String genMail () {
        StringBuilder genMail = new StringBuilder();
        genMail.append(genString(8)).append('@').append(genString(6)).append(".ru");
        //System.out.println(genMail.toString());
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
        //System.out.println(generatedString);
        return generatedString;
    }

    public String getXSRFToken () {
        HttpResponse<JsonNode> response = Unirest.get(urlToken).asJson();
        Cookies mapXZ = response.getCookies();
        Cookie setCookie = mapXZ.getNamed("XSRF-TOKEN");
        return setCookie.getValue();
    }

    public String retSession (HttpResponse<JsonNode> response) {
        Cookies responseCookies = response.getCookies();
        Cookie needCookie = responseCookies.getNamed("JSESSIONID");
        String session = needCookie.getValue();
        return session;
    }

    public HttpResponse<JsonNode> tryLogin(String login, String pwd,String token) throws IOException {

        Map<String, String> httpHead = new HashMap<>();
        httpHead.put("x-xsrf-token",token);

        Map<String, Object> request = new HashMap<>();
        request.put("username",login);
        request.put("password",pwd);
        request.put("remember-me","undefined");
        request.put("submit","Login");


        HttpResponse<JsonNode> response = Unirest.post(urlAuh).headers(httpHead)
                .cookie("XSRF-TOKEN", token)
                .fields(request)
                .asJson();
        return response;

    }

    public void successLogin() throws IOException {
        String token = getXSRFToken();
        response = tryLogin(login,passtrue,token);
        assertEquals(response.getStatus(), 200);
    }

    public void failLogin() throws IOException {
        String token = getXSRFToken();
        response = tryLogin(login,pass,token);
        Assert.assertFalse(response.getStatus() == 200, "Test");
    }


    public void registrationTest() {
        String token = getXSRFToken();
        Map<String, String> httpHead = new HashMap<>();
        httpHead.put("Accept", "application/json, text/plain, */*");
        httpHead.put("Content-Type","application/json");
        httpHead.put("x-xsrf-token",token);


        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email",genMail());
        jsonRequest.put("role","ROLE_NEW_TESTER");
        jsonRequest.put("tariffType", (String) null);

        HttpResponse<JsonNode> response = Unirest.post(urlReg).headers(httpHead)
                    .cookie("XSRF-TOKEN", token)
                    .body(jsonRequest)
                    .asJson();
        assertEquals(response.getStatus(), 201);

    }

    @Test
    public void ChangePasswordTest () throws IOException {

        String token = getXSRFToken();

        try (FileReader fileReader = new FileReader("C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\TZ1\\Pass.txt")){
            BufferedReader reader = new BufferedReader(fileReader);
            passChange = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(passChange);

        loginResponse = tryLogin(loginChange,passChange,token);
        assertEquals(loginResponse.getStatus(), 200);
        String session = retSession(loginResponse);
        String newPass = genString(6)+"D1";
        System.out.println(newPass);

        Map<String, String> httpHead = new HashMap<>();
        httpHead.put("Accept", "application/json, text/plain, */*");
        httpHead.put("Content-Type","application/json");
        httpHead.put("x-xsrf-token",token);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("password_one",passChange);
        jsonRequest.put("password_new",newPass);
        jsonRequest.put("repeat_password_new", newPass);

        HttpResponse<JsonNode> passChangeResult = Unirest.post(urlChange).headers(httpHead)
                .cookie("XSRF-TOKEN", token)
                .cookie("JSESSIONID", session)
                .body(jsonRequest)
                .asJson();
        System.out.println(passChangeResult.getStatus());
        System.out.println(passChangeResult.getHeaders());
        System.out.println(passChangeResult.getCookies());
        System.out.println(passChangeResult.getBody());
       if (passChangeResult.getStatus() == 200) {
            FileWriter writer = new FileWriter("C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\TZ1\\Pass.txt", false);
            writer.write(newPass);
            writer.close();
            System.out.println(newPass);
        }



    }


}
