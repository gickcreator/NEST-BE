package ssu.opensource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssu.opensource.exception.code.IllegalArgumentErrorCode;

@Getter
@RequiredArgsConstructor
public class IllegalArgumentException extends RuntimeException {
    private final IllegalArgumentErrorCode errorCode;
}
