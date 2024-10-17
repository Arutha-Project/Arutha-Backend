package com.arutha.api.request.users;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for User Registration.
 */
@Getter
@Setter
public class UserRegistrationApi {

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private Integer roleId;

}
