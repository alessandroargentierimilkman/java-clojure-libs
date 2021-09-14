package com.milkman.core.services;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestFitServiceTest {

    /**
     * (deftest find-duplicates-test
     *   (let [actual1 (best-fit/find-duplicates '(1 2 3 4 5 5 6 5 1))
     *         expected1 '(5 1)
     *         actual2 (best-fit/find-duplicates [1 2 3 4 5 5 6 5 1])
     *         expected2 [5 1]]
     *     (is (= actual1 expected1))
     *     (is (= actual2 expected2))
     *     )
     *   )
     */
    @Test
    void findDuplicates() {
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5, 5, 6, 5, 1);
        List<?> out = new BestFitService().findDuplicates(in);
        assertEquals(Arrays.asList(5,1), out);
    }
}