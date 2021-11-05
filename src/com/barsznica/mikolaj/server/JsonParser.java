package com.barsznica.mikolaj.server;

import java.util.List;
import java.util.Map;

public class JsonParser
{
    private static String toJson(String key, Object value)
    {
        return String.format("\"%s\": %s", key, value);
    }

    private static String toJson(String key, String value)
    {
        return String.format("\"%s\": \"%s\"", key, value.replaceAll("\"", "\\\\\""));
    }

    private static String toJson(String key, List<?> list)
    {
        var stringBuilder = new StringBuilder();

        addDataToStringBuilder(list, stringBuilder);

        return "\"" + key + "\": [" + stringBuilder + "]";
    }

    @SuppressWarnings("unchecked")
    private static void addDataToStringBuilder(List<?> list, StringBuilder stringBuilder) {
        for (var value : list)
        {
            if (value instanceof String)
            {
                stringBuilder.append("\"").append(((String) value).replaceAll("\"", "\\\\\"")).append("\"");
            }
            else if(value instanceof Map)
            {
                stringBuilder.append(toJson((Map<String, ?>)value));
            }
            else
            {
                stringBuilder.append(value.toString());
            }
            stringBuilder.append(",");
        }

        if (list.size() > 0)
        {
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    public static String toJson(List<?> list)
    {
        StringBuilder stringBuilder = new StringBuilder();

        addDataToStringBuilder(list, stringBuilder);

        return "[" + stringBuilder + "]";
    }

    @SuppressWarnings("unchecked")
    public static String toJson(Map<String, ?> map) {
        StringBuilder stringBuilder = new StringBuilder();

        if (map == null || map.keySet().size() == 0)
        {
            return "{}";
        }

        stringBuilder.append("{");
        for (var entry : map.entrySet())
        {
            var key = entry.getKey();
            var value = entry.getValue();

            if (value instanceof String){
                stringBuilder.append(toJson(key, (String) value));
            }
            else if(value instanceof List)
            {
                stringBuilder.append(toJson(key, (List<?>) value));
            }
            else if(value instanceof Map)
            {
                stringBuilder.append("\"").append(key).append("\": ").append(toJson((Map<String, ?>) value));
            }
            else
            {
                stringBuilder.append(toJson(key, value));
            }
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1).append("}");

        return stringBuilder.toString();
    }
}
