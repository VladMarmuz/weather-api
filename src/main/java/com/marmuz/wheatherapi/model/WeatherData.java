package com.marmuz.wheatherapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "temperature")
    private int temperature;
    @Column(name = "wind_speed")
    private double windSpeed;
    @Column(name = "atmosphere_pressure")
    private int atmospherePressure;
    @Column(name = "air_humidity")
    private int airHumidity;
    @Column(name = "weather_conditions")
    private String weatherConditions;
    @Column(name = "location")
    private String location;
    @Column(name = "weather_date")
    private LocalDate weatherDate;


}
