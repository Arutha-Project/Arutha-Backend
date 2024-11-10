package com.arutha.mapper.resolver.roleresolver;

import com.arutha.exception.CustomException;
import com.arutha.model.role.Role;
import com.arutha.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Custom resolver useful for Role.
 */
@Component
public class RoleResolver {

    @Autowired
    private RoleService roleService;

    // Maps roleId to the relevant role entity
    public Role resolve(Integer id) throws CustomException {
        return roleService.getReferenceById(id);
    }
}
