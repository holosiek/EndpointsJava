package com.barsznica.mikolaj.server;

public class Server
{
    private final static int PORT = 8000;
    private final static int MAX_LISTENERS = 0;

    public static void main(String[] args)
    {
        var server = new HttpServer(PORT, MAX_LISTENERS);
    }
}
