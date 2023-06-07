package org.example.service.impl;

import org.example.service.ArrayService;

public class ArrayServiceImpl implements ArrayService {
    @Override
    public String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    @Override
    public int[] parseIntArray(String str) {
        String[] strArr = str.split(",");
        int[] arr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        return arr;
    }
}
