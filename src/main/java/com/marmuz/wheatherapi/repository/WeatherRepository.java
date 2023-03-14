package com.marmuz.wheatherapi.repository;

import com.marmuz.wheatherapi.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Integer> {
}
