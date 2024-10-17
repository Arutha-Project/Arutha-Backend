package com.arutha.constants;

/**
 * Error codes for the application.
 */
public class AppErrorCodes {

    private static final String CANNOT_INSTANTIATE_CLASS = "Cannot instantiate a Constant class: ";

    private AppErrorCodes() {
        throw new IllegalStateException(CANNOT_INSTANTIATE_CLASS + "AppErrorCodes");
    }

    /**
     * Error codes for Roles.
     */
    public static class RoleErrorCodes {
        private RoleErrorCodes() {
            throw new IllegalStateException(CANNOT_INSTANTIATE_CLASS + "RoleErrorCodes");
        }

        public static final int DEFAULT = 10000;
        public static final int ROLE_NOT_FOUND = 10001;
        public static final int INVALID_ROLE = 10002;
        public static final int ROLE_SELECT_QUERY_FAILED = 10003;
        public static final int ROLE_INSERT_QUERY_FAILED = 10004;
        public static final int ROLE_UPDATE_QUERY_FAILED = 10005;
        public static final int ROLE_DELETE_QUERY_FAILED = 10006;

    }

    /**
     * Error codes for Users.
     */
    public static class UsersErrorCodes {
        private UsersErrorCodes() {
            throw new IllegalStateException(CANNOT_INSTANTIATE_CLASS + "UsersErrorCodes");
        }

        public static final int DEFAULT = 20000;
        public static final int USERS_NOT_FOUND = 20001;
        public static final int INVALID_USERS = 20002;
        public static final int USERS_SELECT_QUERY_FAILED = 20003;
        public static final int USERS_INSERT_QUERY_FAILED = 20004;
        public static final int USERS_UPDATE_QUERY_FAILED = 20005;
        public static final int USERS_DELETE_QUERY_FAILED = 20006;

    }

    /**
     * Error codes for Auth.
     */
    public static class AuthErrorCodes {
        private AuthErrorCodes() {
            throw new IllegalStateException(CANNOT_INSTANTIATE_CLASS + "AuthErrorCodes");
        }

        public static final int DEFAULT = 30000;
        public static final int INVALID_CREDENTIALS = 30001;
    }
}
