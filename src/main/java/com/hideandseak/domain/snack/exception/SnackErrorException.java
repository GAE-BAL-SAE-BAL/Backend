package com.hideandseak.domain.snack.exception;

import com.hideandseak.domain.snack.exception.error.SnackError;
import com.hideandseak.global.exception.BusinessException;
import com.hideandseak.global.exception.error.ErrorProperty;

public class SnackErrorException extends BusinessException {
    private static final SnackErrorException NOT_FOUND = new SnackErrorException(SnackError.NOT_FOUND);

    public SnackErrorException(ErrorProperty error) {
        super(error);
    }

    public static SnackErrorException NOT_FOUND(){
        return NOT_FOUND;
    }
}
