package com.arutha.api.response.forgetpassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response class for reset password.
 *
 * <p>Contains information about whether the email exists and a related message.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCheckResponse {
    // Getters and setters
    private boolean exists;
    private String message;
    private String email;

}
