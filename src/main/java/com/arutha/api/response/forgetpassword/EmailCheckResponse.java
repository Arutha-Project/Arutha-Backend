package com.arutha.api.response.forgetpassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response class for email-existence check.
 *
 * <p>Represents the result of checking whether an email exists, including a boolean flag,
 * a related message, and the email address in question.
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
