package com.barsznica.mikolaj.server;

import java.util.LinkedHashMap;
import java.util.Map;

public class BodyParser
{
    public static Map<String, String> parseBody(String body) {
        var bodyMap = new LinkedHashMap<String, String>();

        for (String keyValue : body.split(" *& *"))
        {
            String[] pairs = keyValue.split(" *= *", 2);
            bodyMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        return bodyMap;
    }
}
