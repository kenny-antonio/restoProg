package org.example.entity;

import lombok.*;
import org.example.dao.DishAvailabilityDAO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor


public class Order {
    private long id;
    private TableNumber tableNumber;
    private Double amountPaid;
    private Double amountDue;
    private Instant CustomerArrivalDatetime;
    private List<DishOrder> dishOrderList;

    public List<DishOrder> addDishOrders(List<DishOrder> dishOrderList){
        DishAvailabilityDAO dishAvailabilityDAO = new DishAvailabilityDAO();

        List<DishAvailability> dishAvailabilities = dishAvailabilityDAO.findAll();

        Map<String, Double> availabilityMap = dishAvailabilities.stream()
                .collect(Collectors.toMap(DishAvailability::getName, DishAvailability::getAvailability));


        for (DishOrder dishOrder : dishOrderList) {
            String dishName = dishOrder.getDishName();
            double requiredQuantity = dishOrder.getQuantity();
            Double availableStock = availabilityMap.get(dishName);

            if (availableStock == null || availableStock < requiredQuantity) {
                throw new IllegalStateException("Not enough stock for order " + dishName);
            }
        }

        this.dishOrderList = new ArrayList<>();
        this.dishOrderList.addAll(dishOrderList);

        return this.dishOrderList;
    }

    public OrderPaymentStatus getPayementStatus(){
        if (amountDue <= 0){
            return OrderPaymentStatus.PAID;
        }else{
            return OrderPaymentStatus.UNPAID;
        }
    }

    public Double getTotalPrice(){
        return dishOrderList.stream()
                .mapToDouble(dishOrder -> dishOrder.getPrice() * dishOrder.getQuantity())
                .sum();
    }
}
