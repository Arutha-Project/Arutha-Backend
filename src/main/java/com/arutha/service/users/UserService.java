package com.arutha.service.users;

import com.arutha.api.request.users.UserRegistrationApi;
import com.arutha.api.response.users.UsersResponse;
import com.arutha.exception.CustomException;
import com.arutha.mapper.users.UsersMapper;
import com.arutha.model.users.Users;
import com.arutha.constants.AppErrorCodes;
import com.arutha.repository.users.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for User.
 */
@Service
@AllArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UsersMapper usersMapper;

    private final PasswordEncoder passwordEncoder;

    /**
     * Endpoint to get all users.
     *
     * @return List of Users
     */
    public List<UsersResponse> getAllUsers() throws CustomException {
        try {
            List<Users> users = userRepository.findAll();
            return usersMapper.toUsersResponseList(users);
        } catch (Exception e) {
            String errMsg = "Error while fetching all users";
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_SELECT_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to create a User.
     *
     * @param userRegistrationApi user data to create
     * @return User
     * @throws CustomException custom exception
     */
    public Users saveUser(UserRegistrationApi userRegistrationApi) throws CustomException {
        try {
            Users user = usersMapper.toUserEntity(userRegistrationApi);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            String errMsg = "Error while saving user with email: " + userRegistrationApi.getEmail();
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_INSERT_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to update a User.
     *
     * @param userId user id
     * @param userRegistrationApi user data to update
     * @return User
     * @throws CustomException custom exception
     */
    public Users updateUser(Integer userId, UserRegistrationApi userRegistrationApi) throws CustomException {
        try {
            Users existingUser = userRepository.getReferenceById(userId);
            Users user = usersMapper.updateUserEntity(existingUser,  userRegistrationApi);
            return userRepository.save(user);
        } catch (Exception e) {
            String errMsg = "Error while updating user with email: " + userRegistrationApi.getEmail();
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_UPDATE_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to delete a User.
     *
     * @param userId user id
     * @throws CustomException custom exception
     */
    public void deleteUser(Integer userId) throws CustomException {
        try {
            Users users = userRepository.findById(userId).orElse(null);
            if (users == null) {
                String errMsg = "User with id: " + userId + " does not exist";
                throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_NOT_FOUND, errMsg);
            }
            userRepository.delete(users);
        } catch (Exception e) {
            String errMsg = "Error while deleting user with email: " + userId;
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_DELETE_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to get a User by id.
     *
     * @param userId user id
     * @return User
     * @throws CustomException custom exception
     */
    public UsersResponse getUserById(Integer userId) throws CustomException {
        try {
            Users users = userRepository.findById(userId).orElse(null);
            return usersMapper.toUsersResponse(users);
        } catch (Exception e) {
            String errMsg = "Error while fetching user with id: " + userId;
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_SELECT_QUERY_FAILED, errMsg);
        }
    }
}
