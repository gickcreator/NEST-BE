package ssu.opensource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssu.opensource.exception.code.InternalServerErrorCode;

@Getter
@RequiredArgsConstructor
public class DiscordException extends RuntimeException{
    private final InternalServerErrorCode internalServerErrorCode;
}

