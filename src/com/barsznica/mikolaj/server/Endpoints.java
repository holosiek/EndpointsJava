package com.barsznica.mikolaj.server;

import com.barsznica.mikolaj.processor.*;

import java.util.*;

public class Endpoints
{
    private static final HashMap<String, Object> data = new HashMap<>();

    static {
        data.put("test", new ArrayList<>());
    }

    private static Map<String, String> parseBody(String body) {
        var bodyMap = new LinkedHashMap<String, String>();

        for (String keyValue : body.split(" *& *"))
        {
            String[] pairs = keyValue.split(" *= *", 2);
            bodyMap.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }

        return bodyMap;
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/test", method = HttpMethod.Get)
    public static HttpAnswer TestGet()
    {
        if (data.containsKey("test"))
        {
            return new HttpAnswer(200, JsonParser.toJson((ArrayList<Object>)data.get("test")));
        }

        return new HttpAnswer(200, "{\"Error\": \"'test' key doesn't exists\"}");
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/test/book", method = HttpMethod.Post)
    public static HttpAnswer TestNewBookPost(@Body String body)
    {
        if (data.containsKey("test"))
        {
            var parsedBody = parseBody(body);
            if (parsedBody.containsKey("name") && parsedBody.containsKey("description") && parsedBody.containsKey("genre"))
            {
                var name = parsedBody.get("name");
                var description = parsedBody.get("description");
                var genre = parsedBody.get("genre");
                var book = new HashMap<String, Object>();
                book.put("name", name);
                book.put("description", description);
                book.put("genre", genre);
                ((ArrayList<Object>)data.get("test")).add(book);
                return new HttpAnswer(200, JsonParser.toJson(book));
            }

            return new HttpAnswer(200, "{\"Error\": \"Body has invalid number of arguments\"}");
        }

        return new HttpAnswer(200, "{\"Error\": \"'test' key doesn't exists\"}");
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/test/book/{name}", method = HttpMethod.Get)
    public static HttpAnswer TestNewBookGet(@EndpointPath(parameter = "name") String name)
    {
        if (data.containsKey("test"))
        {
            for (var bookObject : (ArrayList<Object>)data.get("test"))
            {
                var book = (HashMap<String, Object>)bookObject;

                if (book.get("name").equals(name))
                {
                    return new HttpAnswer(200, JsonParser.toJson(book));
                }
            }

            return new HttpAnswer(200, "{\"Error\": \"Book doesn't exists\"}");
        }

        return new HttpAnswer(200, "{\"Error\": \"'test' key doesn't exists\"}");
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/test/book", method = HttpMethod.Put)
    public static HttpAnswer TestNewBookPut(@Body String body)
    {
        if (data.containsKey("test"))
        {
            var parsedBody = parseBody(body);

            if (parsedBody.containsKey("name") && parsedBody.containsKey("description") && parsedBody.containsKey("genre"))
            {
                var array = (ArrayList<Object>)data.get("test");

                for (int i=0; i<array.size(); i++)
                {
                    var book = (HashMap<String, Object>)array.get(i);

                    if (book.get("name").equals(parsedBody.get("name")))
                    {
                        var hash = ((HashMap<String, Object>)(((ArrayList<Object>)data.get("test")).get(i)));
                        hash.replace("name", parsedBody.get("name"));
                        hash.replace("description", parsedBody.get("description"));
                        hash.replace("genre", parsedBody.get("genre"));

                        return new HttpAnswer(200, JsonParser.toJson(hash));
                    }
                }

                return new HttpAnswer(200, "{\"Error\": \"Book doesn't exists\"}");
            }

            return new HttpAnswer(200, "{\"Error\": \"Body has invalid number of arguments\"}");
        }

        return new HttpAnswer(200, "{\"Error\": \"'test' key doesn't exists\"}");
    }

    @SuppressWarnings("unchecked")
    @Endpoint(path = "/test/book/{name}", method = HttpMethod.Delete)
    public static HttpAnswer TestNewBookDelete(@EndpointPath(parameter = "name") String name)
    {
        if (data.containsKey("test"))
        {
            var array = (ArrayList<Object>)data.get("test");

            for (int i=0; i<array.size(); i++)
            {
                var book = (HashMap<String, Object>)array.get(i);

                if (book.get("name").equals(name))
                {
                    array.remove(i);
                    return new HttpAnswer(200, "{\"Result\": \"Deleted book successfully\"}");
                }
            }

            return new HttpAnswer(200, "{\"Error\": \"Book doesn't exists\"}");
        }

        return new HttpAnswer(200, "{\"Error\": \"'test' key doesn't exists\"}");
    }
}
