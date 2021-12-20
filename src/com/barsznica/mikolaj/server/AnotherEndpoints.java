package com.barsznica.mikolaj.server;
import com.barsznica.mikolaj.commonap.*;
import java.util.*;

public class AnotherEndpoints
{
    private final HashMap<String, Object> data = new HashMap<>();

    public AnotherEndpoints()
    {
        data.put("buildings", new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/buildings", method = HttpMethod.Get)
    public HttpAnswer buildingsGet()
    {
        if (data.containsKey("buildings"))
        {
            return new HttpAnswer(200, JsonParser.toJson((ArrayList<Object>)data.get("buildings")));
        }

        return new HttpAnswer(200, "{\"Error\": \"'buildings' key doesn't exists\"}");
    }
}
