package com.marmuz.wheatherapi.controller;

import com.marmuz.wheatherapi.dto.RequestAvgTemp;
import com.marmuz.wheatherapi.dto.WeatherResponse;
import com.marmuz.wheatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public WeatherResponse getCurrentWeather(){

        return null;
    }

    @PostMapping("/avg-temperature")
    public Integer getAvgDailyTemp(@RequestBody RequestAvgTemp requestAvgTemp){

        return null;
    }


}
