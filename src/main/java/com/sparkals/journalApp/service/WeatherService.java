package com.sparkals.journalApp.service;

import com.sparkals.journalApp.api.response.WeatherResponse;
import com.sparkals.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {
    @Value("${Weather.api.key}")
    private String apikey;
    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    private final RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisTemplate redisTemplate;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalapi = appCache.APP_CACHE.get(AppCache.keys.weather_api.toString()).replace("<city>", city).replace("<apiKey>", apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }
    }
}
