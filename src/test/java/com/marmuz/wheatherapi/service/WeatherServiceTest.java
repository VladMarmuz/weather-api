package com.marmuz.wheatherapi.service;

import com.marmuz.wheatherapi.dto.AvgTempRequest;
import com.marmuz.wheatherapi.dto.WeatherResponse;
import com.marmuz.wheatherapi.exception.DataIsIncorrectException;
import com.marmuz.wheatherapi.exception.DataNotFoundException;
import com.marmuz.wheatherapi.model.WeatherData;
import com.marmuz.wheatherapi.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private WeatherService weatherService;


    @Test
    void findWeather() {
        WeatherData weather = createWeather();
        doReturn(Optional.of(weather))
                .when(weatherRepository).findCurrentWeather();

        WeatherResponse actualResult = weatherService.findWeather();

        WeatherResponse expectedResult = createWeatherResponse();
        assertEquals(actualResult, expectedResult);

        verify(weatherRepository).findCurrentWeather();
        verifyNoMoreInteractions(modelMapper, weatherRepository);

    }

    @Test
    void whenFindWeather_thenException() {
        assertThrows(DataNotFoundException.class, () -> weatherService.findWeather());
    }

    @Test
    void findAvgDailyTemp() {
        var request = new AvgTempRequest(LocalDate.ofEpochDay(2023 - 03 - 15),
                LocalDate.ofEpochDay(2023 - 03 - 18));
        doReturn(Optional.of(5)).when(weatherRepository).findAVGTemperature(request.getFrom(), request.getTo());

        var actualResult = weatherService.findAvgDailyTemp(request);

        var expectedResult = 5;
        assertEquals(actualResult, expectedResult);
        verify(weatherRepository).findAVGTemperature(request.getFrom(), request.getTo());
        verifyNoMoreInteractions(modelMapper, weatherRepository);
    }

    @Test
    void whenFindAvgTemp_returnException() {
        AvgTempRequest request = new AvgTempRequest(LocalDate.ofEpochDay(2023 - 03 - 15),
                LocalDate.ofEpochDay(2023 - 03 - 18));
        assertThrows(DataIsIncorrectException.class, () -> weatherService.findAvgDailyTemp(request));
    }

    public WeatherData createWeather() {
        return WeatherData.builder()
                .weatherDate(LocalDate.ofEpochDay(2023-03-17))
                .temperature(5)
                .windSpeed(9000)
                .atmospherePressure(1055)
                .airHumidity(90)
                .weatherConditions("Snow")
                .location("Minsk")
                .build();
    }

    public WeatherResponse createWeatherResponse() {
        return WeatherResponse.builder()
                .temperature(5)
                .windSpeed(9000)
                .atmospherePressure(1055)
                .airHumidity(90)
                .weatherConditions("Snow")
                .location("Minsk")
                .build();
    }


}