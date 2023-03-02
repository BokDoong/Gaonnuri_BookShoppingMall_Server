package GaonNuri.Project.ShoppingMall.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();

        JwtErrorResponse jwtErrorResponse = new JwtErrorResponse();
        jwtErrorResponse.setMessage("접근 권한이 없습니다.");
        jwtErrorResponse.setErrorCode("403");

        //에러데이터
//        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        response.setStatus(403);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(jwtErrorResponse));
    }
}
