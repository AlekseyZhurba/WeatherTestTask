package ua.zhurba.testtask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.zhurba.testtask.domain.City;


import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query("select c.name from City c")
    List<String> getAllName();
}
