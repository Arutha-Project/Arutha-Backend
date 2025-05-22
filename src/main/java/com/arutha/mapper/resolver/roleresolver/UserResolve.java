package com.arutha.mapper.resolver.roleresolver;

import com.arutha.exception.CustomException;
import com.arutha.model.users.Users;
import com.arutha.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Custom resolver useful for Users.
 */
@Component
public class UserResolve {

    @Autowired
    private UserService userService;

    public Users resolve(Integer id) throws CustomException {
        return userService.getReferenceById(id);
    }

}
