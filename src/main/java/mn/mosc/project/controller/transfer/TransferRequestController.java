package mn.mosc.project.controller.transfer;

import mn.mosc.project.domain.entity.transfer.TransferRequest;
import mn.mosc.project.domain.service.transfer.TransferRequestService;
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
@RequestMapping("/rest/transfer")
public class TransferRequestController {
    private final TransferRequestService transferRequestService;

    @Autowired
    public TransferRequestController(TransferRequestService transferRequestService) {
        this.transferRequestService = transferRequestService;
    }

    @RequestMapping(value = "/{transferRequestId}/getTransferRequest", method = RequestMethod.GET)
    public ResponseEntity<TransferRequest> getTransferRequest(@PathVariable("transferRequestId") String transferRequestId) {
        try {
            TransferRequest transferRequest = transferRequestService.getTransferRequest(transferRequestId);
            return new ResponseEntity<>(transferRequest, transferRequest == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new TransferRequest(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getTransferRequests", method = RequestMethod.GET)
    public ResponseEntity<List<TransferRequest>> getTransferRequest() {
        try {
            List<TransferRequest> transferRequests = transferRequestService.getTransferRequests();
            return new ResponseEntity<>(transferRequests, transferRequests == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addTransferRequest", method = RequestMethod.POST)
    public ResponseEntity<TransferRequestControllerResponse> addTransferRequest(@RequestBody TransferRequest transferRequest) {
        if (transferRequest == null || StringUtils.isBlank(transferRequest.getId()) || StringUtils.isBlank(transferRequest.getTransferRequestTitle()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(new TransferRequestControllerResponse(transferRequestService.addTransferRequest(transferRequest)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new TransferRequestControllerResponse("Internal Service error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
