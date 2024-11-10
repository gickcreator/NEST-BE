package ssu.opensource.exception.code;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessErrorCode implements DefaultErrorCode{
    WRONG_ENTRY_POINT(HttpStatus.BAD_REQUEST, "error", "잘못된 요청입니다."),
    ;
    @JsonIgnore
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
