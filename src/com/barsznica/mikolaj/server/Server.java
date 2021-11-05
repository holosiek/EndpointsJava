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
}
