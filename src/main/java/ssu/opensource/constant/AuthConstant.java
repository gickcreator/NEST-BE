package ssu.opensource.constant;

public class AuthConstant {
    public static final String USER_ID_CLAIM_NAME = "uid";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String[] AUTH_WHITELIST = {
            "/api/**",
            "/v3/api-docs/**",
            "/actuator/health",
            "/api/auth/login/google/**",
            "/api/auth/login/google",
            "/api/test/token/**",
            "/login/google",
            "/auth/logout",
    };
    private AuthConstant() {
    }
}

