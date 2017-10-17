package mn.mosc.project.domain.service.sales;

import mn.mosc.project.domain.entity.sales.OrderDetail;
import mn.mosc.project.domain.repository.sales.OrderDetailRepository;
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
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    private static final Log LOGGER = LogFactory.getLog(OrderDetailService.class);

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public OrderDetail getOrderDetail(String orderDetailId) {
        Validate.notBlank(orderDetailId, "OrderDetail ID should not be blank!");

        try {
            return orderDetailRepository.getOrderDetail(orderDetailId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in OrderDetailService.getOrderDetail: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public List<OrderDetail> getOrderDetails() {
        try {
            return orderDetailRepository.getOrderDetails();
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in OrderDetailService.getOrderDetail: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        Validate.notNull(orderDetail, "OrderDetail should not be Null!");
        Validate.notBlank(orderDetail.getId(), "OrderDetail ID should not be Null!");
        Validate.notBlank(orderDetail.getInventoryTransferId(), "InventoryTransferId should not be Null!");

        try {
            orderDetailRepository.putOrderDetail(orderDetail);
            return orderDetail;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in OrderDetailService.getOrderDetail: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }
}
