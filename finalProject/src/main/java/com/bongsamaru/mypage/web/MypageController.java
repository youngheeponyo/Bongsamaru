package com.bongsamaru.mypage.web;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.mypage.service.MypageService;
import com.bongsamaru.mypage.service.sendSmsService;
import com.bongsamaru.user.service.UserDetailVO;
import com.bongsamaru.user.service.UserService;

import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;




/**
 * 마이페이지, 프로필 페이지
 * @author 나채현
 *
 */
@Log4j
@Controller
public class MypageController {
	
	 @Autowired
	 MypageService mypageService;
	
	 @Autowired
	 UserService userService;	
	 
	 /**
	  *  마이페이지 마음온도,기부횟수,기부금액,프로필 리스트
	  * @param model
	  * @return my/mypage
	  */
	 
	 @GetMapping("/my")
	 public String myPage(HttpSession session,Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String memId = "";
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;
	                memId = userDetailVO.getUsername();
	                
	                List<UserVO> list = mypageService.getProfile(memId);

	        		Double heart = mypageService.getHeart(memId);
	        		Integer sumamt = mypageService.getSumAmt(memId);
	        		Integer gibuCount = mypageService.getGibuCount(memId);
	        		
	        		if (sumamt == null) {
	        		    sumamt = 0;
	        		}
	        		
	        		if (gibuCount == null) {
	        			gibuCount = 0;
	        		}
	        		model.addAttribute("list",list);
	        		model.addAttribute("heart",heart);
	        		model.addAttribute("gibuCount",gibuCount);
	        		model.addAttribute("sumAmt",sumamt);
	         }
	      }
	    	String profile = userService.findProfile(memId);
	    	session.setAttribute("profile", profile);
	      return "my/mypage"; 
	}
	 /**
	  * 회원탈퇴 페이지
	  * @param model
	  * @return my/withDrawal
	  */
	 @GetMapping("/withdrawal")
	 public String withDrawal(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;
	         }
	      }

	      return "my/withdrawal"; 
	}
	 
	 /**
	  * 회원탈퇴 성공 페이지
	  * @param model
	  * @return my/bye
	  */
	 @GetMapping("/bye")
	 public String byePage() {

	      return "my/bye"; 
	}
	 
	 /**
	  * 프로필페이지 리스트출력
	  * @param model
	  * @return my/profile
	  */
	 
	 @GetMapping("/profile")
	    public String profile(Model model) {
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
	        }

	        return "my/profile";
	        
	 }
	 
	 /**
	  * CoolSMS 이용한 프로필에서 휴대폰 인증번호
	  * @param to
	  * @return smsService.PhoneNumberCheck(to)
	  * @throws CoolsmsException
	  */
	 
	 // coolSMS 구현 로직 연결  
	 @GetMapping("/sendSMS")
	 public @ResponseBody String sendSMS(@RequestParam(value="to") String to) throws CoolsmsException {  
	     sendSmsService smsService = new sendSmsService();
	     return smsService.PhoneNumberCheck(to);
	 }
	 
	 /**
	  * 프로필 수정
	  * @param userVO
	  */

	 // 수정
	 @PostMapping("/updateProFile")
	 @ResponseBody
	 public ResponseEntity<String> updateProFile(@RequestBody UserVO userVO,
			 									 @RequestParam(value = "files", required = false) MultipartFile[] files,
			 									 HttpSession session) {
	     int result = mypageService.updateProFile(userVO);
	     if (result > 0) {
	    	 return ResponseEntity.ok("프로필이 성공적으로 업데이트되었습니다.");
	         
	     } else {
	         return ResponseEntity.badRequest().body("프로필 업데이트에 실패했습니다.");
	     }
	 }
	 
	 
	 @PostMapping("/deleteMember")
	 @ResponseBody
	 public ResponseEntity<String> deleteMember(@RequestBody UserVO userVO) {
	     int result = mypageService.deleteMember(userVO);
	     if (result > 0) {
	    	 // 현재 사용자 로그아웃 처리
	    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	         authentication.setAuthenticated(false);
	         SecurityContextHolder.clearContext();
	         return ResponseEntity.ok("회원탈퇴 성공");
	     } else {
	         return ResponseEntity.badRequest().body("회원탈퇴실패");
	     }
	 }
}