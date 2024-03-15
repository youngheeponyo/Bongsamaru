package com.bongsamaru.user.service.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
    									HttpServletResponse response, 
    									Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        HttpSession session = request.getSession();
            session.setAttribute("name",  oAuth2User.getAttribute("name"));
            session.setAttribute("email",  oAuth2User.getAttribute("email"));
            session.setAttribute("sub",  oAuth2User.getAttribute("sub"));
            session.setAttribute("profile",  oAuth2User.getAttribute("picture"));
            System.out.println(oAuth2User);
        
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String sub = oAuth2User.getAttribute("sub");
        response.sendRedirect("/"); // 로그인 성공 후 리디렉션할 페이지
    }
}
