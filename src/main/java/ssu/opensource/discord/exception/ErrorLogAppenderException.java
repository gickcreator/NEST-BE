package ssu.opensource.discord.exception;

import ssu.opensource.exception.DiscordException;
import ssu.opensource.exception.code.InternalServerErrorCode;

public class ErrorLogAppenderException extends DiscordException {

    public ErrorLogAppenderException() {
        super(InternalServerErrorCode.DISCORD_LOG_APPENDER_ERROR);
    }
}
