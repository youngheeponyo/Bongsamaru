package com.bongsamaru.feed.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.InterestVO;
import com.bongsamaru.feed.service.FeedService;
import com.bongsamaru.feed.service.FeedVO;
import com.bongsamaru.user.service.UserDetailVO;

import groovyjarjarpicocli.CommandLine.Parameters;

/**
 * 내 피드와 상대방 피드, 좋아요 페이지
 * @author 나채현
 *
 */
@Controller
public class FeedController {
	
	@Autowired
	FeedService feedService;
	
	 /**
	  * 내 피드 페이지 리스트
	  * @param model
	  * @return feed/myFeed
	  */
	 @GetMapping("/myfeed")
	 public String myFeed(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;

	                String memId = userDetailVO.getUserVO().getId();
	                //회원의 상세정보
	                List<FeedVO> list = feedService.getFeedList(memId);
	                // 피드 게시글 첫번째 이미지 리스트 
	                List<FeedVO> list2 = feedService.getFeedFirstList(memId);
	                // 관심분야
	                List<InterestVO> list3 = feedService.getInterestList(memId);
	                model.addAttribute("list", list);
	                model.addAttribute("feedList", list2);
	                model.addAttribute("InterestList", list3);
	         }
	         return "feed/myFeed"; 
	      }else {
	    	  return "login/FacilityLogin";
	      }

	}
	 
	 @GetMapping("/insertFeed")
	 public String insertFeed(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			 Object principal = auth.getPrincipal();
			 
			 if (principal instanceof UserDetails) {
				 UserDetailVO userDetailVO = (UserDetailVO) principal;
				 
				 String memId = userDetailVO.getUserVO().getId();
				 //회원의 상세정보
				 List<InterestVO> list3 = feedService.getInterestList(memId);
				 model.addAttribute("list", list3);
			 }
			 return "feed/insertFeed"; 
		 }else {
			 return "login/FacilityLogin";
		 }
		 
	 }
	 
	 /**
	  * 상대방 피드 볼수있는 페이지 (상대방아이디이용)
	  * @param memId
	  * @param model
	  * @return feed/feed
	  */
	 @GetMapping("/feed/{memId}")
	 public String feed(@PathVariable String memId, Model model) {
	     
	     // memId를 이용하여 해당 멤버의 피드를 가져오는 로직을 추가하세요.
	     List<FeedVO> list = feedService.getFeedList(memId);
	     List<FeedVO> list2 = feedService.getFeedFirstList(memId);
         List<InterestVO> list3 = feedService.getInterestList(memId);
	     model.addAttribute("list", list);
	     model.addAttribute("feedList", list2);
	     model.addAttribute("InterestList", list3);
	     
	     return "feed/feed";
	 }
	 /**
	  * 피드게시글의 해당하는 상세 페이지
	  * @param memId
	  * @param model
	  * @return feed/feedDetail
	  */
	 @GetMapping("/feed/{memId}/{boardId}")
	 public String feedDetail(@PathVariable String memId,@PathVariable Integer boardId, Model model) {
		 
		 List<FeedVO> list = feedService.getFeedDetail(memId, boardId);
		 List<FeedVO> list1 = feedService.getFeedList(memId);
		 List<CommentsVO> list2 = feedService.getFeedCommentsList(boardId);
		 model.addAttribute("list", list);
		 model.addAttribute("getMem", list1);
		 model.addAttribute("CommentList", list2);
		 return "feed/feedDetail";
	 }
	 
	 /**
	  * 피드의 좋아요
	  * @param boardId
	  * @param model
	  */
	 
	 @PostMapping("/likes")
	 @ResponseBody // ajax호출 return 값
	 public String likeInsert(@RequestParam Integer boardId, Model model) {
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();

	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;
	             System.out.println(userDetailVO.getUserVO() + " 확인");

	             model.addAttribute("userVO", userDetailVO.getUserVO());
	             String memId = userDetailVO.getUserVO().getId();
	             
	             return Integer.toString(feedService.changeLike(memId, boardId));
	             
	         }
	         return "1";
	     }else {
	    	 return "0";
	     }
	 }
	 
}
