import org.junit.jupiter.api.Test;

import java.time.Instant;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() throws Exception {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, Instant.now());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() throws Exception {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test
    void getOrderByStatusTest() throws Exception {
        //given
        OrderRepo orderRepo = new OrderMapRepo();
        orderRepo.addOrder(new Order("1", List.of(), OrderStatus.PROCESSING, Instant.now()));
        Order order1 = new Order("2", List.of(), OrderStatus.PROCESSING, Instant.now());
        Order order2 = new Order("3", List.of(), OrderStatus.IN_DELIVERY, Instant.now());
        Order order3 = new Order("4", List.of(), OrderStatus.IN_DELIVERY, Instant.now());
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);


        ShopService shopService = new ShopService(new ProductRepo(), orderRepo);

        // when
        List<Order> actual = shopService.getOrderByStatus(OrderStatus.IN_DELIVERY);

        //then
        assertEquals(List.of(order2, order3), actual);

    }
}
