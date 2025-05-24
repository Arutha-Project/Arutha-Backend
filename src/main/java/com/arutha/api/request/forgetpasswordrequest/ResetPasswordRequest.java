package com.arutha.api.request.forgetpasswordrequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request class for reset password functionality.
 *
 * <p>This class contains the new password and associated email address for the reset process.
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    private String password;
    private String email;
}
