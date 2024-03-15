package com.bongsamaru.securing.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bongsamaru.user.User;
import com.bongsamaru.user.UserRepository;
import com.bongsamaru.user.service.UserService;

@Component
public class AdditionalInfoFilter extends OncePerRequestFilter {
	private UserRepository userRepository;
	
	@Autowired
    public AdditionalInfoFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Autowired
	public UserService userService;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자 상태 확인 로직 (예: 세션 또는 DB 조회)
            Optional<User> additionalInfoNeeded = userRepository.findByName(ALREADY_FILTERED_SUFFIX);
            Boolean a = userService.countMemId(ALREADY_FILTERED_SUFFIX);
            logger.info(a);
            if (!a && !request.getRequestURI().equals("/signup")) {
                response.sendRedirect("/signup");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
