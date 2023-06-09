package com.marmuz.wheatherapi.service;

import com.marmuz.wheatherapi.exception.DataIsIncorrectException;
import com.marmuz.wheatherapi.model.WeatherData;
import com.marmuz.wheatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WeatherAPI {
    private static final Logger logger = LoggerFactory.getLogger(WeatherAPI.class);
    private static final String URL_URI = "https://api.openweathermap.org/data/2.5/forecast?q=Minsk,by&units=metric&cnt=8&APPID=4d55983229e8b3f59d19986154322980";

    private final WeatherRepository weatherRepository;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    @Transactional
    @Scheduled(fixedRate = 86400000) // the application will be read data from the API for the next day once a day
    public void addNewWeatherData() {

        Optional<WeatherData> weatherData;
        try {
            weatherData = gettingWeather();
        } catch (Exception e) {
            throw new DataIsIncorrectException();
        }
        logger.info("Added new weather data to database");
        weatherData.ifPresent(weatherRepository::save);
    }

    private String sendGet() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_URI))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Getting data from api");
        return response.body();
    }

    public Optional<WeatherData> gettingWeather() throws Exception {

        String json = sendGet();

        JSONObject obj = new JSONObject(json);
        JSONArray list = obj.getJSONArray("list");
        JSONObject lastMeasurements = list.optJSONObject(7);
        logger.info("Converting data from json to java object ");

        return Optional.of(WeatherData.builder()
                .weatherDate(LocalDate.parse(parseIntoDate(lastMeasurements.getString("dt_txt"))))
                .temperature(lastMeasurements.getJSONObject("main").getInt("temp"))
                .windSpeed((lastMeasurements.getJSONObject("wind").getDouble("speed")) * 3600)
                .atmospherePressure(lastMeasurements.getJSONObject("main").getInt("pressure"))
                .airHumidity(lastMeasurements.getJSONObject("main").getInt("humidity"))
                .weatherConditions(lastMeasurements.getJSONArray("weather").getJSONObject(0).getString("main"))
                .location(obj.getJSONObject("city").getString("name"))
                .build());
    }

    public String parseIntoDate(String dateTime) {
        return dateTime.substring(0, 10);
    }
}
