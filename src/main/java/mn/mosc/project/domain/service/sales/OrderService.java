package mn.mosc.project.domain.service.sales;

import mn.mosc.project.domain.entity.sales.Order;
import mn.mosc.project.domain.repository.sales.OrderRepository;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Loya on 10/16/17
 */
@Component
public class OrderService {
    private final OrderRepository orderRepository;

    private static final Log LOGGER = LogFactory.getLog(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(String orderId) {
        Validate.notBlank(orderId, "Order ID should not be blank!");

        try {
            return orderRepository.getOrder(orderId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in OrderService.getOrder: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrders() {
        try {
            return orderRepository.getOrders();
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in OrderService.getOrder: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    public Order addOrder(Order order) {
        Validate.notNull(order, "Order should not be Null!");
        Validate.notBlank(order.getId(), "Order ID should not be Null!");

        try {
            orderRepository.putOrder(order);
            return order;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in OrderService.getOrder: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }
}
