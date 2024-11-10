package com.arutha.mapper.users;

import com.arutha.api.request.users.UserRegistrationApi;
import com.arutha.api.response.users.UsersResponse;
import com.arutha.mapper.resolver.roleresolver.RoleResolver;
import com.arutha.model.users.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapstruct Mapper class for mapping User entities.
 */
@Mapper(componentModel = "spring", uses = { RoleResolver.class })
public interface UsersMapper {

    @Mapping(target = "email",  source = "email")
    @Mapping(target = "firstName",  source = "firstName")
    @Mapping(target = "lastName",  source = "lastName")
    @Mapping(target = "password",  source = "password")
    @Mapping(target = "role",  source = "roleId")
    Users toUserEntity(UserRegistrationApi userRegistrationApi);

    @Mapping(target = "id",  source = "existingUser.id")
    @Mapping(target = "email",  source = "updatedUser.email")
    @Mapping(target = "firstName",  source = "updatedUser.firstName")
    @Mapping(target = "lastName",  source = "updatedUser.lastName")
    @Mapping(target = "password",  source = "existingUser.password")
    Users updateUserEntity(Users existingUser, UserRegistrationApi updatedUser);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "role.roleName", target = "roleName")
    UsersResponse toUsersResponse(Users users);

    List<UsersResponse> toUsersResponseList(List<Users> users);


}
