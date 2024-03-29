package com.hideandseak.global.exception;

import com.hideandseak.global.exception.error.ErrorCode;

public class JwtException extends BusinessException {
    public static final JwtException JWT_IS_EXPIRED = new JwtException(ErrorCode.JWT_IS_EXPIRED);
    public static final JwtException JWT_NOT_FOUND_EXCEPTION = new JwtException(ErrorCode.JWT_NOT_FOUND);

    private JwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}