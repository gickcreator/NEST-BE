package ssu.opensource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssu.opensource.exception.code.UnAuthorizedErrorCode;

@Getter
@RequiredArgsConstructor
public class UnAuthorizedException extends RuntimeException {
    private final UnAuthorizedErrorCode errorCode;
}
