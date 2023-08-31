package com.kujproject.kuj.authentication;

/**
 * JWT 관련된 상수로 사용 되는 파일
 *
 * @author lee
 * @fileName AuthConstants
 * @since 2022.12.23
 */
public final class AuthProperties {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";
    public static final int TOKEN_EXPIRED_TIME = 10;
}