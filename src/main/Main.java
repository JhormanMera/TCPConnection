package main;

import app.TCPConnection;

public class Main {

    public static void main(String[] args) {
        TCPConnection server = new TCPConnection();
        server.run();
    }

}
