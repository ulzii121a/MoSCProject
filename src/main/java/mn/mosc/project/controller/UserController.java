package mn.mosc.project.controller;

import mn.mosc.project.domain.entity.authorization.User;
import mn.mosc.project.domain.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * created by ubulgan on 9/30/17
 */
@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{userId}/getUser", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        try {
            User user = userService.getUser(userId);
            return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUser() {
        try {
            List<User> users = userService.getUsers();
            return new ResponseEntity<>(users, users == null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        if (user == null || StringUtils.isBlank(user.getId()))
            new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        try {
            boolean result = userService.addUser(user);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
