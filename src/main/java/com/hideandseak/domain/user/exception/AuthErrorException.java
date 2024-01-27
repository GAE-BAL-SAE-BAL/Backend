package com.hideandseak.domain.user.exception;

import com.hideandseak.domain.user.exception.error.AuthError;
import com.hideandseak.global.exception.BusinessException;
import com.hideandseak.global.exception.error.ErrorProperty;

public class AuthErrorException extends BusinessException {
    private static final AuthErrorException AUTH_ERROR_DUPLICATED_ID = new AuthErrorException(AuthError.AUTH_ERROR_DUPLICATED_ID);

    private static final AuthErrorException AUTH_ERROR_FAIL_TOKEN = new AuthErrorException(AuthError.AUTH_ERROR_FAIL_TOKEN);

    private static final AuthErrorException AUTH_ERROR_NOT_FOUND_ID_OR_PASSWORD = new AuthErrorException(AuthError.AUTH_ERROR_NOT_FOUND_ID_OR_PASSWORD);

    private static final AuthErrorException AUTH_ERROR_NOT_IN_ID_AND_PASSWORD = new AuthErrorException(AuthError.AUTH_ERROR_NOT_IN_ID_AND_PASSWORD);
    public AuthErrorException(ErrorProperty error) {
        super(error);
    }

    public static AuthErrorException DUPLICATED(){
        return AUTH_ERROR_DUPLICATED_ID;
    }

    public static AuthErrorException FAIL_TOKEN(){
        return AUTH_ERROR_FAIL_TOKEN;
    }

    public static AuthErrorException NOT_FOUND(){
        return AUTH_ERROR_NOT_FOUND_ID_OR_PASSWORD;
    }

    public static AuthErrorException ID_PW_NULL(){
        return AUTH_ERROR_NOT_IN_ID_AND_PASSWORD;
    }
}
