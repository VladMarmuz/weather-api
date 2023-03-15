package com.marmuz.wheatherapi.service;

import com.marmuz.wheatherapi.exception.DataIsIncorrectException;
import com.marmuz.wheatherapi.model.WeatherData;
import com.marmuz.wheatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WeatherAPI {

    private final WeatherRepository weatherRepository;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    @Scheduled(fixedRate = 86400000)
    public void addNewWeatherData() {
        Optional<WeatherData> weatherData;
        try {
            weatherData = gettingWeather();
        } catch (Exception e) {
            throw new DataIsIncorrectException();
        }
        weatherData.ifPresent(weatherRepository::save);
    }

    private String sendGet() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/forecast?q=Minsk,by&units=metric&cnt=2&APPID=4d55983229e8b3f59d19986154322980"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public Optional<WeatherData> gettingWeather() throws Exception {

        var json = sendGet();

        var obj = new JSONObject(json);
        var list = obj.getJSONArray("list");
        var lastMeasurements = list.optJSONObject(0);

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
