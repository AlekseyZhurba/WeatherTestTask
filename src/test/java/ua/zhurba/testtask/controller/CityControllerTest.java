package ua.zhurba.testtask.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
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
        city.setId(1234);
        city.setName("London");
        city.setTemp(25d);

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
        createCity.setTemp(25d);

        City readCity = new City();
        readCity.setId(1234);
        readCity.setName("London");
        readCity.setTemp(25d);

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
        updateCity.setTemp(15d);

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
        city.setId(1234);
        city.setName("Lviv");
        city.setTemp(20d);
        return city;
    }


}


