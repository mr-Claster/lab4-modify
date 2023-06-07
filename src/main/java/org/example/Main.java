package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.example.service.impl.ArrayServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new ClientThread(clientSocket, new ArrayServiceImpl());
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}