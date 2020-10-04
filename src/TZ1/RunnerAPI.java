package TZ1;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RunnerAPI {


    private final String url = "https://test.uxcrowd.ru:443/api/authentication";


    @Test
    public void HttpTest () throws IOException {

        Map httpHead = new HashMap<String,String>();
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
