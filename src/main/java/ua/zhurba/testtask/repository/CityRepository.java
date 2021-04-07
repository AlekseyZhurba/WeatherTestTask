package ua.zhurba.testtask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.zhurba.testtask.domain.City;

import java.util.UUID;

@Repository
public interface CityRepository extends CrudRepository<City, UUID> {
}
