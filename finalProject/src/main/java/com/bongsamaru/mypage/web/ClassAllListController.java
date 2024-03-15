package com.bongsamaru.mypage.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.mypage.service.MypageService;
import com.bongsamaru.user.service.UserDetailVO;


/**
 * 하루모임,동아리,시설봉사 리스트 아직 미구현
 * @author 나채현
 *
 */
@Controller
public class ClassAllListController {
	 @Autowired
	 MypageService mypageService;
	
	 @GetMapping("/dayList")
	 public String dayListPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;
	                String memId = userDetailVO.getUsername();
	                List<VolMemVO> list = mypageService.getDayList(memId);
	                
	                model.addAttribute("list",list);
	                
	         }
	      }

	      return "my/dayList"; 
	}
	 
	 @GetMapping("/clubList")
	 public String classListPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;
	                String memId = userDetailVO.getUsername();
	                List<VolMemVO> list = mypageService.getClubList(memId);

	                model.addAttribute("list", list);
	         }
	      }
		 
		 return "my/clubList"; 
	 }
	 
	 @GetMapping("/facility")
	 public String clubListPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;
	                String memId = userDetailVO.getUsername();
	                List<VolMemVO> list = mypageService.getFacList(memId);
	                
	                model.addAttribute("list",list);
	                
	         }
	      }
		 
		 return "my/facilityList"; 
	 }
}
