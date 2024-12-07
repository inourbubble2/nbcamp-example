package com.navi.nbcampjpaspringtestexample.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.navi.nbcampjpaspringtestexample.client.model.WeatherResponse;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@ExtendWith(MockitoExtension.class)
public class WeatherClientTest {


    private WeatherClient weatherClient;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        weatherClient = new WeatherClient(restTemplateBuilder);
    }

    @Test
    @DisplayName("날씨를 정상적으로 구한다")
    public void testGetTodayWeather_Success() {
        // Given
        WeatherResponse[] weatherResponses = {
            new WeatherResponse("12-25", "맑음"),
            new WeatherResponse("12-26", "흐림")
        };
        ResponseEntity<WeatherResponse[]> responseEntity = new ResponseEntity<>(weatherResponses, HttpStatus.OK);

        when(restTemplate.getForEntity(any(URI.class), eq(WeatherResponse[].class))).thenReturn(responseEntity);

        // When
        String result1 = weatherClient.getTodayWeather("12-25");
        String result2 = weatherClient.getTodayWeather("12-26");

        // Then
        assertThat(result1).isEqualTo("맑음");
        assertThat(result2).isEqualTo("흐림");
        verify(restTemplate, times(2)).getForEntity(any(URI.class), eq(WeatherResponse[].class));
    }

    @Test
    @DisplayName("날씨 서버가 200이 아닌 것을 반환했을 때")
    public void testGetTodayWeather_ResponseStatusNotOk() {
        // Given
        ResponseEntity<WeatherResponse[]> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        when(restTemplate.getForEntity(any(URI.class), eq(WeatherResponse[].class))).thenReturn(responseEntity);

        // When & Then
        assertThatThrownBy(() -> weatherClient.getTodayWeather("12-25"))
            .isInstanceOf(IllegalStateException.class);
        verify(restTemplate).getForEntity(any(URI.class), eq(WeatherResponse[].class));
    }

    @Test
    @DisplayName("날씨 서버가 빈 배열을 반환했을 때")
    public void testGetTodayWeather_ResponseBodyNull() {
        // Given
        ResponseEntity<WeatherResponse[]> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.getForEntity(any(URI.class), eq(WeatherResponse[].class))).thenReturn(responseEntity);

        // When & Then
        assertThatThrownBy(() -> weatherClient.getTodayWeather("12-25"))
            .isInstanceOf(NoSuchElementException.class);
        verify(restTemplate).getForEntity(any(URI.class), eq(WeatherResponse[].class));
    }

    @Test
    @DisplayName("날씨 서버 응답 목록에 현재 날짜가 없을 때")
    public void testGetTodayWeather_NoDataForGivenDate() {
        // Given
        WeatherResponse[] weatherResponses = {
            new WeatherResponse("12-23", "맑음"),
            new WeatherResponse("12-26", "흐림")
        };
        ResponseEntity<WeatherResponse[]> responseEntity = new ResponseEntity<>(weatherResponses, HttpStatus.OK);

        when(restTemplate.getForEntity(any(URI.class), eq(WeatherResponse[].class))).thenReturn(responseEntity);

        // When & Then
        assertThatThrownBy(() -> weatherClient.getTodayWeather("12-25"))
            .isInstanceOf(NoSuchElementException.class);
        verify(restTemplate).getForEntity(any(URI.class), eq(WeatherResponse[].class));
    }
}
