package ssu.opensource.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ssu.opensource.dto.common.ResponseDto;
import ssu.opensource.exception.code.BusinessErrorCode;
import ssu.opensource.exception.code.DefaultErrorCode;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {  //인증 실패시 처리
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException
    ) throws IOException {
        DefaultErrorCode errorCode = (DefaultErrorCode) request.getAttribute("exception");
        if (errorCode == null)
            errorCode = BusinessErrorCode.WRONG_ENTRY_POINT;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());
        response.getWriter().write(
                objectMapper.writeValueAsString(ResponseDto.fail(errorCode))
        );
    }
}
