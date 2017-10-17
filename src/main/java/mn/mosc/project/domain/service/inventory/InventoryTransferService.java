package mn.mosc.project.domain.service.inventory;

import mn.mosc.project.domain.entity.inventory.InventoryTransfer;
import mn.mosc.project.domain.repository.inventory.InventoryTransferRepository;
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
public class InventoryTransferService {
    private final InventoryTransferRepository inventoryTransferRepository;

    private static final Log LOGGER = LogFactory.getLog(InventoryTransferService.class);

    @Autowired
    public InventoryTransferService(InventoryTransferRepository inventoryTransferRepository) {
        this.inventoryTransferRepository = inventoryTransferRepository;
    }

    public InventoryTransfer getInventoryTransfer(String inventoryTransferId) {
        Validate.notBlank(inventoryTransferId, "InventoryTransfer ID should not be blank!");

        try {
            return inventoryTransferRepository.getInventoryTransfer(inventoryTransferId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in InventoryTransferService.getInventoryTransfer: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public List<InventoryTransfer> getInventoryTransfers() {
        try {
            return inventoryTransferRepository.getInventoryTransfers();
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in InventoryTransferService.getInventoryTransfer: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    public InventoryTransfer addInventoryTransfer(InventoryTransfer inventoryTransfer) {
        Validate.notNull(inventoryTransfer, "InventoryTransfer should not be Null!");
        Validate.notBlank(inventoryTransfer.getId(), "InventoryTransfer ID should not be Null!");
        Validate.notBlank(inventoryTransfer.getTransferRequestDetailId(), "Transfer Request ID should not be Null!");

        try {
            inventoryTransferRepository.putInventoryTransfer(inventoryTransfer);
            return inventoryTransfer;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in InventoryTransferService.getInventoryTransfer: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }
}
