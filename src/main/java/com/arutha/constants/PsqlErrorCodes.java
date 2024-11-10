package com.arutha.constants;

/**
 * Class to store PostgreSQL error codes.
 */

public class PsqlErrorCodes {

    private PsqlErrorCodes() {
        throw new IllegalStateException("Cannot instantiate a Constant class: PsqlErrorCodes");
    }

    public static final String FOREIGN_KEY_VIOLATION_CODE = "23503";
}
