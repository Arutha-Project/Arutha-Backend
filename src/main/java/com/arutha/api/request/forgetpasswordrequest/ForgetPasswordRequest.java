package com.arutha.api.request.forgetpasswordrequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request class for forget password functionality.
 *
 * <p>This class holds the email address needed to initiate a password reset.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ForgetPasswordRequest {

    private String email;

}
