package application.order;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private List<OrderItem> orders;

    public Order() {
        this.orders = new LinkedList<>();
    }

    /**
     * List today orders.
     */
    public void showOrderReport() {
        LocalDateTime today = LocalDateTime.now();
        orders.stream()
                .filter(o -> o.getCreated().getDayOfMonth() == today.getDayOfMonth()
                        && o.getCreated().getMonth() == today.getMonth() && o.getCreated().getYear() == today.getYear())
                .forEach(System.out::println);
    }

    public void addOrder(OrderItem item) {
        this.orders.add(item);
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

}
