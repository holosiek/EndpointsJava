package com.barsznica.mikolaj.server;

import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args)
    {
        var server = new HttpServer(8000, 0);
    }
}
