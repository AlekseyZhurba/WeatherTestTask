package ua.zhurba.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.zhurba.testtask.domain.City;
import ua.zhurba.testtask.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City getCity(Integer id) {
        return cityRepository.findById(id).get();
    }

    public City createCity(City city){
        city = cityRepository.save(city);
        return cityRepository.findById(city.getId()).get();
    }

    public City updateCity(Integer id, City city) {
        City cityToUpdate = cityRepository.findById(id).get();
        cityToUpdate.setTemp(city.getTemp());
        cityToUpdate.setName(city.getName());

        cityToUpdate = cityRepository.save(cityToUpdate);
        return cityToUpdate;
    }

    public void deleteCity(Integer id) {
        City city = cityRepository.findById(id).get();
        cityRepository.delete(city);
    }
}
