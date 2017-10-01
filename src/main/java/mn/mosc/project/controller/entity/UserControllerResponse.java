package mn.mosc.project.controller.entity;

import mn.mosc.project.domain.entity.authorization.User;
import org.apache.commons.lang3.StringUtils;

/**
 * created by ubulgan on 10/1/17
 */
public class UserControllerResponse {

    private User user;
    private String errorMessage;
    private boolean result;

    public UserControllerResponse(User user) {
        this.user = user;
    }

    public UserControllerResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public UserControllerResponse(boolean result) {
        this.result = result;
    }

    public boolean isSuccessful() {
        return StringUtils.isBlank(errorMessage);
    }

    public User getUser() {
        return user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean getResult() {
        return result;
    }
}
