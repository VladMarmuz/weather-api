package com.marmuz.wheatherapi.service;

import com.marmuz.wheatherapi.dto.RequestAvgTemp;
import com.marmuz.wheatherapi.dto.WeatherResponse;
import com.marmuz.wheatherapi.exception.DataNotFoundException;
import com.marmuz.wheatherapi.model.WeatherData;
import com.marmuz.wheatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final ModelMapper modelMapper;

    public WeatherResponse findWeather() {
        return weatherRepository.findCurrentWeather()
                .map(this::convertToWeatherResponse).orElseThrow(DataNotFoundException::new);
    }

    public WeatherResponse convertToWeatherResponse(WeatherData weatherData) {
        return modelMapper.map(weatherData, WeatherResponse.class);
    }

    public Integer findAvgTemp(RequestAvgTemp requestAvgTemp) {
        return weatherRepository.findAVGTemperature(requestAvgTemp.getFrom(), requestAvgTemp.getTo())
                .orElseThrow(DataNotFoundException::new);
    }
}
