package com.barsznica.mikolaj.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;

public class Server {
    private static final int port = 8000;
    private static final int maxListeners = 0;

    private static HttpHandler getHandler()
    {
        String handlerName = Server.class.getPackageName() + ".GeneratedEndpoints.GeneratedEndpoints";
        HttpHandler handler = null;

        try
        {
            handler = (HttpHandler)Class.forName(handlerName).getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            System.out.println("Error related to endpoints:");
            e.printStackTrace();
        }
        return handler;
    }

    public static void main(String[] args)
    {
        try
        {
            var server = HttpServer.create(new InetSocketAddress(port), maxListeners);
            server.createContext("/", getHandler());
            server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
            server.start();
            System.out.println("Server started and listening at port: " + port);
        }
        catch (IOException e)
        {
            System.out.println("Error related to server:");
            e.printStackTrace();
        }
    }
}
