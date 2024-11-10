package com.arutha.api.request.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Request class for JWT.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtApi {

    private String email;
    private String password;

}
