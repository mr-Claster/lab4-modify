package org.example.service.impl;

import org.example.service.SwapService;

public class SwapServiceImpl implements SwapService {
    private static final int THREADS_NUMBER = 2;

    public int[] swap(int[] arr) {
        int chunkSize = arr.length / THREADS_NUMBER;
        SwapThread[] swapThreads = new SwapThread[THREADS_NUMBER];
        double testLine = Math.sqrt(arr.length);
        if (testLine % 1 != 0) {
            throw new RuntimeException("Array mast be square");
        }
        int line = (int) testLine;

        for (int i = 0; i < THREADS_NUMBER; i++) {
            int start = i * chunkSize;
            int end = i == THREADS_NUMBER - 1 ? arr.length : start + chunkSize;
            swapThreads[i] = new SwapThread(arr, start, end, line);
            swapThreads[i].start();
        }
        for (int i = 0; i < THREADS_NUMBER; i++) {
            try {
                swapThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return swapThreads[0].getSwap();
    }

    private static class SwapThread extends Thread {
        private final int[] arr;
        private final int start;
        private final int end;
        private final int size;

        public SwapThread(int[] arr, int start, int end, int size) {
            this.arr = arr;
            this.start = start;
            this.end = end;
            this.size = size;
        }

        @Override
        public void run() {
            int swap;
            for (int i = start; i < end; i++) {
                if ((i / size) % 2 == 0 && (i + size < arr.length)) {
                    swap = arr[i];
                    arr[i] = arr[i + size];
                    arr[i + size] = swap;
                }
            }
        }

        public int[] getSwap() {
            return arr;
        }
    }
}
