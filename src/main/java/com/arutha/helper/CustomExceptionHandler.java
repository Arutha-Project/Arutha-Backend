package com.arutha.helper;

import com.arutha.constants.AppErrorCodes;
import com.arutha.constants.PsqlErrorCodes;
import com.arutha.exception.CustomException;
import com.arutha.exception.ErrorMessage;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;

/**
 * Custom Exception Handler to handle app specific exceptions.
 */
public class CustomExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private static final String UNEXPECTED_ERROR_OCCURRED_MSG = "Unexpected Error Occurred";

    private CustomExceptionHandler() {
        throw new IllegalStateException("Utility class: ExceptionHandler");
    }

    /**
     * Handle request validation exceptions.
     *
     * @param errorList validation errors
     * @param errMsg    error message
     * @param errCode   error code
     * @return Error Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static ResponseEntity<Object> handleValidationExceptions(List<ObjectError> errorList, String errMsg,
                                                                    int errCode) {
        LOGGER.error("Field validation errors occurred: {}", errorList);
        //todo: actual validation error is not sent to the frontend
        return ResponseEntity.badRequest().body(new ErrorMessage(errMsg, errCode));
    }

    public static String getDbErrorMsg(RuntimeException runtimeException) {
        return NestedExceptionUtils.getMostSpecificCause(runtimeException).getMessage();
    }

    /**
     * Method to obtain the db exception from runtime exception.
     *
     * @param runtimeException runtime exception
     * @return PSQL Exception if valid DB exception, null if not
     */
    public static PSQLException getDbException(RuntimeException runtimeException) {
        if (runtimeException instanceof DataIntegrityViolationException) {
            return (PSQLException) NestedExceptionUtils.getMostSpecificCause(runtimeException);
        } else {
            return null;
        }

    }

    /**
     * Method to check if a DB Exception is due to a foreign key violation.
     * eg: Can happen when an action tab is deleted when a record under that exists.
     *
     * @param exception runtime exception
     * @return if the exception was because of a foreign key violation
     */
    public static Boolean isFkViolation(RuntimeException exception) {
        boolean isForeignKeyViolation = false;
        PSQLException dbException = getDbException(exception);

        if (Objects.nonNull(dbException)) {
            // 23503 is the error code for foreign key violations
            if (Objects.equals(dbException.getSQLState(), PsqlErrorCodes.FOREIGN_KEY_VIOLATION_CODE)) {
                isForeignKeyViolation = true;
            }
        }
        return isForeignKeyViolation;
    }

    private static CustomException getCustomException(Exception e) {
        CustomException customException = null;
        if (e instanceof CustomException) {
            customException = (CustomException) e;
        } else if (e.getCause() instanceof CustomException) {
            customException = (CustomException) e.getCause();
        }
        return customException;
    }

    /**
     * Method to handle resource exceptions.
     *
     * @param e runtime exception
     * @return Error Response
     */
    public static ResponseEntity<Object> handleRoleResourceExceptions(Exception e) {
        CustomException customException = getCustomException(e);

        if (customException != null) {
            return switch (customException.getAppErrorCode()) {
                // 400 Bad Request
                case AppErrorCodes.RoleErrorCodes.INVALID_ROLE -> ResponseEntity.badRequest().body(
                        new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));

                // 404 Not Found
                case AppErrorCodes.RoleErrorCodes.ROLE_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));

                // 500 Internal Server Error for other cases
                case AppErrorCodes.RoleErrorCodes.ROLE_SELECT_QUERY_FAILED,
                        AppErrorCodes.RoleErrorCodes.ROLE_INSERT_QUERY_FAILED,
                        AppErrorCodes.RoleErrorCodes.ROLE_UPDATE_QUERY_FAILED,
                        AppErrorCodes.RoleErrorCodes.ROLE_DELETE_QUERY_FAILED,
                        AppErrorCodes.RoleErrorCodes.DEFAULT -> ResponseEntity.internalServerError().body(
                            new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));

                // Default fallback for unexpected cases
                default -> ResponseEntity.internalServerError().body(
                        new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));
            };
        }
        return ResponseEntity.internalServerError().body(
                new ErrorMessage(UNEXPECTED_ERROR_OCCURRED_MSG, AppErrorCodes.RoleErrorCodes.DEFAULT));
    }

    /**
     * Handle users resource exceptions.
     *
     * @param e exception
     * @return error response
     */
    public static ResponseEntity<Object> handleUsersResourceExceptions(Exception e) {
        CustomException customException = getCustomException(e);

        if (customException != null) {
            return switch (customException.getAppErrorCode()) {
                // 400 Bad Request
                case AppErrorCodes.UsersErrorCodes.INVALID_USERS -> ResponseEntity.badRequest().body(
                        new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));

                // 404 Not Found
                case AppErrorCodes.RoleErrorCodes.ROLE_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));

                // 500 Internal Server Error for other cases
                case AppErrorCodes.UsersErrorCodes.USERS_SELECT_QUERY_FAILED,
                        AppErrorCodes.UsersErrorCodes.USERS_INSERT_QUERY_FAILED,
                        AppErrorCodes.UsersErrorCodes.USERS_UPDATE_QUERY_FAILED,
                        AppErrorCodes.UsersErrorCodes.USERS_DELETE_QUERY_FAILED,
                        AppErrorCodes.UsersErrorCodes.DEFAULT -> ResponseEntity.internalServerError().body(
                            new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));

                // Default fallback for unexpected cases
                default -> ResponseEntity.internalServerError().body(
                        new ErrorMessage(customException.getMessage(), customException.getAppErrorCode()));
            };
        }
        return ResponseEntity.internalServerError().body(
                new ErrorMessage(UNEXPECTED_ERROR_OCCURRED_MSG, AppErrorCodes.UsersErrorCodes.DEFAULT));
    }


}
