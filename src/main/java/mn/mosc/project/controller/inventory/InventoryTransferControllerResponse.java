package mn.mosc.project.controller.inventory;

import mn.mosc.project.domain.entity.inventory.InventoryTransfer;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Loya on 10/16/17
 */

public class InventoryTransferControllerResponse {
    private InventoryTransfer inventoryTransfer;
    private String message;
    private boolean result;

    public InventoryTransferControllerResponse(InventoryTransfer inventoryTransfer) {
        this.inventoryTransfer = inventoryTransfer;
    }

    public InventoryTransferControllerResponse(String messsage) {
        this.message = messsage;
    }

    public InventoryTransferControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(message);
    }

    public InventoryTransfer getInventoryTransfer() {
        return inventoryTransfer;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }
}
