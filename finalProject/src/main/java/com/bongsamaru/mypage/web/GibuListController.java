package com.bongsamaru.mypage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.mypage.service.DonledgerVO;
import com.bongsamaru.mypage.service.MypageService;
import com.bongsamaru.user.service.UserDetailVO;

/**
 * 기부 내역 볼수있는 페이지
 * @author 나채현
 *
 */
@Controller
public class GibuListController {
	
	 @Autowired
	 MypageService mypageService;
	
	 /**
	  * 기부 내역 리스트 출력
	  * @param model
	  * @return my/gibulist
	  */
	 @GetMapping("/gibuList")
	 public String myPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;

	                System.out.println(userDetailVO.getUserVO() + "확인");
	                
	                List<DonledgerVO> list = mypageService.getGibuList(userDetailVO.getUsername());
	                
	                model.addAttribute("userVO", userDetailVO.getUserVO());
	                model.addAttribute("list", list);

	         }
	      }

	      return "my/gibulist"; 
	}
	 
}