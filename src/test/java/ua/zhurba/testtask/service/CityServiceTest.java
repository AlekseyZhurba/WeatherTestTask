package ua.zhurba.testtask.service;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.zhurba.testtask.domain.City;
import ua.zhurba.testtask.repository.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
//@Sql(statements = "delete from city", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CityServiceTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Test
    public void testGetCity() {
        City city = createCity();

        City savedCity = cityService.getCity(city.getId());

        Assertions.assertThat(savedCity).usingRecursiveComparison().isEqualTo(city);
    }

    @Test
    public void testCreateCity() {
        City city = new City();
        city.setName("Lviv");
        city.setTemp(10.0);
        cityService.createCity(city);

        Assert.assertNotNull(city.getId());

        City checkCity = cityRepository.findById(city.getId()).get();
        Assertions.assertThat(city).usingRecursiveComparison().isEqualTo(checkCity);
    }

    @Test
    public void testUpdateCity() {
        City city = createCity();

        City updateCity = new City();
        updateCity.setName("Kharkiv");
        updateCity.setTemp(17.0);

        city = cityService.updateCity(city.getId(), updateCity);
        Assert.assertNotNull(city.getName());
        Assert.assertNotNull(city.getTemp());

        City cityAfterUpdate = cityRepository.findById(city.getId()).get();
        Assertions.assertThat(city).usingRecursiveComparison().isEqualTo(cityAfterUpdate);
    }

    @Test
    public void testDeleteCity(){
        City city = createCity();
        Assert.assertTrue(cityRepository.existsById(city.getId()));

        cityRepository.delete(city);
        Assert.assertFalse(cityRepository.existsById(city.getId()));
    }

    private City createCity(){
        City city = new City();
        city.setName("Dnipro");
        city.setTemp(25.0);
        city = cityRepository.save(city);
        return city;
    }
}
