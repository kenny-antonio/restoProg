package org.example.entity;

import lombok.*;
import org.example.dao.DishAvailabilityDAO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class DishAvailability {
    private String name;
    private Double availability;



public List<DishAvailability> getAllDishAvailability(){
    DishAvailabilityDAO dao = new DishAvailabilityDAO();
    return dao.findAll();

    }
}