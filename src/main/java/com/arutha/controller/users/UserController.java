package com.arutha.controller.users;

import com.arutha.api.request.users.UserRegistrationApi;
import com.arutha.api.response.users.UsersResponse;
import com.arutha.constants.AppErrorCodes;
import com.arutha.controller.role.RoleController;
import com.arutha.helper.CustomExceptionHandler;
import com.arutha.model.users.Users;
import com.arutha.service.users.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for User.
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Endpoint to get all users.
     *
     * @return List of Users
     */
    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        try {
            LOGGER.info("GET request for all users received.");
            List<UsersResponse> usersResponses = userService.getAllUsers();
            return ResponseEntity.ok(usersResponses);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleUsersResourceExceptions(e);
        }
    }

    /**
     * Endpoint to create a User.
     *
     * @param userRegistrationApi user data to create.
     * @return User
     */
    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRegistrationApi userRegistrationApi,
                                             BindingResult result) {
        LOGGER.info("POST request for add user received.");
        if (result.hasErrors()) {
            String errMsg = "Invalid userRegistrationApi in request body";
            return CustomExceptionHandler.handleValidationExceptions(result.getAllErrors(), errMsg,
                    AppErrorCodes.UsersErrorCodes.INVALID_USERS);
        }
        try {
            Users user = userService.saveUser(userRegistrationApi);
            return ResponseEntity.ok(user.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleUsersResourceExceptions(e);
        }
    }

    /**
     * Endpoint to update a User.
     *
     * @param userId user id to update.
     * @param userRegistrationApi user data to update.
     * @return User
     */
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid UserRegistrationApi userRegistrationApi,
                                             BindingResult result, @PathVariable Integer userId) {
        LOGGER.info("PUT request for update user received.");
        if (result.hasErrors()) {
            String errMsg = "Invalid userRegistrationApi in request body";
            return CustomExceptionHandler.handleValidationExceptions(result.getAllErrors(), errMsg,
                    AppErrorCodes.UsersErrorCodes.INVALID_USERS);
        }
        try {
            Users user = userService.updateUser(userId, userRegistrationApi);
            return ResponseEntity.ok(user.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleUsersResourceExceptions(e);
        }
    }

    /**
     * Endpoint to get a User by id.
     *
     * @param userId user id
     * @return User
     */
    @GetMapping("/get/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer userId) {
        LOGGER.info("GET request for get user received.");
        try {
            UsersResponse user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleUsersResourceExceptions(e);
        }
    }

    /**
     * Endpoint to delete a User by id.
     *
     * @param userId user id
     * @return User
     */
    @GetMapping("/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
        LOGGER.info("DELETE request for delete user received.");
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleUsersResourceExceptions(e);
        }
    }

}
