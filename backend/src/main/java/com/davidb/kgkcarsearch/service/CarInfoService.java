package com.davidb.kgkcarsearch.service;

import com.davidb.kgkcarsearch.bean.CarInfo;

import java.util.List;

public interface CarInfoService {

    List<CarInfo> searchCar(String marke, String modell, String tillverkningsar, String bransletyp);
    double getAveragePrice(List<CarInfo> cars);
}
