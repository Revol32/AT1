package TZ1;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RunnerAPI {


    private final String urlAuh = "https://test.uxcrowd.ru:443/api/authentication";
    private final String urlReg = "https://test.uxcrowd.ru:443/api/register";




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


    public void HttpTest () throws IOException {

        Map httpHead = new HashMap<String,String>();
        httpHead.put("x-xsrf-token","fae6792d-cc98-4b13-a78f-9edffebd26e4");

        Map request = new HashMap<String,String>();
        request.put("username","brizer@inbox.ru");
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

    }

    @Test
    public void RegistrationTest () {
        Map httpHead = new HashMap<String,String>();
        httpHead.put("x-xsrf-token","ae4357fc-e554-4150-b386-d1562577ae1a");



        Map request = new HashMap<String,String>();
        request.put("email",genMail());
        request.put("role","ROLE_NEW_TESTER");
        request.put("tariffType","null");


        HttpResponse<JsonNode> response = Unirest.post(urlReg).headers(httpHead)
                .cookie("XSRF-TOKEN", "ae4357fc-e554-4150-b386-d1562577ae1a")
                .fields(request)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders());
        System.out.println(response.getCookies());
        System.out.println(response.getBody());

    }

}
