import org.example.entity.DishAvailability;
import org.example.entity.DishOrder;
import org.example.entity.Order;
import org.example.entity.OrderPaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishAvailabilityTest {
    DishAvailability dishAvailability = new DishAvailability();

    @Test
    void DishAvailabilityTest(){
        List<DishAvailability> dishAvailabilities = dishAvailability.getAllDishAvailability();
        assertEquals(5,dishAvailabilities.size());
    }
    @Test
    void AddDishOrderTest(){
        Order order = new Order();

        DishOrder dishOrder1 = new DishOrder(4, "Pizza Margherita", 1.0, 12000.0, null);
        DishOrder dishOrder2 = new DishOrder(5, "Riz Cantonais", 2.0, 20000.0, null);
        DishOrder dishOrder3 = new DishOrder(6, "Burger Classique", 3.0, 5000.0, null);
        List<DishOrder> dishOrders = Arrays.asList(dishOrder1, dishOrder2, dishOrder3);

        List<DishOrder> result = order.addDishOrders(dishOrders);

        assertEquals(3, result.size());
        assertEquals(3, order.getDishOrderList().size());

    }

    @Test
    void testGetPaymentStatus_Paid() {
        Order order = new Order();
        order.setAmountDue(0.0);

        OrderPaymentStatus status = order.getPayementStatus();

        assertEquals(OrderPaymentStatus.PAID, status);
    }

    @Test
    void testGetPaymentStatus_Unpaid() {
        Order order = new Order();
        order.setAmountDue(100.0);

        OrderPaymentStatus status = order.getPayementStatus();

        assertEquals(OrderPaymentStatus.UNPAID, status);
    }
    @Test
    void testGetTotalPrice() {
        Order order = new Order();
        DishOrder dishOrder1 = new DishOrder(4, "Pizza Margherita", 1.0, 12000.0, null);
        DishOrder dishOrder2 = new DishOrder(5, "Riz Cantonais", 2.0, 20000.0, null);
        DishOrder dishOrder3 = new DishOrder(6, "Burger Classique", 3.0, 5000.0, null);
        order.addDishOrders(Arrays.asList(dishOrder1, dishOrder2, dishOrder3));

        double expectedTotalPrice = (1.0 * 12000.0) + (2.0 * 20000.0) + (3.0 * 5000.0);

        double actualTotalPrice = order.getTotalPrice();

        assertEquals(expectedTotalPrice, actualTotalPrice, 0.001);
    }


}
