package org.mateuszlaski.logon.security.entrypoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mateuszlaski.logon.common.response.ExceptionResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public RestAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final int httpStatus = HttpStatus.UNAUTHORIZED.value();

        var er = new ExceptionResponse(
                LocalDateTime.now(),
                httpStatus,
                "Unauthorized",
                request.getRequestURI()
        );

        response.setStatus(httpStatus);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(er));
    }
}
