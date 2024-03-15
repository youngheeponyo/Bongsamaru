package com.bongsamaru.center.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.center.service.CenterService;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.user.service.UserDetailVO;

import lombok.extern.log4j.Log4j2;



/**
 * 고객센터(공지사항,자주하는질문,1:1문의내역,1:1문의하기) 페이지
 * @author 나채현
 *
 */
@Log4j2
@Controller
public class CenterController {
	
	 @Autowired
	 CenterService centerService;

	 /**
	  * 자주하는 질문 페이지 페이지네이션 같이 되어있음
	  * @param vo
	  * @param model
	  * @param searchKeyword
	  * @param category
	  * @param start
	  * @param end
	  * @return center/faq
	  */
	 @GetMapping("/faq")
	 public String faqList(PageVO vo, Model model
			 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
			 	, @RequestParam(value="category", required = false, defaultValue = "a03")String category
				, @RequestParam(value="start", required = false)String start
				, @RequestParam(value="end", required = false)String end) {

	             
	            int total = centerService.getFaqCategoryCount(category);
	            // start와 end가 null일 경우 기본값으로 1과 10을 사용
	            int startPage = (start == null) ? 1 : Integer.parseInt(start);
	            int endPage = (end == null) ? 10 : Integer.parseInt(end);
	            
	            
	            if(searchKeyword == null) {
	            	vo = new PageVO(total, startPage, endPage, category ,10);	            	
	            }else {
	            	vo = new PageVO(total, startPage, endPage, category,searchKeyword,10);
	            }
	            log.info(startPage);
	            log.info(endPage);
	            log.info(start);
	            log.info(end);
	         	List<FaqVO> list = centerService.getFaqList(vo);
	         	model.addAttribute("searchKeyword",searchKeyword);
	         	model.addAttribute("vo",vo);
	         	model.addAttribute("category",category);
	         	model.addAttribute("list", list);
	     return "center/faq"; // 해당 페이지의 뷰 이름을 반환합니다. 필요에 따라 수정해주세요.
	 }
	
	 /**
	  * 공지사항 페이지 페이지네이션 같이되어있음
	  * @param vo
	  * @param model
	  * @param searchKeyword
	  * @param start
	  * @param end
	  * @return center/notice
	  */
	 @GetMapping("notice")
	 public String noticeList(PageVO vo, Model model
			 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
			 	, @RequestParam(value="start", required = false)String start
				, @RequestParam(value="end", required = false)String end) {
		 
		 int total = centerService.getNoticeCount(vo);
		
		 // start와 end가 null일 경우 기본값으로 1과 10을 사용
         int startPage = (start == null) ? 1 : Integer.parseInt(start);
         int endPage = (end == null) ? 10 : Integer.parseInt(end);
         String category = "b01";        
         
         if(searchKeyword == null) {
         	vo = new PageVO(total, startPage, endPage, category, 10);	            	
         }else {
         	vo = new PageVO(total, startPage, endPage, category,searchKeyword,10);
         }
		 List<BoardVO> list = centerService.getNoticeList(vo);
		 model.addAttribute("searchKeyword",searchKeyword);
		 model.addAttribute("list",list);
		 model.addAttribute("category",category);
		 model.addAttribute("vo",vo);
		 return "center/notice";
	 }
	 
	 
	 @GetMapping("/notice/{boardId}")
	 public String noticeDetail(@PathVariable Integer boardId, Model model) {
		 
		 List<BoardVO> list = centerService.getNoticeDetail(boardId);
		 model.addAttribute("list", list);
		 return "center/noticeDetail";
	 }
	 
	 /**
	  * 
	  * @return center/inquiry
	  */
	 @GetMapping("inquiry")
	 public String inquiryList(PageVO vo, Model model
			 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
			 	, @RequestParam(value="start", required = false)String start
				, @RequestParam(value="end", required = false)String end) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();

	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;
	         String memId = userDetailVO.getUserVO().getId();

			 int total = centerService.getInquiryCount(memId);
			 System.out.println(total + "숫자");
			 
			 
			 // start와 end가 null일 경우 기본값으로 1과 10을 사용
	         int startPage = (start == null) ? 1 : Integer.parseInt(start);
	         int endPage = (end == null) ? 10 : Integer.parseInt(end);
	         String category = "b02";        
	         
	         if(searchKeyword == null) {
	         	vo = new PageVO(total, startPage, endPage, category, 10);	            	
	         }else {
	         	vo = new PageVO(total, startPage, endPage, category,searchKeyword,10);
	         }
	         vo.setMemId(memId);
	         List<BoardVO> list = centerService.getInquiryList(vo);
	         System.out.println(list + "하이루하이루");

	         
			 model.addAttribute("searchKeyword",searchKeyword);
			 model.addAttribute("list",list);
			 model.addAttribute("vo",vo);
	         }
			 return "center/inquiry"; 
		 }else {
			 return "login/FacilityLogin";
		 }
		 
	 }
	 
	 @GetMapping("/inquiry/{boardId}")
	 public String inquiryDetail(@PathVariable Integer boardId, Model model) {
		 
		 List<BoardVO> list = centerService.getInquiryDetail(boardId);
		 System.out.println(list + "확인");
		 model.addAttribute("list", list);
		 return "center/inquiryDetail";
	 }
	 
	 /**
	  * 
	  * @return center/receipt
	  */
	 @GetMapping("receipt")
	 public String receiptPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();

	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;
	             String memId = userDetailVO.getUserVO().getId();
	             
	             model.addAttribute("memId",memId);

			 }
			 return "center/receipt"; 
		 }else {
			 return "login/FacilityLogin";
		 }
		 
	 }
	 
	@PostMapping("/updateViews")
	  public void updateViews(@RequestParam(value="boardId",required = false)Integer boardId) {
	    // 게시물 조회수 업데이트 로직
	    centerService.updateViews(boardId);
	}
	 
	 @PostMapping("/insertInquiry")
	 @ResponseBody // ajax호출 return 값
	 public String insertInquiry(@RequestBody BoardVO vo, Model model) {
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();

	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;
	             System.out.println(userDetailVO.getUserVO() + " 확인");

	             model.addAttribute("userVO", userDetailVO.getUserVO());
	             
	             return Integer.toString(centerService.insertInquiry(vo));
	         }
	         return "1";
	     }else {
	    	 return "0";
	     }
	 }
}
