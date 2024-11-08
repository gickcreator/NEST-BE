package ssu.opensource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssu.opensource.exception.code.BusinessErrorCode;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final BusinessErrorCode errorCode;
}
