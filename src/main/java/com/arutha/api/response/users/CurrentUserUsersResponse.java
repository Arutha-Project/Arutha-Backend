package com.arutha.api.response.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Response class for User.
 */
@Getter
@Setter
public class CurrentUserUsersResponse {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String roleName;

}
