package mn.mosc.project.domain.service.transfer;

import mn.mosc.project.domain.entity.transfer.TransferRequest;
import mn.mosc.project.domain.repository.transfer.TransferRequestRepository;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Loya on 10/12/17
 */
@Component
public class TransferRequestService {
    private final TransferRequestRepository transferRequestRepository;

    private static final Log LOGGER = LogFactory.getLog(TransferRequestService.class);

    @Autowired
    public TransferRequestService(TransferRequestRepository transferRequestRepository) {
        this.transferRequestRepository = transferRequestRepository;
    }

    public TransferRequest getTransferRequest(String transferRequestId) {
        Validate.notBlank(transferRequestId, "TransferRequest ID should not be blank!");

        try {
            return transferRequestRepository.getTransferRequest(transferRequestId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in TransferRequestService.getTransferRequest: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public List<TransferRequest> getTransferRequests() {
        try {
            return transferRequestRepository.getTransferRequests();
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in TransferRequestService.getTransferRequest: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    public TransferRequest addTransferRequest(TransferRequest transferRequest) {
        Validate.notNull(transferRequest, "TransferRequest should not be Null!");
        Validate.notNull(transferRequest.getId(), "TransferRequest ID should not be Null!");
        Validate.notNull(transferRequest.getUserId(), "User ID should not be Null!");

        try {
            transferRequestRepository.putTransferRequest(transferRequest);
            return transferRequest;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in TransferRequestService.getTransferRequest: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }
}
