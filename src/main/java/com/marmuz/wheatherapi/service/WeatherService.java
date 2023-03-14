package com.marmuz.wheatherapi.service;

import com.marmuz.wheatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final ModelMapper modelMapper;


}
