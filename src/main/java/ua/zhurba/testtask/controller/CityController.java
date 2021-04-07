package ua.zhurba.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.zhurba.testtask.service.CityService;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;
}
