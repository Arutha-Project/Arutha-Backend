package com.arutha.service.passwordreset;

import com.arutha.api.request.forgetpasswordrequest.ResetPasswordRequest;
import com.arutha.api.response.forgetpassword.PasswordResetResponse;
import com.arutha.constants.AppErrorCodes;
import com.arutha.exception.CustomException;
import com.arutha.model.users.Users;
import com.arutha.repository.users.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for Password Reset functionality.
 */
@Service
@AllArgsConstructor
public class PasswordResetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Check if email exists in the database.
     *
     * @param email - email to check
     * @return boolean indicating if email exists
     */
    public boolean checkEmailExists(String email) {
        try {
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
            LOGGER.error("Error while checking email existence for: {}", email, e);
            return false;
        }
    }

    /**
     * Reset user password.
     *
     * @param resetPasswordRequest - contains new password and email
     * @return PasswordResetResponse
     */
    @Transactional
    public PasswordResetResponse resetPassword(ResetPasswordRequest resetPasswordRequest) throws CustomException {
        try {
            String email = resetPasswordRequest.getEmail();
            String newPassword = resetPasswordRequest.getPassword();

            // Find user by email
            Users user = userRepository.findByEmail(email);
            if (user == null) {
                throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_NOT_FOUND,
                        "User not found with email: " + email);
            }

            // Encode and update password
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);

            // Save updated user
            userRepository.save(user);

            LOGGER.info("Password successfully reset for user: {}", email);
            return new PasswordResetResponse(true, "Password reset successfully", email);

        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            String errMsg = "Error while resetting password for email: " + resetPasswordRequest.getEmail();
            LOGGER.error(errMsg, e);
            throw new CustomException(AppErrorCodes.UsersErrorCodes.USERS_UPDATE_QUERY_FAILED, errMsg);
        }
    }
}
