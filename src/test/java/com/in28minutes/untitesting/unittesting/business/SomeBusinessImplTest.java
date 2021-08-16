package com.in28minutes.untitesting.unittesting.business;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessImplTest {

    @Test
    void calculateSum_basic() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int result = business.calculateSum(new int [] {1, 2, 3});
        int expected = 6;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void calculateSum_empty() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int result = business.calculateSum(new int [] {});
        int expected = 0;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void calculateSum_oneValue() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int result = business.calculateSum(new int [] {5});
        int expected = 5;
        assertThat(expected).isEqualTo(result);
    }
}