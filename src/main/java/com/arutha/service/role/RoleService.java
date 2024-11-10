package com.arutha.service.role;

import com.arutha.api.request.role.RoleApi;
import com.arutha.api.response.role.RoleResponse;
import com.arutha.constants.AppErrorCodes;
import com.arutha.exception.CustomException;
import com.arutha.mapper.role.RoleMapper;
import com.arutha.model.role.Role;
import com.arutha.repository.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Role.
 */
@Service
@AllArgsConstructor
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    /**
     * Endpoint to create a Role.
     *
     * @param roleApi role data to create.
     * @return Role
     */
    public Role createRole(RoleApi roleApi) throws CustomException {
        try {
            LOGGER.info("create new role with role name: {}", roleApi.getRoleName());
            Role role = roleMapper.toRoleEntity(roleApi);
            return roleRepository.save(role);

        } catch (RuntimeException e) {
            String errMsg = "Error while inserting Role with name: " + roleApi.getRoleName();
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_INSERT_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to get all roles.
     *
     * @return List of Roles
     */
    public List<RoleResponse> getAllRole() throws CustomException {
        try {
            List<Role> roles = roleRepository.findAll();
            return roleMapper.toRoleResponseList(roles);
        } catch (Exception e) {
            String errMsg = "Error while fetching all roles";
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_SELECT_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to get a role by id.
     *
     * @param id role id
     * @return Role
     */
    public RoleResponse getRoleById(Integer id) throws CustomException {
        try {
            Role role = roleRepository.findById(id).orElse(null);
            return roleMapper.toRoleResponse(role);
        } catch (Exception e) {
            String errMsg = "Error while fetching role with id: " + id;
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_SELECT_QUERY_FAILED, errMsg);
        }
    }


    /**
     * Endpoint to update a role by id.
     *
     * @param id      role id
     * @param roleApi role data to update
     * @return Role
     */
    public Role updateRole(Integer id, RoleApi roleApi) throws CustomException {
        try {
            Role existingRole = roleRepository.findById(id).orElse(null);
            if (existingRole == null) {
                String errMsg = "Role with id: " + id + " does not exist";
                throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_NOT_FOUND, errMsg);
            }
            Role role = roleMapper.updateRoleEntity(existingRole, roleApi);
            return roleRepository.save(role);
        } catch (Exception e) {
            String errMsg = "Error while updating role with id: " + id;
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_UPDATE_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to delete a role by id.
     *
     * @param id role id
     */
    public void deleteRole(Integer id) throws CustomException {
        try {
            Role role = roleRepository.findById(id).orElse(null);
            if (role == null) {
                String errMsg = "Role with id: " + id + " does not exist";
                throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_NOT_FOUND, errMsg);
            }
            roleRepository.delete(role);
        } catch (Exception e) {
            String errMsg = "Error while deleting role with id: " + id;
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_DELETE_QUERY_FAILED, errMsg);
        }
    }

    /**
     * Endpoint to get a role by id.
     *
     * @param roleId role id
     * @return Role
     */
    public Role getReferenceById(Integer roleId) throws CustomException {
        try {
            return roleRepository.getReferenceById(roleId);
        } catch (Exception e) {
            String errMsg = "Error while fetching role with id: " + roleId;
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.RoleErrorCodes.ROLE_NOT_FOUND, errMsg);
        }
    }

}
