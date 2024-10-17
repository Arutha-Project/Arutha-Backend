package com.arutha.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Custom exception.
 */
@Getter
@Setter
@AllArgsConstructor
public class CustomException extends Exception {

    private final int appErrorCode;
    private final String message;

}
