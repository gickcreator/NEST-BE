package ssu.opensource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssu.opensource.exception.code.ForbiddenErrorCode;

@Getter
@RequiredArgsConstructor
public class ForbiddenException extends RuntimeException {
    private final ForbiddenErrorCode errorCode;
}
