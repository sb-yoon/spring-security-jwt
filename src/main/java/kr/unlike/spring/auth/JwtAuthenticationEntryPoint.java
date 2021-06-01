package kr.unlike.spring.auth;

import kr.unlike.spring.dto.common.ApiResponse;
import kr.unlike.spring.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("JwtAuthenticationEntryPoint");
        // 유효한 자격증명을 제공하지 않고 접근하려 할 때 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.error("유효하지 않는 토큰입니다.", HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JsonUtil.serialize(apiResponse));
    }
}
