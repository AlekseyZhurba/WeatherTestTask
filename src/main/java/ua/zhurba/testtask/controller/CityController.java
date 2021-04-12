package ua.zhurba.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.zhurba.testtask.domain.City;
import ua.zhurba.testtask.service.CityService;


@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{id}")
    public City getCity(@PathVariable Integer id) {
        return cityService.getCity(id);
    }

    @GetMapping("/{id}/weather/current")
    public String getCityTemperature(@PathVariable Integer id, Model model) {
        City city = cityService.getCity(id);
        model.addAttribute("name", city.getName());
        model.addAttribute("temp", city.getTemp());
        return "index";
    }


    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Integer id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
    }
}
