package com.marmuz.wheatherapi.controller;

import com.marmuz.wheatherapi.dto.AvgTempRequest;
import com.marmuz.wheatherapi.dto.WeatherResponse;
import com.marmuz.wheatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current")
    public WeatherResponse getCurrentWeather() {
        return weatherService.findWeather();
    }

    @PostMapping("/avg-temperature")
    public Integer getAvgDailyTemp(@RequestBody AvgTempRequest avgTempRequest) {
        return weatherService.findAvgDailyTemp(avgTempRequest);
    }
}
