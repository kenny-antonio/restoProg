package org.example.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor

public class DishOrder {
    private long idDishOrder;
    private String dishName;
    private Double quantity;
    private Double price;
    private Order order;
}
