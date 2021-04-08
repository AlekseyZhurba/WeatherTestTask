package ua.zhurba.testtask.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.zhurba.testtask.domain.City;
import ua.zhurba.testtask.service.CityService;

import java.util.UUID;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CityService cityService;

    @Test
    public void testGetCity() throws Exception {
        City city = new City();
        city.setId(UUID.randomUUID());
        city.setName("London");
        city.setTemperature("25");

        Mockito.when(cityService.getCity(city.getId())).thenReturn(city);

        String resultJson = mvc.perform(get("/api/v1/cities/{id}", city.getId())).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        City actualCity = objectMapper.readValue(resultJson, City.class);

        Assertions.assertThat(actualCity).usingRecursiveComparison().isEqualTo(city);

        Mockito.verify(cityService).getCity(city.getId());
    }

    @Test
    public void testCreateCity() throws Exception {
        City createCity = new City();
        createCity.setName("London");
        createCity.setTemperature("25");

        City readCity = new City();
        readCity.setId(UUID.randomUUID());
        readCity.setName("London");
        readCity.setTemperature("25");

        Mockito.when(cityService.createCity(createCity)).thenReturn(readCity);

        String resultJson = mvc.perform(post("/api/v1/cities")
                .content(objectMapper.writeValueAsString(createCity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        City actualCity = objectMapper.readValue(resultJson, City.class);
        Assertions.assertThat(actualCity).usingRecursiveComparison().isEqualTo(readCity);
    }

    @Test
    public void testUpdateCity() throws Exception {
        City updateCity = new City();
        updateCity.setName("Kharkiv");
        updateCity.setTemperature("15");

        City readCity = createCity();

        Mockito.when(cityService.updateCity(readCity.getId(), updateCity)).thenReturn(readCity);

        String resultJson = mvc.perform(put("/api/v1/cities/{id}", readCity.getId().toString())
                .content(objectMapper.writeValueAsString(updateCity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        City actualCity = objectMapper.readValue(resultJson, City.class);
        Assertions.assertThat(actualCity).usingRecursiveComparison().isEqualTo(readCity);
    }

    @Test
    public void testDeleteCity() throws Exception {
        UUID id = UUID.randomUUID();
        mvc.perform(delete("/api/v1/cities/{id}", id.toString())).andExpect(status().isOk());
    }

    private City createCity() {
        City city = new City();
        city.setId(UUID.randomUUID());
        city.setName("Lviv");
        city.setTemperature("20");
        return city;
    }


}


