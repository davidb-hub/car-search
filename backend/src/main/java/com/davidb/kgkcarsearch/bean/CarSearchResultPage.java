package com.davidb.kgkcarsearch.bean;

import java.util.ArrayList;
import java.util.List;


public class CarSearchResultPage {


    private List<CarInfo> results;
    private String next;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public CarSearchResultPage() {
        this(new ArrayList<>());
    }

    public CarSearchResultPage(List<CarInfo> cars) {
        this.results = cars;
    }

    public List<CarInfo> getResults() {
        return results;
    }

    public void setResults(List<CarInfo> results) {
        this.results = results;
    }
}
