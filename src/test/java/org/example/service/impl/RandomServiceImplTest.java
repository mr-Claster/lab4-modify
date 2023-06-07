package org.example.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomServiceImplTest {
    RandomServiceImpl randomService;

    @BeforeEach
    void init() {
        randomService = new RandomServiceImpl();
    }

    @Test
    void mustSetRandomValuesToArray() {
        int[] arr = new int[10];
        randomService.setRandomValues(arr);

        Assertions.assertNotNull(arr);
        Assertions.assertEquals(10, arr.length);
        for (int element : arr) {
            Assertions.assertTrue(element >= 0 && element <= 99);
        }
    }

    @Test
    void mustSetRandomValuesToArray2() {
        int[] arr = new int[5];
        randomService.setRandomValues(arr);

        Assertions.assertNotNull(arr);
        Assertions.assertEquals(5, arr.length);
        for (int element : arr) {
            Assertions.assertTrue(element >= 0 && element <= 99);
        }
    }

    @Test
    void mustSetRandomValuesToArray3() {
        int[] arr = new int[1];
        randomService.setRandomValues(arr);

        Assertions.assertNotNull(arr);
        Assertions.assertEquals(1, arr.length);
        for (int element : arr) {
            Assertions.assertTrue(element >= 0 && element <= 99);
        }
    }
}