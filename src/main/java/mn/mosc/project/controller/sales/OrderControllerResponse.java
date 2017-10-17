package mn.mosc.project.controller.sales;

import mn.mosc.project.domain.entity.sales.Order;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Loya on 10/16/17
 */

public class OrderControllerResponse {
    private Order order;
    private String message;
    private boolean result;

    public OrderControllerResponse(Order order) {
        this.order = order;
    }

    public OrderControllerResponse(String messsage) {
        this.message = messsage;
    }

    public OrderControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(message);
    }

    public Order getOrder() {
        return order;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }
}
