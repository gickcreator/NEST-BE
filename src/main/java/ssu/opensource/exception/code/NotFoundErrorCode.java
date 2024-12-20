package ssu.opensource.exception.code;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotFoundErrorCode implements DefaultErrorCode{
    NOT_FOUND_TASK(HttpStatus.NOT_FOUND, "error", "존재하지 않는 Task입니다."),
    NOT_FOUND_GOOGLE_CALENDER(HttpStatus.NOT_FOUND, "error", "존재하지 않는 구글 캘린더 입니다."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, "error","RefreshToken을 찾을 수 없습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND,"error","존재하지 않는 사용자입니다."),
    NOT_FOUND_TIME_BLOCK(HttpStatus.NOT_FOUND, "error", "존재하지 않는 TimeBlock입니다."),
    NOT_FOUND_TASK_TYPE(HttpStatus.NOT_FOUND,"error","해당하는 Task의 type을 찾을 수 없습니다."),
    NOT_FOUND_TASK_DAY(HttpStatus.NOT_FOUND,"error","지정한 날짜의 Task를 찾을 수 없습니다."),

    ;

    @JsonIgnore
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
