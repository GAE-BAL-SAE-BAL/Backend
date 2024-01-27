package com.hideandseak.domain.snack.exception.error;

import com.hideandseak.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SnackError implements ErrorProperty {

    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 안주를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

}
