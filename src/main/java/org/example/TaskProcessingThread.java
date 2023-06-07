package org.example;

import org.example.service.SwapService;

class TaskProcessingThread extends Thread {
    private int[] arr;
    private final SwapService swapService;

    public TaskProcessingThread(int[] arr, SwapService swapService){
        this.arr = arr;
        this.swapService = swapService;
    }

    public void run() {
        swapService.swap(arr);
    }

    public int[] getArr() {
        return arr;
    }
}