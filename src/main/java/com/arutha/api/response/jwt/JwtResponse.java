package com.arutha.api.response.jwt;

import com.arutha.api.response.users.CurrentUserUsersResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Response class for jwt.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;
    private CurrentUserUsersResponse currentUser;

}
