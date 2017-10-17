package mn.mosc.project.controller.transfer;


import mn.mosc.project.domain.entity.transfer.TransferRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Loya on 10/16/17
 */

public class TransferRequestControllerResponse {
    private TransferRequest transferRequest;
    private String message;
    private boolean result;

    public TransferRequestControllerResponse(TransferRequest transferRequest) {
        this.transferRequest = transferRequest;
    }

    public TransferRequestControllerResponse(String messsage) {
        this.message = messsage;
    }

    public TransferRequestControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(message);
    }

    public TransferRequest getTransferRequest() {
        return transferRequest;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }
}
