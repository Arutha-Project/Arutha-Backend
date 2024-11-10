package com.arutha.controller.role;

import com.arutha.api.request.role.RoleApi;
import com.arutha.api.response.role.RoleResponse;
import com.arutha.constants.AppErrorCodes;
import com.arutha.helper.CustomExceptionHandler;
import com.arutha.model.role.Role;
import com.arutha.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for Role.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    /**
     * Endpoint to create a Role.
     *
     * @param roleApi role data to create.
     * @return Role
     */
    @PostMapping("/")
    public ResponseEntity<Object> createRole(@RequestBody @Valid RoleApi roleApi, BindingResult result) {
        LOGGER.info("POST request for add role received.");
        if (result.hasErrors()) {
            String errMsg = "Invalid roleApi in request body";
            return CustomExceptionHandler.handleValidationExceptions(result.getAllErrors(), errMsg,
                    AppErrorCodes.RoleErrorCodes.INVALID_ROLE);
        }
        try {
            Role role = roleService.createRole(roleApi);
            return ResponseEntity.ok(role.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleRoleResourceExceptions(e);
        }
    }

    /**
     * Endpoint to get all roles.
     *
     * @return List of Roles
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@RequestBody @Valid RoleApi roleApi, BindingResult result,
                                             @PathVariable Integer id) {
        LOGGER.info("PUT request for update role received.");
        if (result.hasErrors()) {
            String errMsg = "Invalid roleApi in request body";
            return CustomExceptionHandler.handleValidationExceptions(result.getAllErrors(), errMsg,
                    AppErrorCodes.RoleErrorCodes.INVALID_ROLE);
        }
        try {
            Role role = roleService.updateRole(id, roleApi);
            return ResponseEntity.ok(role.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleRoleResourceExceptions(e);
        }
    }

    /**
     * Endpoint to get a role by id.
     *
     * @param id role id
     * @return Role
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable Integer id) {
        LOGGER.info("GET request for get role received.");
        try {
            RoleResponse role = roleService.getRoleById(id);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleRoleResourceExceptions(e);
        }
    }

    /**
     * Endpoint to delete a role by id.
     *
     * @param id role id
     * @return Role
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRoleById(@PathVariable Integer id) {
        LOGGER.info("DELETE request for delete role received.");
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleRoleResourceExceptions(e);
        }
    }

    /**
     * Endpoint to get all roles.
     *
     * @return List of Roles
     */
    @GetMapping("/all")
    public ResponseEntity<Object> getAllRole() {
        LOGGER.info("GET request for get all roles received.");
        try {
            List<RoleResponse> roles = roleService.getAllRole();
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleRoleResourceExceptions(e);
        }
    }
}
