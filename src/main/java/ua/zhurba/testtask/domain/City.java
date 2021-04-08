package ua.zhurba.testtask.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String temperature;
}
