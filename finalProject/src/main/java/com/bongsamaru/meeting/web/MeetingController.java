package com.bongsamaru.meeting.web;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.FreeBoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.RemittanceVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActReviewVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.common.service.MailVO;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.meeting.service.MeetingService;
@Controller
public class MeetingController {
	@Autowired
	MeetingService service;
	
	@Autowired
	AdminService userService;
	
	@Autowired
	DonaService donaService;
	
	//봉사 날짜가 지나면 방 폭파
	//@Scheduled(cron = "0 0 0 * * *")
	public void autoDeleteMeeting() {
		Date today = new Date();
		List<VolunteerVO> volActList = userService.dailyAllList();
		for(VolunteerVO vo : volActList) {
			//봉사날짜가 오늘날짜를 지남
			if(vo.getVolDate().compareTo(today) > 0) {
				service.deleteMeeting(vo.getVolId());
			}
		}
	}
	
	//모임 방 메인
	/**
	 * 모임 방 메인
	 * @param volId
	 * @param model
	 * @param req
	 * @param prin
	 * @return
	 * @throws IOException
	 */
	@GetMapping("meetings")
	public String meetings(HttpSession session,PageVO pvo,VolMemVO volVO,@RequestParam Integer volId,Model model,HttpServletRequest req,HttpServletResponse res,Principal prin,VolunteerVO volunteerVO) throws IOException {
		String iden = "";
		if(session.getAttribute("Role") != null) {
			iden = (String)session.getAttribute("Role");
		}
		
		if(iden.equals("m02") || iden.equals("m01")) {
			System.out.println("너, 들어올수 있어");
		}else {
			String uri = "meetings?volId="+volId;
		    if (uri != null && !uri.contains("/login")) {
		        req.getSession().setAttribute("prevPage", uri);
		    }
		    res.sendRedirect("/login?redirectURL=login");
		}
		req.getSession().setAttribute("id",volId);
		VolunteerVO vo2 = service.meetingInfo(volId);
		model.addAttribute("info",vo2);
		
		pvo = new PageVO(10, 1, 10, volId,null);
		List<VolActVO> list = service.meetingVolActListPaging(pvo);
		model.addAttribute("act",list);
		
		volVO.setAppCode("h02");
		List<VolMemVO> member = service.meetingMemList(volVO);
		model.addAttribute("member",member);
		List<VolMemVO> cnt = service.volCnt(volVO);
		model.addAttribute("cnt",cnt);
		
		
		Date today = new Date();
		model.addAttribute("today",today);
		
		List<VolActVO> review = service.volActReviewListPaging(pvo);
		
		for(VolActVO vo : review) {
			vo.setFilePath(service.findFile("p12",vo.getVolActId()));
		}
		model.addAttribute("review",review);
		
		List<VolActVO> after = new ArrayList<>();
		List<VolActVO> before = new ArrayList<>();
		for(VolActVO vo : list) {
			vo.setFilePath(service.findFile("p11",vo.getVolActId()));
			vo.setCnt(service.volActMemCnt(vo.getVolActId()));
			if(vo.getVolDate().compareTo(today) >= 0) {
				after.add(vo);
			}else {
				before.add(vo);
			}
		}
		System.out.println("후기"+after);
		model.addAttribute("after",after);
		model.addAttribute("before",before);
		PageVO pageVO = new PageVO();
		pageVO = new PageVO(3, 1, 3, null ,3);
		pageVO.setRoomStat(1);
		List<VolunteerVO> randomList = userService.meetingList(pageVO);
		model.addAttribute("choose",randomList);
		return "meeting/meetings";
	}
	
	@PostMapping("approveMeeting")
	@ResponseBody
	public int approveMeeting(VolMemVO vo) {
		return service.approveMeeting(vo);
	}
	
	//레이아웃에서 아작스로 받기
	@GetMapping("meetingInfo")
	@ResponseBody
	public VolunteerVO meetingInfo(@RequestParam(name="volId") Integer volId) {
		return service.meetingInfo(volId);
	}
	
	//레이아웃에서 아작스로 받기
	@GetMapping("meetingTag")
	@ResponseBody
	public List<TagVO> meetingTag(TagVO vo) {
		return userService.tagList(vo);
	}
	
	//레이아웃에서 아작스로 받기
	@GetMapping("findMember")
	@ResponseBody
	public int findMember(VolMemVO vo,@RequestParam Integer volId,@RequestParam(required = false) String memId
							,@RequestParam(required = false) String appCode) {
		return service.findMember(vo);
	}
	
	//레이아웃에서 아작스로 받기
	@GetMapping("chamMem")
	@ResponseBody
	public int chamMem(VolMemVO vo,@RequestParam(required = false) Integer volActId,@RequestParam(required = false) String memId) {
		return service.chamMem(vo);
	}
	
	@GetMapping("meetingInfoPage")
	public String meetingInfo(@RequestParam(name="volId") Integer volId,Model model) {
		VolunteerVO vo = service.meetingInfo(volId);
		model.addAttribute("info",vo);
		return "meeting/meetingInfo";
	}
	
	//봉사게시판
   @GetMapping("volBoardList")
   public String volBoardList(Principal prin,PageVO pvo,@RequestParam(name="volId") Integer volId,Model model,HttpServletRequest req
                         , @RequestParam(value="start", required = false,defaultValue = "1")Integer start
                        , @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
      
      int total = service.meetingVolActListCnt(volId);

      pvo = new PageVO(total, start, end, volId,null);
        
      List<VolActVO> list = service.meetingVolActListPaging(pvo);
      req.getSession().setAttribute("id",volId);
      model.addAttribute("vo",pvo);
      model.addAttribute("act",list);
      Date today = new Date();
      
      for(VolActVO vo : list) {
         if(vo.getExpireDate().compareTo(today) < 0&&vo.getVolDate().compareTo(today) < 0) {
            vo.setState(1);
         }else {
            vo.setState(0);
         }
      }
      
      return "meeting/volBoardList";
   }
   
 //봉사게시판 정보
   @GetMapping("volActBoardInfo")
   public String volActBoardInfo(Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req,@RequestParam Integer volActId) {
      VolActVO info = service.volActBoardInfo(volActId);
      model.addAttribute("info",info);
      req.getSession().setAttribute("id",volId);
      //모임의 방장 아이디
      VolunteerVO vo = service.meetingInfo(volId);
      model.addAttribute("meeting",vo.getMemId());
      Date today = new Date();
      model.addAttribute("after",info.getVolDate().compareTo(today)>0);
      
      return "meeting/volActInfo";
   }
   
   //봉사게시판 작성폼
   @GetMapping("insertVolActPage")
   public String insertVolActPage(Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req) {
      req.getSession().setAttribute("id",volId);
      return "meeting/volActBoardInsert";
   }
   
   @PostMapping("insertVolAct")
   @ResponseBody
   public int insertVolAct(VolActVO vo) {
      return service.insertVolAct(vo);
   }
   
   //봉사게시판 삭제
   @PostMapping("delVolActBoard")
   @ResponseBody
   public int delVolActBoard(Integer volActId) {
      return service.delVolActBoard(volActId);
   }
   
   @GetMapping("findVolActNo")
   @ResponseBody
   public int findVolActNo() {
      return service.findVolActNo();
   }
   
	//자유게시판
	@GetMapping("freeBoardList")
	   public String freeBoardList(PageVO vo,FreeBoardVO freeVo, Model model,HttpServletRequest req,@RequestParam Integer volId,Principal prin
	                        , @RequestParam(value="start", required = false,defaultValue = "1")Integer start
	                        , @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
	      int total = service.getBoardListCnt(volId);
	        

	        vo = new PageVO(total, start, end, volId,null);

	        req.getSession().setAttribute("id",volId);
	        
	      List<FreeBoardVO> list = service.getBoardList(vo);
	      model.addAttribute("board",list);
	      model.addAttribute("vo",vo);
	      System.out.println(list);
	      
	      return "meeting/freeBoardList";
	   }
	
	//자유게시판 작성폼
	@GetMapping("freeBoardInsertPage")
	public String freeBoardInsertPage(Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req) {
		req.getSession().setAttribute("id",volId);
		
		return "meeting/freeBoardInsert";
	}
	//자유게시판 추가
	@PostMapping("freeBoardInsert")
	@ResponseBody
	public int freeBoardInsert(FreeBoardVO vo) {
		return service.insertBoard(vo);
	}
	
	//자유게시판 추가 번호
	@GetMapping("findNo")
	@ResponseBody
	public int findBoardNo() {
		return service.findBoardNo();
	}
	
	@GetMapping("findReviewNo")
	@ResponseBody
	public int findReviewNo() {
		return service.findReviewNo();
	}
	
	@GetMapping("updateFreeBoard")
	public String updateFreeBoard(FreeBoardVO freeVO,Model model,@RequestParam Integer volId,HttpServletRequest req) {
		FreeBoardVO vo = service.freeBoardInfo(freeVO);
		model.addAttribute("vo",vo);
		return "meeting/updateFreeBoard";
	}
	
	@GetMapping("freeBoardInfo")
	public String freeBoardInfo(FreeBoardVO freeVO,Model model,@RequestParam Integer volId,HttpServletRequest req) {
		FreeBoardVO vo = service.freeBoardInfo(freeVO);
		model.addAttribute("vo",vo);
		return "meeting/freeBoardInfo";
	}
	
	@PostMapping("updateFreeBoard")
	@ResponseBody
	public int updateFreeBoard(FreeBoardVO vo) {
		return service.updateFreeBoard(vo);
	}
	
	@PostMapping("deleteFreeBoard")
	@ResponseBody
	public int deleteFreeBoard(@RequestParam Integer volId,@RequestParam Integer boardNo) {
		return service.deleteFreeBoard(volId,boardNo);
	}
	
	
	//봉사후기
	@GetMapping("reviewBoardList")
	public String reviewBoardList(VolActReviewVO reviewVO,PageVO vo, Model model,@RequestParam Integer volId,HttpServletRequest req,Principal prin
								, @RequestParam(value="start", required = false,defaultValue = "1")Integer start
								, @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
		int total = service.volActReviewListCnt(reviewVO);
		
		vo = new PageVO(total, start, end,volId,null);
		
		List<VolActVO> list = service.volActReviewListPaging(vo);
		req.getSession().setAttribute("id",volId);
		model.addAttribute("board",list);
		model.addAttribute("vo",vo);
		return "meeting/reviewBoardList";
	}
	
	//봉사후기 작성폼
	@GetMapping("reviewBoardInsertPage")
	public String reviewBoardInsertPage(VolActVO vo,Principal prin,Model model,@RequestParam Integer volId,HttpServletRequest req) {
		if(prin != null && prin.getName() != null) {
	        model.addAttribute("userId",prin.getName());
	    } else {
	    	 model.addAttribute("userId","익명");
	    }
		req.getSession().setAttribute("id",volId);
		
		VolActVO volVO = service.volActInfo(vo);
		model.addAttribute("volVO",volVO);
		return "meeting/reviewBoardInsert";
	}
	
	@PostMapping("reviewBoardInsert")
	@ResponseBody
	public int reviewBoardInsert(VolActReviewVO vo) {
		return service.insertReview(vo);
	}
	
	@GetMapping("ReviewInfo")
	public String ReviewInfo(Principal prin,Model model,VolActReviewVO vo,HttpServletRequest req,@RequestParam Integer reviewId,@RequestParam Integer volId) {
		vo.setReviewId(reviewId);
		req.getSession().setAttribute("id",volId);
		
		VolActReviewVO reviewInfo = service.ReviewInfo(vo);
		model.addAttribute("info",reviewInfo);
		return "meeting/reviewBoardInfo";
	}
	
	@PostMapping("delReview")
	@ResponseBody
	public int delReview(@RequestParam Integer reviewId) {
		return service.delReview(reviewId);
	}
	
	//동아리 정보
	@GetMapping("aboutMeeting")
	public String aboutMeeting(@RequestParam Integer volId,Model model,HttpServletRequest req,Principal prin) {
		VolunteerVO vo = service.meetingInfo(volId);
		model.addAttribute("info",vo);
		
		req.getSession().setAttribute("id",volId);
		
		return "meeting/aboutMeeting";
	}
	
	//동아리 마이페이지
	@GetMapping("myInfoPage")
	public String myInfoPage(HttpSession session ,PageVO pageVO,VolMemVO vo,VolActReviewVO reviewVO,@RequestParam Integer volId,Model model,HttpServletRequest req,Principal prin) {
		vo.setMemId((String) session.getAttribute("userId"));
		pageVO.setWriter((String) session.getAttribute("userId"));
		List<VolMemVO> MemVolActList = service.MemVolActList(volId, (String) session.getAttribute("userId"));
		
		req.getSession().setAttribute("id",volId);
		List<VolMemVO> cnt = service.volCnt(vo);
		if(cnt.size()!=0) {
			model.addAttribute("cnt",cnt.get(0).getCnt());
		}else {
			model.addAttribute("cnt",0);
		}
		
		Date today = new Date();
		for(VolMemVO volmemVO : MemVolActList) {
			if(volmemVO.getVolDate().compareTo(today) >= 0) {
				volmemVO.setBigo("after");
			}else {
				volmemVO.setBigo("before");
			}
			reviewVO.setVolActId(volmemVO.getVolActId());
			reviewVO.setVolId(volId);
			reviewVO.setWriter((String) session.getAttribute("userId"));
			if(service.volReviewCnt(reviewVO) == null) {
				volmemVO.setChecking("ok");
			}else {
				volmemVO.setChecking("no");
			}
		}
		model.addAttribute("MemVolActList",MemVolActList);
		
		vo.setAppCode("h02");
		List<VolMemVO> date = service.meetingMemList(vo);
		model.addAttribute("date",date.get(0).getAppDate());
		
		int total = service.volActReviewListCnt(reviewVO);
		pageVO = new PageVO(total, 1, 10,volId,null);
		List<VolActVO> review = service.volActReviewListPaging(pageVO);
		
		model.addAttribute("review",review);
		
		
		return "meeting/myInfoPage";
	}
	
	//방장 마이페이지
	@GetMapping("managerPage")
	public String managerPage(@RequestParam Integer volId,VolMemVO volVO,Model model,HttpServletRequest req,Principal prin) {
		PageVO pvo = new PageVO(10, 1, 10, volId,null);
		req.getSession().setAttribute("id",volId);
	    List<VolActVO> list = service.meetingVolActListPaging(pvo);
	    model.addAttribute("volAct",list);
	    
	    List<VolActVO> after = new ArrayList<>();
		List<VolActVO> before = new ArrayList<>();
		Date today = new Date();
		
	    for(VolActVO vo : list) {
			if(vo.getVolDate().compareTo(today) >= 0) {
				after.add(vo);
			}else {
				before.add(vo);
			}
		}
		model.addAttribute("after",after);
		model.addAttribute("before",before);
		
	    volVO.setAppCode("h02");
	    List<VolMemVO> member = service.meetingMemList(volVO);
		model.addAttribute("member",member);
		System.out.println("member"+member);
		
		volVO.setAppCode("h01");
		List<VolMemVO> whobo = service.meetingMemList(volVO);
		model.addAttribute("whobo",whobo);
		System.out.println("whobo"+whobo);
		return "meeting/managerInfo";
	}
	
	//모임 탈퇴 페이지
	@GetMapping("WithdrawalMeeting")
	public String WithdrawalMeeting(@RequestParam Integer volId,Model model,HttpServletRequest req,Principal prin) {
		req.getSession().setAttribute("id",volId);
		return "meeting/WithdrawalMeeting";
	}
	//모임 탈퇴 프로세스
	@GetMapping("WithdrawalProcess")
	@ResponseBody
	public int WithdrawalProcess(VolMemVO vo) {
		return service.withdrawalMeeting(vo);
	}
	
	//모임 삭제 프로세스
	@GetMapping("delMeet")
	@ResponseBody
	public int delMeet(@RequestParam Integer volId) {
		return service.deleteMeeting(volId);
	}
	
	@GetMapping("goodbye")
	public String goodbye() {
		return "meeting/goodbye";
	}
	
	//모임 등록 페이지
	@GetMapping("regMeetingPage")
	public String redMeeting(Model model) {
		List<DonaVO> region = donaService.getCategoryList("f");
		List<DonaVO> categoryList = donaService.getCategoryList("z");
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("region", region);
		return "meeting/regMeeting";
	}
	
	@Autowired
	FileService fileService;
	
	//모임등록 프로세스
	@PostMapping(value="regMeeting",consumes = "multipart/form-data")
	@ResponseBody
	@Transactional
	public int insertMeeting(VolunteerVO vo,HttpSession session,
					@RequestPart(value = "uploadfiles" ,required = false) MultipartFile[] uploadfiles) throws IOException {
		System.out.println("지금 들어감!!");
		//모임등록
		service.insertMeeting(vo);
		//파일등록
		if(uploadfiles!=null) {
			int codeNo = vo.getVolId();
			String code = "p09";
			fileService.uploadFiles(uploadfiles, code, codeNo,(String)session.getAttribute("userId"),0);
		}
		//모임장등록
		VolMemVO memVO = new VolMemVO();
		memVO.setVolId(vo.getVolId());
		memVO.setAppCode("h02");
		memVO.setAppReason(null);
		memVO.setMemId(vo.getMemId());
		service.approveMeeting(memVO);
		System.out.println("지금 나옴!!");
		return vo.getVolId();
	}
	
	@GetMapping("updateMeeting")
	public String updateMeetingPage(@RequestParam Integer volId,HttpServletRequest req,Model model) {
		req.getSession().setAttribute("id",volId);
		VolunteerVO vo = service.meetingInfo(volId);
		model.addAttribute("info",vo);
		return "meeting/updateMeeting";
	}
	
	@PostMapping(value="updateMeeting",consumes = "multipart/form-data")
	@ResponseBody
	@Transactional
	public int updateMeeting(VolunteerVO vo,HttpSession session,
						@RequestPart(value = "uploadfiles" ,required = false) MultipartFile[] uploadfiles,@RequestParam Integer volId) throws IOException {
		if(uploadfiles!=null) {
			service.deleteFile(volId);
			fileService.uploadFiles(uploadfiles, "p09", volId,(String)session.getAttribute("userId"),0);
		}
		return service.updateMeeting(vo);
	};
	
	@PostMapping("insertTag")
	@ResponseBody
	public int insertTag(TagVO vo) {
		return service.insertTag(vo);
	}
	
	@PostMapping("insertvolActMem")
	@ResponseBody
	public int insertvolActMem(VolMemVO vo) {
		return service.insertVolActMem(vo);
	}
	
}