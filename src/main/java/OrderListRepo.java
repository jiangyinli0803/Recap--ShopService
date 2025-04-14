import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Order newOrder) {
        newOrder = new Order(
                newOrder.id(),
                newOrder.products(),
                newOrder.orderStatus(),
                Instant.now());
        orders.add(newOrder);
        return newOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }

    public Order updateOrder(Order newOrder) {
        for (Order order : orders) {
            if (order.id().equals(newOrder.id())) {
                order = newOrder;
            }
        }
        return newOrder;
    }
}
