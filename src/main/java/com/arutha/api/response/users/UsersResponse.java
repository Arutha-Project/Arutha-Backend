package com.arutha.api.response.users;

import lombok.Getter;
import lombok.Setter;

/**
 * Response class for User.
 */
@Getter
@Setter
public class UsersResponse {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String roleName;

}
