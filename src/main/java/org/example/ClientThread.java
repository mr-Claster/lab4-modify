package org.example;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.example.service.ArrayService;
import org.example.service.impl.SwapServiceImpl;

class ClientThread extends Thread {
    private final Socket clientSocket;
    private final ExecutorService executorService;
    private final ArrayService arrayService;

    public ClientThread(Socket socket, ArrayService arrayService) {
        this.clientSocket = socket;
        this.arrayService = arrayService;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run() {
        try {
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String clientMessage = inputReader.readLine();
            int[] arr = arrayService.parseIntArray(clientMessage);

            Future<int[]> future = executorService.submit(() -> {
                TaskProcessingThread processingThread = new TaskProcessingThread(arr, new SwapServiceImpl());
                processingThread.start();
                processingThread.join();
                return processingThread.getArr();
            });

            while (!future.isDone()) {
                System.out.println("The future is not ready yet.");
            }
            System.out.println("The future is ready.");

            try {
                int[] processedArray = future.get();
                PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                outputWriter.println(arrayService.arrayToString(processedArray));
                outputWriter.close();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("An error occurred while processing the future.", e);
            }

            inputReader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
