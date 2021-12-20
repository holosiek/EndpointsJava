package com.barsznica.mikolaj.server;
import com.barsznica.mikolaj.commonap.*;
import java.util.*;

public class AnotherEndpoints
{
    private final HashMap<String, Object> data = new HashMap<>();

    private static Map<String, String> parseBody(String body) {
        var bodyMap = new LinkedHashMap<String, String>();

        for (String keyValue : body.split(" *& *"))
        {
            String[] pairs = keyValue.split(" *= *", 2);
            bodyMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        return bodyMap;
    }

    public AnotherEndpoints()
    {
        data.put("ser", new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/ser", method = HttpMethod.Get)
    public HttpAnswer SerGet()
    {
        if (data.containsKey("ser"))
        {
            return new HttpAnswer(200, JsonParser.toJson((ArrayList<Object>)data.get("serrrrrrrrrrrrrrrrr")));
        }

        return new HttpAnswer(200, "{\"Error\": \"'ser' key doesn't exists\"}");
    }
}
