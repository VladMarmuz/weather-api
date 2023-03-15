package com.marmuz.wheatherapi.repository;

import com.marmuz.wheatherapi.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Integer> {

    @Query("select w from WeatherData w where w.weatherDate in(select max(weatherDate) from WeatherData)")
    Optional<WeatherData> findCurrentWeather();

    @Query("select avg(w.temperature) from WeatherData w where w.weatherDate > :from and w.weatherDate < :to")
    Optional<Integer> findAVGTemperature(LocalDate from, LocalDate to);
}
