package ua.zhurba.testtask.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.zhurba.testtask.domain.City;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherClientTest {

    @Autowired
    private WeatherClient weatherClient;

    @Test
    public void testGetWeather() {
        String cityName = "Dnipro";
        City city = weatherClient.getCityWeather(cityName);

        Assert.assertEquals(cityName, city.getName());
    }
}
