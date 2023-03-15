package com.marmuz.wheatherapi.repository;

import com.marmuz.wheatherapi.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Integer> {

    @Query(
            value= "SELECT w FROM weather_data w ORDER BY weather_date DESC LIMIT 1",
            nativeQuery = true
    )
    Optional<WeatherData> findCurrentWeather();

    /*Optional<Integer> findby*/
}
