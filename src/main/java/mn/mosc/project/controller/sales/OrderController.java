package mn.mosc.project.controller.sales;

import mn.mosc.project.domain.entity.sales.Order;
import mn.mosc.project.domain.service.sales.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loya on 10/16/17
 */

@RestController
@RequestMapping("/rest/sales")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/{orderId}/getOrder", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("orderId") String orderId) {
        try {
            Order order = orderService.getOrder(orderId);
            return new ResponseEntity<>(order, order == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Order(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrder() {
        try {
            List<Order> orders = orderService.getOrders();
            return new ResponseEntity<>(orders, orders == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseEntity<OrderControllerResponse> addOrder(@RequestBody Order order) {
        if (order == null || StringUtils.isBlank(order.getId()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(new OrderControllerResponse(orderService.addOrder(order)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new OrderControllerResponse("Internal Service error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
