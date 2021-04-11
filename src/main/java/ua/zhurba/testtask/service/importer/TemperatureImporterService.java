package ua.zhurba.testtask.service.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.zhurba.testtask.client.WeatherClient;
import ua.zhurba.testtask.domain.City;
import ua.zhurba.testtask.repository.CityRepository;

import javax.transaction.Transactional;

@Service
public class TemperatureImporterService {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public Integer importTemperature(String cityName) {
        City city = weatherClient.getCityWeather(cityName);

        cityRepository.save(city);
        return city.getId();
    }
}
