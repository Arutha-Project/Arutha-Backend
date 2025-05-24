package com.arutha.api.response.forgetpassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response class for password reset functionality.
 *
 * <p>Indicates whether the password reset was successful and includes a message and the related email.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetResponse {

    private boolean success;
    private String message;
    private String email;
}