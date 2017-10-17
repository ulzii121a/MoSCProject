package mn.mosc.project.controller.transfer;

import mn.mosc.project.domain.entity.transfer.TransferRequestDetail;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Loya on 10/16/17
 */

public class TransferRequestDetailControllerResponse {
    private TransferRequestDetail transferRequestDetail;
    private String message;
    private boolean result;

    public TransferRequestDetailControllerResponse(TransferRequestDetail transferRequestDetail) {
        this.transferRequestDetail = transferRequestDetail;
    }

    public TransferRequestDetailControllerResponse(String messsage) {
        this.message = messsage;
    }

    public TransferRequestDetailControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(message);
    }

    public TransferRequestDetail getTransferRequestDetail() {
        return transferRequestDetail;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }
}
