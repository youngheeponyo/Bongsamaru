package com.bongsamaru.user.service.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        // 여기서 이전 페이지 URL을 가져오는 로직을 구현합니다.
        // referer 헤더에서 이전 페이지 URL을 가져옵니다.
        String refererUrl = request.getHeader("Referer");
        
        // 리다이렉트 로직
        response.sendRedirect(refererUrl != null ? refererUrl : "/default-page");
    }
}
