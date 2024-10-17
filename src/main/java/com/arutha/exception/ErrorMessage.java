package com.arutha.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Error message.
 */
@Getter
@Setter
public class ErrorMessage {
    private String message;
    private int errorCode;

    public ErrorMessage(String message, int errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
