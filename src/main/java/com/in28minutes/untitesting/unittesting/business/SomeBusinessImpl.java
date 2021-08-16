package com.in28minutes.untitesting.unittesting.business;

import com.in28minutes.untitesting.unittesting.data.SomeDataService;

public class SomeBusinessImpl {

    private SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    public int calculateSum(int[] data) {

        int sum = 0;
        for (int value : data) {
            sum += value;
        }

        return sum;
    }

    public int calculateSumUsingDataService() {

        int sum = 0;
        int [] data = someDataService.retrieveAllData();
        for (int value : data) {
            sum += value;
        }

        return sum;
    }
}