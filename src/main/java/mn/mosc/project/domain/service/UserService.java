package mn.mosc.project.domain.service;

import mn.mosc.project.domain.entity.authorization.User;
import mn.mosc.project.domain.repository.authorization.UserRepository;
import mn.mosc.project.domain.utility.Encryption;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * created by ubulgan on 9/30/17
 */
@Component
public class UserService {

    private final UserRepository userRepository;
    private final Encryption encryption;

    private static final Log LOGGER = LogFactory.getLog(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, Encryption encryption) {
        this.userRepository = userRepository;
        this.encryption = encryption;
    }

    public User getUser(String userId) {
        Validate.notBlank(userId, "User Id should be provided as properly!");

        try {
            return userRepository.getUser(userId);
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in UserService.getUser: %s", e.getMessage()), e);
            return null;
        }
    }

    public List<User> getUsers() {
        try {
            return userRepository.getUsers();
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in UserService.getUser: %s", e.getMessage()), e);
            return new ArrayList<>();
        }
    }

    public boolean addUser(User user) {
        Validate.notNull(user, "User should not be null!");
        Validate.notBlank(user.getId(), "User Id should be provided as properly!");
        Validate.notBlank(user.getPassword(), "User Password should be provided as properly!");

        try {
            //-------Encrypts Password using AES256:
            user.setPassword(encryption.encrypt(user.getPassword()));

            userRepository.putUser(user);
            return true;
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in UserService.getUser: %s", e.getMessage()), e);
            return false;
        }
    }

    public boolean login(String userId, String pass) {
        Validate.notBlank(userId, "User Id should be provided as properly!");
        Validate.notBlank(pass, "Pass should be provided as properly!");

        User user = getUser(userId);
        if (user == null)
            return false;

        try {
            String encrypted = encryption.encrypt(pass);
            return encrypted.equals(user.getPassword());
        } catch (Exception e) {
            LOGGER.error(String.format("Exception in UserService.getUser: %s", e.getMessage()), e);
            return false;
        }
    }
}
