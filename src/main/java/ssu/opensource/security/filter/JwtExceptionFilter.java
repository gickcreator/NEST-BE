package ssu.opensource.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import ssu.opensource.exception.BusinessException;
import ssu.opensource.exception.code.DefaultErrorCode;
import ssu.opensource.exception.code.InternalServerErrorCode;
import ssu.opensource.exception.code.UnAuthorizedErrorCode;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            handleException(request, response, filterChain, UnAuthorizedErrorCode.TOKEN_MALFORMED_ERROR, e);
        } catch (IllegalArgumentException e) {
            handleException(request, response, filterChain, UnAuthorizedErrorCode.TOKEN_TYPE_ERROR, e);
        } catch (ExpiredJwtException e) {
            handleException(request, response, filterChain, UnAuthorizedErrorCode.TOKEN_EXPIRED_ERROR, e);
        } catch (UnsupportedJwtException e) {
            handleException(request, response, filterChain, UnAuthorizedErrorCode.TOKEN_UNSUPPORTED_ERROR, e);
        } catch (JwtException e) {
            handleException(request, response, filterChain, UnAuthorizedErrorCode.TOKEN_UNKNOWN_ERROR, e);
        } catch (BusinessException e) {
            handleException(request, response, filterChain, e.getErrorCode(), e);
        } catch (Exception e) {
            handleException(request, response, filterChain, InternalServerErrorCode.INTERNAL_SERVER_ERROR, e);
        }
    }

    private void handleException(   //예외 처리
                                    final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain,
                                    final DefaultErrorCode errorCode,
                                    final Exception e
    ) throws ServletException, IOException {
        log.error(e.getMessage(), e);
        request.setAttribute("exception", errorCode);
        filterChain.doFilter(request, response);
    }
}
