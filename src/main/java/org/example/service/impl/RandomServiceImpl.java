package org.example.service.impl;

import java.util.Random;
import org.example.service.RandomService;

public class RandomServiceImpl implements RandomService {
    private static final int THREADS_NUMBER = 4;

    public int[] setRandomValues(int[] arr) {
        int chunkSize = arr.length / THREADS_NUMBER;
        RandomThread[] randomThread = new RandomThread[THREADS_NUMBER];
        for (int i = 0; i < THREADS_NUMBER; i++) {
            int start = i * chunkSize;
            int end = i == THREADS_NUMBER - 1 ? arr.length : start + chunkSize;
            randomThread[i] = new RandomThread(arr, start, end);
            randomThread[i].start();
        }
        for (int i = 0; i < THREADS_NUMBER; i++) {
            try {
                randomThread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return randomThread[0].getArr();
    }

    private static class RandomThread extends Thread {
        private final Random random = new Random();
        private final int[] arr;
        private final int start;
        private final int end;

        public RandomThread(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                arr[i] = random.nextInt(0, 99);
            }
        }

        public int[] getArr() {
            return arr;
        }
    }
}
