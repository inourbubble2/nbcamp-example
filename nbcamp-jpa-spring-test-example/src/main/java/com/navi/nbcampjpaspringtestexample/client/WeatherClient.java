package com.navi.nbcampjpaspringtestexample.client;

import com.navi.nbcampjpaspringtestexample.client.model.WeatherResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class WeatherClient {

    private final RestTemplate restTemplate;

    public WeatherClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getTodayWeather(String date) {
        ResponseEntity<WeatherResponse[]> responseEntity =
            restTemplate.getForEntity(buildWeatherApiUri(), WeatherResponse[].class);

        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new IllegalStateException("Invalid response code: " + responseEntity.getStatusCode());
        }

        WeatherResponse[] weathers = responseEntity.getBody();

        if (weathers == null || weathers.length == 0) {
            throw new NoSuchElementException("No weather found");
        }

        return Arrays.stream(weathers)
            .filter(weather -> date.equals(weather.date()))
            .map(WeatherResponse::weather)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No weather found"));
    }

    public URI buildWeatherApiUri() {
        return UriComponentsBuilder
            .fromUriString("https://f-api.github.io")
            .path("/f-api/weather.json")
            .encode()
            .build()
            .toUri();
    }
}
