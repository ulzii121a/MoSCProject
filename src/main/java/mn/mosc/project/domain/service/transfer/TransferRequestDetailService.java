package mn.mosc.project.domain.service.transfer;

import mn.mosc.project.domain.entity.transfer.TransferRequestDetail;
import mn.mosc.project.domain.repository.transfer.TransferRequestDetailRepository;
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
public class TransferRequestDetailService {
    private final TransferRequestDetailRepository transferRequestDetailRepository;

    private static final Log LOGGER = LogFactory.getLog(TransferRequestDetailService.class);

    @Autowired
    public TransferRequestDetailService(TransferRequestDetailRepository transferRequestDetailRepository) {
        this.transferRequestDetailRepository = transferRequestDetailRepository;
    }

    public TransferRequestDetail getTransferRequestDetail(String transferRequestDetailId) {
        Validate.notBlank(transferRequestDetailId, "TransferRequestDetail ID should not be blank!");

        try {
            return transferRequestDetailRepository.getTransferRequestDetail(transferRequestDetailId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in TransferRequestDetailService.getTransferRequestDetail: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    public List<TransferRequestDetail> getTransferRequestDetails() {
        try {
            return transferRequestDetailRepository.getTransferRequestDetails();
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in TransferRequestDetailService.getTransferRequestDetail: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }

    public TransferRequestDetail addTransferRequestDetail(TransferRequestDetail transferRequestDetail) {
        Validate.notNull(transferRequestDetail, "TransferRequestDetail should not be Null!");
        Validate.notBlank(transferRequestDetail.getId(), "TransferRequestDetail ID should not be Null!");

        try {
            transferRequestDetailRepository.putTransferRequestDetail(transferRequestDetail);
            return transferRequestDetail;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in TransferRequestDetailService.getTransferRequestDetail: %s", e.getMessage(), e));
            throw new RuntimeException(e);
        }
    }
}
