package com.hideandseak.domain.drink.exception;

import com.hideandseak.domain.drink.exception.error.DrinkError;
import com.hideandseak.global.exception.BusinessException;
import com.hideandseak.global.exception.error.ErrorProperty;

public class DrinkErrorException extends BusinessException {
    private static final DrinkErrorException NOT_FOUND = new DrinkErrorException(DrinkError.NOT_FOUND);


    public DrinkErrorException(ErrorProperty error) {
        super(error);
    }

    public static DrinkErrorException NOT_FOUND(){
        return DrinkErrorException.NOT_FOUND;
    }
}
