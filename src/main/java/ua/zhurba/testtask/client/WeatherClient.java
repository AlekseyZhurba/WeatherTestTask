package ua.zhurba.testtask.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.zhurba.testtask.domain.City;

@FeignClient(value = "api.key", url = "${openweathermap.api.url}", configuration = WeatherClientConfig.class)
public interface WeatherClient {

    @Value("${api.key}")
    @RequestMapping(method = RequestMethod.GET , value = "/weather?q={cityName}&appid=${api.key}&units=metric" )
    City getCityWeather(@PathVariable("cityName") String cityName);
}
