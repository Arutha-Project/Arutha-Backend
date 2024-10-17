package com.arutha.mapper.role;

import com.arutha.api.request.role.RoleApi;
import com.arutha.api.response.role.RoleResponse;
import com.arutha.model.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapstruct Mapper class for mapping Role entities.
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleName", target = "roleName")
    RoleResponse toRoleResponse(Role role);

    List<RoleResponse> toRoleResponseList(List<Role> roles);

    @Mapping(source = "roleName", target = "roleName")
    Role toRoleEntity(RoleApi roleApi);

    @Mapping(source = "existingRole.id", target = "id")
    @Mapping(source = "updatedRole.roleName", target = "roleName")
    Role updateRoleEntity(Role existingRole, RoleApi updatedRole);

}
