package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SwapServiceImplTest {
    SwapServiceImpl swapService;

    @BeforeEach
    void init() {
        this.swapService = new SwapServiceImpl();
    }

    @Test
    void mustSwapElementsInArray() {
        int[] arr = {6, 6, 6, 6, 6,
                     7, 7, 7, 7, 7,
                     8, 8, 8, 8, 8,
                     9, 9, 9, 9, 9,
                     10, 10, 10, 10, 10};
        int[] actualArr = swapService.swap(arr);
        int[] expectArr = {7, 7, 7, 7, 7,
                           6, 6, 6, 6, 6,
                           9, 9, 9, 9, 9,
                           8, 8, 8, 8, 8,
                           10, 10, 10, 10, 10};
        assertArrayEquals(expectArr, actualArr);
    }

    @Test
    void mustSwapElementsInArray2() {
        int[] arr = {1, 1, 1, 1,
                2, 2, 2, 2,
                3, 3, 3, 3,
                4, 4, 4, 4,};
        int[] actualArr = swapService.swap(arr);
        int[] expectArr = {2, 2, 2, 2,
                1, 1, 1, 1,
                4, 4, 4, 4,
                3, 3, 3, 3};
        assertArrayEquals(expectArr, actualArr);
    }

    @Test
    void mustThrowException() {
        assertThrows(Exception.class, () -> swapService.swap(new int[]{1, 2, 3}));
    }
}