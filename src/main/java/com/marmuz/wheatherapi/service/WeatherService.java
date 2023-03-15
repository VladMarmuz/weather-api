package com.marmuz.wheatherapi.service;

import com.marmuz.wheatherapi.controller.WeatherController;
import com.marmuz.wheatherapi.dto.AvgTempRequest;
import com.marmuz.wheatherapi.dto.WeatherResponse;
import com.marmuz.wheatherapi.exception.DataIsIncorrectException;
import com.marmuz.wheatherapi.exception.DataNotFoundException;
import com.marmuz.wheatherapi.model.WeatherData;
import com.marmuz.wheatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherRepository weatherRepository;
    private final ModelMapper modelMapper;

    public WeatherResponse findWeather() {
        logger.info("Works endpoint getCurrentWeather");
        return weatherRepository.findCurrentWeather()
                .map(this::convertToWeatherResponse).orElseThrow(DataNotFoundException::new);
    }

    public Integer findAvgDailyTemp(AvgTempRequest avgTempRequest) {
        logger.info("Works endpoint getCurrentWeather");
        return weatherRepository.findAVGTemperature(avgTempRequest.getFrom(), avgTempRequest.getTo())
                .orElseThrow(DataIsIncorrectException::new);
    }

    public WeatherResponse convertToWeatherResponse(WeatherData weatherData) {
        return modelMapper.map(weatherData, WeatherResponse.class);
    }
}
