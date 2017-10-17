package mn.mosc.project.controller.inventory;

import mn.mosc.project.domain.entity.inventory.InventoryTransfer;
import mn.mosc.project.domain.service.inventory.InventoryTransferService;
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
@RequestMapping("/rest/inventory")
public class InventoryTransferController {
    private final InventoryTransferService inventoryTransferService;

    @Autowired
    public InventoryTransferController(InventoryTransferService inventoryTransferService) {
        this.inventoryTransferService = inventoryTransferService;
    }

    @RequestMapping(value = "/{inventoryTransferId}/getInventoryTransfer", method = RequestMethod.GET)
    public ResponseEntity<InventoryTransfer> getInventoryTransfer(@PathVariable("inventoryTransferId") String inventoryTransferId) {
        try {
            InventoryTransfer inventoryTransfer = inventoryTransferService.getInventoryTransfer(inventoryTransferId);
            return new ResponseEntity<>(inventoryTransfer, inventoryTransfer == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new InventoryTransfer(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getInventoryTransfers", method = RequestMethod.GET)
    public ResponseEntity<List<InventoryTransfer>> getInventoryTransfer() {
        try {
            List<InventoryTransfer> inventoryTransfers = inventoryTransferService.getInventoryTransfers();
            return new ResponseEntity<>(inventoryTransfers, inventoryTransfers == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addInventoryTransfer", method = RequestMethod.POST)
    public ResponseEntity<InventoryTransferControllerResponse> addInventoryTransfer(@RequestBody InventoryTransfer inventoryTransfer) {
        if (inventoryTransfer == null || StringUtils.isBlank(inventoryTransfer.getId()) || StringUtils.isBlank(inventoryTransfer.getTransferRequestDetailId()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(new InventoryTransferControllerResponse(inventoryTransferService.addInventoryTransfer(inventoryTransfer)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new InventoryTransferControllerResponse("Internal Service error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
