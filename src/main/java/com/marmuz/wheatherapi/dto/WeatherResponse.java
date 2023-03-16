package com.marmuz.wheatherapi.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

    private int temperature;
    private double windSpeed;
    private int atmospherePressure;
    private int airHumidity;
    private String weatherConditions;
    private String location;
}
