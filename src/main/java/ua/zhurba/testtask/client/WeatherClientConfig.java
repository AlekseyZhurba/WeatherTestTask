package ua.zhurba.testtask.client;


import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class WeatherClientConfig {

    @Bean
    public RequestInterceptor authRequestInterceptor(@Value("${api.key}") String apiKey){
        return requestTemplate -> requestTemplate.header("Authorization", "API Key " + apiKey);

    }
}
