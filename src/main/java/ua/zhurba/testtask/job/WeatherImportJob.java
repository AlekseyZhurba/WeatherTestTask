package ua.zhurba.testtask.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.zhurba.testtask.client.WeatherClient;
import ua.zhurba.testtask.domain.City;
import ua.zhurba.testtask.repository.CityRepository;
import ua.zhurba.testtask.service.importer.TemperatureImporterService;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class WeatherImportJob {

    @Autowired
    WeatherClient weatherClient;

    @Autowired
    private TemperatureImporterService temperatureImporterService;

    @Autowired
    private CityRepository cityRepository;

    @Value("${weather.import.enabled}")
    private boolean enabled;

    @Transactional(readOnly = true)
    @Scheduled( cron = "${update.temperature.of.city}")
    @PostConstruct
    void updateTemperatureOfCityJob() {
        if(!enabled){
            log.info("Import is enabled");
        }

        doImport();
    }


    public void doImport() {
        log.info("Job started");
        List<String> cityNames = cityRepository.getAllName();
        log.info("Starting import of temperature");
        for (String cityName : cityNames){
            City city = weatherClient.getCityWeather(cityName);
            temperatureImporterService.importTemperature(city.getName());
        }
        log.info("Import finished");
    }
}
