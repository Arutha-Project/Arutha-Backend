package com.arutha.controller.passwordresetcontroller;

import com.arutha.api.request.forgetpasswordrequest.ForgetPasswordRequest;
import com.arutha.api.request.forgetpasswordrequest.ResetPasswordRequest;
import com.arutha.api.response.forgetpassword.EmailCheckResponse;
import com.arutha.api.response.forgetpassword.PasswordResetResponse;
import com.arutha.exception.CustomException;
import com.arutha.service.passwordreset.PasswordResetService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for Password Reset functionality.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/password")
@CrossOrigin(origins = "*") // Configure this properly for production
public class PasswordResetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetController.class);

    private final PasswordResetService passwordResetService;

    /**
     * Check if email exists in the database.
     *
     * @param request contains email to check
     * @return response indicating if email exists
     */
    @PostMapping("/forgot")
    public ResponseEntity<EmailCheckResponse> checkEmail(@RequestBody ForgetPasswordRequest request) {
        try {
            String email = request.getEmail();
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new EmailCheckResponse(false, "Email is required", email));
            }

            boolean emailExists = passwordResetService.checkEmailExists(email);
            if (emailExists) {
                return ResponseEntity.ok(
                        new EmailCheckResponse(true, "Email found in our system", email)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new EmailCheckResponse(false, "Email not found in our system", email));
            }
        } catch (Exception e) {
            LOGGER.error("Error while checking email", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmailCheckResponse(false, "Internal server error", null));
        }
    }

    /**
     * Resets the user's password based on the provided request.
     * Performs validation and updates the database accordingly.
     *
     * @param request contains new password and email
     * @return response indicating success or failure
     */
    @PostMapping("/reset")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ResetPasswordRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            PasswordResetResponse resetResponse = passwordResetService.resetPassword(request);

            response.put("success", resetResponse.isSuccess());
            response.put("message", resetResponse.getMessage());
            response.put("email", resetResponse.getEmail());

            return ResponseEntity.ok(response);

        } catch (CustomException e) {
            LOGGER.error("Custom error while resetting password", e);
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            LOGGER.error("Error while resetting password", e);
            response.put("success", false);
            response.put("message", "Failed to reset password. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}