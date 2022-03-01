package com.davidb.kgkcarsearch.service.impl;

import com.davidb.kgkcarsearch.service.CarInfoService;
import com.davidb.kgkcarsearch.bean.CarInfo;
import com.davidb.kgkcarsearch.bean.CarSearchResultPage;
import com.davidb.kgkcarsearch.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.url}")
    private String url;

    @Value("${kgk.debug}")
    private boolean debug;

    private boolean isValid(String queryParam) {
        return queryParam != null && !queryParam.isEmpty();
    }

    @Override
    public List<CarInfo> searchCar(String marke, String modell, String tillverkningsar, String bransletyp) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        builder.queryParam("marke", marke);
        builder.queryParam("modell", modell);
        if (isValid(tillverkningsar)) {
            builder.queryParam("tillverkningsar", tillverkningsar);
        }
        if (isValid(bransletyp)) {
            builder.queryParam("bransletyp", bransletyp);
        }

        String urlString = builder.build().encode().toUri().toString();
        List<CarInfo> cars = new ArrayList<>();
        do {
            CarSearchResultPage response = performSearch(urlString);
            cars.addAll(response.getResults());
            urlString = response.getNext();

        } while (urlString != null && !urlString.isEmpty());

        return cars;
    }

    private CarSearchResultPage performSearch(String urlString) {
        CarSearchResultPage response;
        System.out.println(urlString);

        if (debug) {
            return getDummyResponse();

        } else {
            response = webClientBuilder.build()
                    .get()
                    .uri(urlString)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(CarSearchResultPage.class)
                    .block();
        }
        try {
            Thread.sleep(200);
            // Don't spam the server with requests
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        return response;

    }

    private CarSearchResultPage getDummyResponse() {
        CarSearchResultPage page = new CarSearchResultPage();
        List<CarInfo> cars = new ArrayList<>();
        cars.add(new CarInfo("Volvo", "S60 D1", "2014", "Diesel", "266000"));
        cars.add(new CarInfo("Volvo", "S60 D1", "2014", "Diesel", "230000"));
        cars.add(new CarInfo("Volvo", "S60 D1", "2014", "Diesel", "400000"));

        page.setResults(cars);


        return page;
    }

    @Override
    public double getAveragePrice(List<CarInfo> cars) {
        double total = 0;
        for (CarInfo car : cars) {
            total += Double.parseDouble(car.getNybilspris());
        }

        return Math.round(total / (double)cars.size());
    }

//    private CarSearchResultPage performSearch(String urlString) {
//        CarSearchResultPage response = restTemplate.getForObject(urlString, CarSearchResultPage.class);
//        System.out.println(urlString);
//
//        try {
//            Thread.sleep(200);
//            // Don't spam the server with requests
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted");
//        }
//
//        System.out.println(response);
//        return response;
//    }


}
