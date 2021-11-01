package com.barsznica.mikolaj.server;

import com.barsznica.mikolaj.processor.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Endpoints
{
    private Map<String, String> parseBody(String body) {
        var bodyMap = new LinkedHashMap<String, String>();

        for(String keyValue : body.split(" *& *"))
        {
            String[] pairs = keyValue.split(" *= *", 2);
            bodyMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        return bodyMap;
    }

    @Endpoint(path = "/test", method = HttpMethod.Get)
    public static HttpAnswer Test()
    {
        System.out.println("A");

        return new HttpAnswer(200, "");
    }

    @Endpoint(path = "/test/xd", method = HttpMethod.Put)
    public static HttpAnswer Test2(@Body String body)
    {
        System.out.println("C " + body);

        return new HttpAnswer(200, "");
    }

    @Endpoint(path = "/test/{id}", method = HttpMethod.Put)
    public static HttpAnswer Test3(@EndpointPath(parameter = "id") int id, @Body String body)
    {
        System.out.println("B " + id + " " + body);

        return new HttpAnswer(200, "");
    }
}
