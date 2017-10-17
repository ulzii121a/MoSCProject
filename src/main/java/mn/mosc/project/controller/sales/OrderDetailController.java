package mn.mosc.project.controller.sales;

import mn.mosc.project.domain.entity.sales.OrderDetail;
import mn.mosc.project.domain.service.sales.OrderDetailService;
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
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping(value = "/{orderDetailId}/getOrderDetail", method = RequestMethod.GET)
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable("orderDetailId") String orderDetailId) {
        try {
            OrderDetail orderDetail = orderDetailService.getOrderDetail(orderDetailId);
            return new ResponseEntity<>(orderDetail, orderDetail == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new OrderDetail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getOrderDetails", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> getOrderDetail() {
        try {
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetails();
            return new ResponseEntity<>(orderDetails, orderDetails == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addOrderDetail", method = RequestMethod.POST)
    public ResponseEntity<OrderDetailControllerResponse> addOrderDetail(@RequestBody OrderDetail orderDetail) {
        if (orderDetail == null || StringUtils.isBlank(orderDetail.getId()) || StringUtils.isBlank(orderDetail.getInventoryTransferId()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(new OrderDetailControllerResponse(orderDetailService.addOrderDetail(orderDetail)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new OrderDetailControllerResponse("Internal Service error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
