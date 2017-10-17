package mn.mosc.project.controller.transfer;

import mn.mosc.project.domain.entity.transfer.TransferRequestDetail;
import mn.mosc.project.domain.service.transfer.TransferRequestDetailService;
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
public class TransferRequestDetailController {
    private final TransferRequestDetailService transferRequestDetailService;

    @Autowired
    public TransferRequestDetailController(TransferRequestDetailService transferRequestDetailService) {
        this.transferRequestDetailService = transferRequestDetailService;
    }

    @RequestMapping(value = "/{transferRequestDetailId}/getTransferRequestDetail", method = RequestMethod.GET)
    public ResponseEntity<TransferRequestDetail> getTransferRequestDetail(@PathVariable("transferRequestDetailId") String transferRequestDetailId) {
        try {
            TransferRequestDetail transferRequestDetail = transferRequestDetailService.getTransferRequestDetail(transferRequestDetailId);
            return new ResponseEntity<>(transferRequestDetail, transferRequestDetail == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new TransferRequestDetail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getTransferRequestDetails", method = RequestMethod.GET)
    public ResponseEntity<List<TransferRequestDetail>> getTransferRequestDetail() {
        try {
            List<TransferRequestDetail> transferRequestDetails = transferRequestDetailService.getTransferRequestDetails();
            return new ResponseEntity<>(transferRequestDetails, transferRequestDetails == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addTransferRequestDetail", method = RequestMethod.POST)
    public ResponseEntity<TransferRequestDetailControllerResponse> addTransferRequestDetail(@RequestBody TransferRequestDetail transferRequestDetail) {
        if (transferRequestDetail == null || StringUtils.isBlank(transferRequestDetail.getId()) || StringUtils.isBlank(transferRequestDetail.getTransferRequestId()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(new TransferRequestDetailControllerResponse(transferRequestDetailService.addTransferRequestDetail(transferRequestDetail)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new TransferRequestDetailControllerResponse("Internal Service error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
