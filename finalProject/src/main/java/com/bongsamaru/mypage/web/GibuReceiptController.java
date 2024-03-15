package com.bongsamaru.mypage.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.mypage.service.MypageService;
import com.bongsamaru.user.service.UserDetailVO;

/**
 * 기부영수증 페이지 미구현
 * @author 나채현
 *
 */
@Controller
public class GibuReceiptController {
	 @Autowired
	 MypageService mypageService;
	
	 @GetMapping("/gibuReceipt")
	 public String myPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;

	                System.out.println(userDetailVO.getUserVO() + "확인");
	         }
	      }

	      return "my/GibuReceipt"; 
	}
}
