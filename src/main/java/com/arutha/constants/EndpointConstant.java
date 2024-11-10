package com.arutha.constants;

/**
 * Class to store endpoint constants.
 */
public class EndpointConstant {

    private EndpointConstant() {
        throw new IllegalStateException("Cannot instantiate a Constant class: EndpointConstant");
    }

    public static final String USERS = "/users";
    public static final String ROLES = "/roles";
    public static final String AUTH = "/auth/login";
}
