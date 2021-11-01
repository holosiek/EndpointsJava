package com.barsznica.mikolaj.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args)
    {
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            var endpoints = (HttpHandler)Class.forName(Server.class.getPackageName() + ".GeneratedEndpoints").getDeclaredConstructor().newInstance();
            server.createContext("/", endpoints);
            server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
            server.start();
        }
        catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
        /*
        server.createContext("/func1", he -> {
            try {
                final Headers headers = he.getResponseHeaders();
                final String requestMethod = he.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(he.getRequestURI());
                        // do something with the request parameters
                        final String responseBody = "['hello world!']";
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        he.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        he.getResponseBody().write(rawResponseBody);
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                he.close();
            }
        });
         */
}
