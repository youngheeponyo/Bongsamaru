package com.bongsamaru.mypage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.mypage.service.MypageService;
import com.bongsamaru.user.service.UserDetailVO;


/**
 * 프로필 수정을 위한 비밀번호 인증
 * @author 나채현
 *
 */
@Controller
public class PasswordController {
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private MypageService mypageService;
	/**
	 * 프로필 수정 비밀번호 인증 페이지 생성
	 * @return my/pass
	 */
	@GetMapping("/pass")
	public String getPasswordForm() {
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            
            return "my/pass";
        }else {
        	return "login/FacilityLogin";
        }
	}
	
	/**
	 * 회원탈퇴 비밀번호 인증 페이지 생성
	 * @return my/pass2
	 */
	@GetMapping("/password")
	public String getPassword(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Object principal = auth.getPrincipal();
            
            if (principal instanceof UserDetails) {
                UserDetailVO userDetailVO = (UserDetailVO) principal;
                // 이제 userDetailVO를 사용하여 필요한 정보를 얻을 수 있습니다.
                // 예를 들어, userDetailVO.getUsername()을 호출하여 사용자 이름을 얻을 수 있습니다.
                // 또는 userDetailVO에 있는 다른 메서드를 호출하여 추가 정보를 얻을 수 있습니다.
                
                // 예시: 사용자 이름을 모델에 추가
                List<UserVO> list = mypageService.getProfile(userDetailVO.getUsername());
                
                
                model.addAttribute("list", list);
                
                // 필요한 경우, 여기에서 userDetailVO의 다른 정보를 모델에 추가할 수 있습니다.
            }
            	return "my/pass2";
        }else {
        	return "login/FacilityLogin";
        }
	}

	/**
	 * 비밀번호 인증 절차
	 * @param currentPassword
	 * @return
	 */
	@PostMapping("/passwordCheck")
	public ResponseEntity<?> passwordCheck(@RequestParam("currentPassword") String currentPassword) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	        Object principal = auth.getPrincipal();

	        if (principal instanceof UserDetails) {
	            UserDetailVO userDetailVO = (UserDetailVO) principal;

	            // 현재 로그인한 사용자의 비밀번호와 입력한 비밀번호를 비교합니다.
	            if (passwordEncoder.matches(currentPassword, userDetailVO.getPassword())) {
	                // 비밀번호가 일치하는 경우
	                return ResponseEntity.ok().body("비밀번호가 일치합니다.");
	            } else {
	                // 비밀번호가 일치하지 않는 경우
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
	            }
	        }
	    }

	    // 인증되지 않은 경우
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않았습니다.");
	}
}
