package ssu.opensource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssu.opensource.exception.code.NotFoundErrorCode;

@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException{
    private final NotFoundErrorCode errorCode;
}
