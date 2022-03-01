package com.davidb.kgkcarsearch.controller;

import com.davidb.kgkcarsearch.bean.AvgPriceResponse;
import com.davidb.kgkcarsearch.bean.CarInfo;
import com.davidb.kgkcarsearch.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/car-service")
public class CarSearchController {

    @Autowired
    private CarInfoService carInfoService;

    @GetMapping("/sale-price/avg")
    public AvgPriceResponse getCarSearch(@RequestParam("marke") String marke,
                                         @RequestParam("modell") String modell,
                                         @RequestParam(required = false, name= "tillverkningsar") String tillverkningsar,
                                         @RequestParam(required = false, name="bransletyp") String bransletyp) {

        List<CarInfo> cars = carInfoService.searchCar(marke, modell, tillverkningsar, bransletyp);
        double averagePrice = carInfoService.getAveragePrice(cars);

        return new AvgPriceResponse(cars.size(), averagePrice);

    }
}
