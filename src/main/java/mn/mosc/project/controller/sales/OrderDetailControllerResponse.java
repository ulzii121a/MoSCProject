package mn.mosc.project.controller.sales;

import mn.mosc.project.domain.entity.sales.OrderDetail;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Loya on 10/16/17
 */

public class OrderDetailControllerResponse {
    private OrderDetail orderDetail;
    private String message;
    private boolean result;

    public OrderDetailControllerResponse(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderDetailControllerResponse(String messsage) {
        this.message = messsage;
    }

    public OrderDetailControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(message);
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }
}
