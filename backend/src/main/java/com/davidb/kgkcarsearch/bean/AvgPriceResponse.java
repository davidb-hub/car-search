package com.davidb.kgkcarsearch.bean;

public class AvgPriceResponse {

    private final int resultCount;
    private final double averagePrice;

    public AvgPriceResponse(int resultCount, double averagePrice) {
        this.resultCount = resultCount;
        this.averagePrice = averagePrice;
    }

    public int getResultCount() {
        return resultCount;
    }
    public double getAveragePrice() {
        return averagePrice;
    }
}
