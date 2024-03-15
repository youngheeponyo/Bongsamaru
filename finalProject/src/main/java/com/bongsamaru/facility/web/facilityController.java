package com.bongsamaru.facility.web;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.challenges.service.ChallengesService;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.SubCodeVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.common.mapper.CommonMapper;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.HeartVO;

import lombok.extern.log4j.Log4j2;
/**
 * 시설소개 관련, crud
 * 시설 마이페이지 - 기부내역조회, 시설봉사 등록 조회 승인...
 * @author 예빈
 * 
 */
@Log4j2
@Controller
public class facilityController {
	
	@Autowired
	FacilityService facilityService;
	@Autowired
    private FileService fileService;
	@Autowired
	CommonMapper commonMapper;
	@Autowired
	BongsaService bongsaService;
	@Autowired
	ChallengesService challengeService;
	
	/**
	 * 사이트에 가입한 시설 리스트
	 * @param facZip2
	 * @param facType
	 * @param model
	 * @return
	 */
	@GetMapping("/facilityList")
	public String getFacilityList(String facZip2, 
								  String facType,
								  PageVO vo, 
								  Integer cntPerPage,
								
								  Model model,
								  @RequestParam(value="category", required = false)String category,
								  @RequestParam(value="start", required = false,defaultValue = "1")Integer start,
								  @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
		int total = facilityService.getCategoryCount(facZip2, facType);
		
        // start와 end가 null일 경우 기본값으로 1과 10을 사용
		
        vo = new PageVO(total,start, end, category ,6);
     	model.addAttribute("vo",vo);
     	model.addAttribute("category",category);
     	
     	List<FacilityVO> list = facilityService.getFacilityList(vo,facZip2, facType);
     	model.addAttribute("facilityList", list);
     	log.info(list);
     	List<SubCodeVO> z =commonMapper.subCodeList("z");
     	List<SubCodeVO> f = commonMapper.subCodeList("f");
     	model.addAttribute("subZ", z);
     	model.addAttribute("subF", f);
		return "facility/facilityList";
	}
	
	
	/**
	 * 시설상세정보사이트
	 * @param facId
	 * @param model
	 * @param request
	 * @return
	 */
	 @GetMapping("fInfo/facilityInfo")
		public String getFacilityInfo(@RequestParam(name="facId") String facId,
										Model model,HttpServletRequest request) {
		
		 	FacilityVO findVO =facilityService.getFacilityInfo(facId);
		 	log.info(facId);
			request.getSession().setAttribute("facilityInfo",findVO);
			model.addAttribute("facilityInfo", findVO);
			log.info( findVO);
			return "facility/facilityInfo";
		}
	 
	/**
	 * 시설이 기부하는 관련 정보
	 * @param facId
	 * @param facilityVO
	 * @param model
	 * @return
	 */
	 
	@GetMapping("fInfo/fundingList")
	public String getFundingList( PageVO vo2,
								@RequestParam(name="facId") String facId,  
								FacilityVO facilityVO,
								Integer recStat,
								Model model,
								PageVO vo,
								@RequestParam(value="category", required = false)String category,
								@RequestParam(value="start", required = false,defaultValue = "1")Integer start,
								 @RequestParam(value="end", required = false,defaultValue = "10")Integer end ) {
		
		int total = facilityService.getFacDonCount(1, facId);
		vo = new PageVO(total,start, end, category ,3);
     	model.addAttribute("vo",vo);
     	model.addAttribute("category",category);								
		//기부중리스트
		List<FundingVO> list = facilityService.getFundingList(vo,facId);
		model.addAttribute("fundingList", list);
		
		int total2 = facilityService.getFacDonCount(0, facId);
		vo2 = new PageVO(total2,start, end, category ,3);
     	model.addAttribute("vo2",vo2);
     	model.addAttribute("category",category);
		
		//기부마감리스트
		List<FundingVO> listed = facilityService.getFundedList(vo2,facId);
		model.addAttribute("fundedList", listed);
		return "facility/facilityDonation";
	}

	/**
	 * 시설이 봉사한 관련 정보 리스트
	 * @param facId
	 * @param model
	 * @return 봉사리스트보는 페이지
	 */
	@GetMapping("fInfo/volunteerList")
	public String getVolunteerList(@RequestParam(name="facId") String facId,
								 	Model model,
								 	PageVO vo, PageVO vo2,
								 	@RequestParam(value="category", required = false)String category,
								 	@RequestParam(value="start", required = false,defaultValue = "1")Integer start,
								 	@RequestParam(value="end", required = false,defaultValue = "10")Integer end ) {
		int total = facilityService.getVolFCount(facId);
		vo = new PageVO(total,start, end, category ,3);
     	model.addAttribute("vo",vo);
		List<VolunteerVO> listf = facilityService.getVolFList(vo,facId);
		model.addAttribute("volFList", listf);
		int total2 = facilityService.getVolICount(facId);
		log.info(total2);
		vo2 = new PageVO(total2,start, end, category ,3);
     	model.addAttribute("vo2",vo2);
		List<VolunteerVO> list = facilityService.getVolIList(vo2,facId);
		model.addAttribute("volIList", list);
		log.info(list);
		return "facility/facilityVolunteer";
	}
	
	
	
	//시설 마이페이지	
	//마이페이지 (기부)
	@GetMapping("/fac")
	public String getFacilityMyPage(Model model,Integer recStat,Principal principal,
									String facId,
									PageVO vo, 
									@RequestParam(value="category", required = false)String category,
									@RequestParam(value="start", required = false,defaultValue = "1")Integer start,
									@RequestParam(value="end", required = false,defaultValue = "10")Integer end ) {
		
		int total = facilityService.getVolDonCount(principal.getName(),"0","1");
		  vo = new PageVO(total,start, end, category ,5);
	      model.addAttribute("vo",vo);
	      List<DonaVO> list = facilityService.getDonaList(vo, principal.getName(), "0","1"); 
	      model.addAttribute("donaList", list);
		return "facility/myPageDona2";
	}
	
	
	  @GetMapping("/fac/donaInfo")
	   public String getFacilityMyPageDona(Model model,
			   								String facId,
			   								Principal principal,
			   								PageVO vo, PageVO vo2,
											@RequestParam(value="category", required = false)String category,
											@RequestParam(value="start", required = false,defaultValue = "1")Integer start,
											@RequestParam(value="end", required = false,defaultValue = "10")Integer end ) {
		  
		  int total = facilityService.getVolDonCount(principal.getName(),"1","0");
		  vo = new PageVO(total,start, end, category ,5);
	      model.addAttribute("vo",vo);
	      log.info(vo);
	      log.info(principal.getName());
	      List<DonaVO> list1 = facilityService.getDonaList(vo, principal.getName(), "1","0"); // 모금완료
	      model.addAttribute("donaList1", list1);
	      log.info(list1);
	      
	      
	      int total2 = facilityService.getVolDonCount(principal.getName(),"1","1");
	      vo2 = new PageVO(total2,start, end, category ,3);
	      model.addAttribute("vo2",vo2);
	      log.info(vo2);
	      List<DonaVO> list0 = facilityService.getDonaList(vo2, principal.getName(),"1", "1"); //모금중
	      model.addAttribute("donaList0", list0);
	      log.info(list0);
	      return "facility/myPageDona";
	   }
	
	 //마이페이지(시설봉사신청, 신청진행상황)
	  @GetMapping("/fac/volJoin")
	  public String getFacilityMyPageVol(Model model,
	                                     Principal principal,
	                                     PageVO vo, 
	                                     Integer cntPerPage,
	                                     @RequestParam(value="category", required = false)String category,
	                                     @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
	                                     @RequestParam(value="end", required = false, defaultValue = "10") Integer end)
	  {
		 // model.addAttribute("category",category);
		  String facId = principal.getName();
		  int total = facilityService.getFVolCategoryCount(facId);
		  vo = new PageVO(total, start, end,category, 5);
	      log.info(vo);
	      List<VolActVO> list = facilityService.getVolunteerJoinList(vo,facId);
	      model.addAttribute("volList", list);
	      List<SubCodeVO> f = commonMapper.subCodeList("f");
	     	model.addAttribute("subF", f);
	     	log.info(f);
	      model.addAttribute("vo", vo);
	      log.info(list);
	      return "facility/myPageVolBefore";
	  }
	//마이페이지(시설 봉사 마무리 후)
		@GetMapping("/fac/volFinish")
		  public String getFacilityVolFinish(Model model,
							                  Principal principal,
							                  PageVO vo, 
							                  Integer cntPerPage,
							                  @RequestParam(value="category", required = false)String category,
							                  @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
							                  @RequestParam(value="end", required = false, defaultValue = "10") Integer end){
			String facId = principal.getName();
			int total = facilityService.getFinishVolCategoryCount(facId);
			vo = new PageVO(total, start, end,category, 5);
			List<VolActVO> list = facilityService.getVolunteerFinishList(vo,facId);
			model.addAttribute("volList", list);
			model.addAttribute("vo", vo);
			log.info(list);
			return "facility/myPageVolAfter";
		}
		
	
	@GetMapping("/VolAppList")
	@ResponseBody
	public List<VolMemVO> getVolunteerAppList(Model model, Integer volActId) {
		List<VolMemVO> listApp = facilityService.getVolunteerAppList(volActId);
		model.addAttribute("volAppList", listApp);
		log.info(listApp);
		log.info(volActId);
		return listApp;
	}
	
	@PostMapping("/volAppUpdate")
	@ResponseBody
	public int volAppUpdate(Model model, Integer volActId, String memId) {
		int list = facilityService.volAppUpdate(volActId, memId);
		//model.addAttribute("volAppUpd", list);
		return list;
	}
	
	@PostMapping("/volAppInsert")
	@ResponseBody
	public int volAppInsert(Model model, VolMemVO volMemVO) {
		int list = facilityService.volAppInsert(volMemVO);
		//model.addAttribute("volAppIns", list);
		return list;
	}
	
	@GetMapping("/showJoin")
	public VolMemVO getJoinApp(Model model, Integer volActId) {
		VolMemVO info = facilityService.getJoinApp(volActId);
		model.addAttribute("info", info);
		return info; 
	}

	
	  //회원이 시설봉사 정보 조회
	 
	  @GetMapping("/facilityVolInfo")
	  @ResponseBody
	  public VolActVO getFacVolInfo(Model model, Integer volActId) { 
		  
		
		  VolActVO info = facilityService.getFacVolInfo(volActId);
		  model.addAttribute("info", info);
		  return info;
	  }
	
	//회원이 시설봉사 신청
	@PostMapping("/joinVol")
	@ResponseBody
	public int getJoinVol(Model model,VolMemVO volMemVO,Principal principal,HttpServletRequest req){
		req.getSession().getAttributeNames();
		volMemVO.setMemId(principal.getName());
		
		log.info(principal.getName());
		int joinVol = facilityService.insertJoinVolunteer(volMemVO);
		log.info(joinVol);
		return joinVol;
	}
	@GetMapping("/getJoinAppCheck")
	  @ResponseBody
	  public int getJoinAppCheck(Model model, @RequestParam(name="volActId") Integer volActId, @RequestParam(name="memId")String memId) { 
		  int info = facilityService.getJoinAppCheck(volActId,memId);
		  model.addAttribute("info", info);
		  return info;
	  }
	@GetMapping("/getFacRevCheck")
	  @ResponseBody
	  public int getFacRevCheck(Model model, @RequestParam(name="detailCate") Integer detailCate) { 
		  int info = facilityService.getFacRevCheck(detailCate);
		  model.addAttribute("info", info);
		  return info;
	  }
	
	//시설봉사등록
	@PostMapping("/InvolJoin")
	@ResponseBody
	public int facVolInsert (Model model,@RequestParam(value = "files", required = false) MultipartFile[] uploadFiles,VolActVO volActVO, Principal principal) {
		volActVO.setFacId(principal.getName());
		log.info("값"+volActVO);
		
		int result = facilityService.InsertFacVol(volActVO);
		log.info("값"+result);
		
		
		  try { 
			  fileService.uploadFiles(uploadFiles,"p14", volActVO.getVolActId(),volActVO.getFacId(),1);
		  }catch (IOException e) {
			  e.printStackTrace(); 
		   }
		return result;
	}
		
	@PostMapping("/InsertVolHeart")
	@ResponseBody
	public int volHeartInsert (@RequestBody HeartVO heartVO, Principal principal) {
		int result = facilityService.insertVolHeart(heartVO);
		return result;
	}
	/**
	 * 봉사후기 등록페이지
	 * @return
	 */
	@GetMapping("volReviewInsert")
	public String insertVolReview(Model model, Integer volActId,Principal principal,HttpServletRequest req) {
		  if(principal != null && principal.getName() != null) {
	           model.addAttribute("userId",principal.getName());
	       } else {
	           model.addAttribute("userId","없음");
	       }
		  log.info(principal.getName());
		return "facility/volReview";
	}
	/**
	 * 봉사후기 등록 프로세스
	 * @param boardVO
	 * @return 
	 * @return
	 */
	@PostMapping("volReviewInsert")
	@ResponseBody
	public BoardVO insertVolReview( BoardVO boardVO, Principal principal) {
		boardVO.setMemId(principal.getName());
		facilityService.insertVolReview(boardVO);
		/*
		 * try { fileService.uploadFiles(uploadFiles,"p16",
		 * volActId,boardVO.getMemId());
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */
		return boardVO;
	}
	@GetMapping("findBoradNo")
	   @ResponseBody
	   public int findBoardtNo() {
	      return facilityService.findBoardNo();
	   }
	/**
	 * 봉사후기 리스트
	 * @param model
	 * @param category
	 * @return
	 */
	@GetMapping("fInfo/volReviewList")
	public String getNoticeOne(Model model, String memId) {
		
		List<BoardVO> list = facilityService.getVolReviewList(memId);
		log.info("시설이름"+ memId);
		model.addAttribute("list",list);
		log.info("시설후기"+ list);
		return "facility/reviewList";
	}
	/**
	 * 봉사후기 info
	 * @param category
	 * @param detailCate
	 * @param codeNo
	 * @param model
	 * @return
	 */
	@GetMapping("fInfo/volReviewInfo")
	public String getNoticeOne(Integer detailCate,Model model) {
		BoardVO vo = facilityService.getVolReviewInfo(detailCate);
		model.addAttribute("info",vo);
		return "facility/volReviewInfo";
	}
	/**
	 * 봉사후기수정
	 * @param boardVO
	 * @return
	 */
	@PostMapping("updateVolReview")
	public String updateVolReview(BoardVO boardVO) {
		facilityService.updateVolReview(boardVO);
		return "redirect:boardList?category=b03";
	}
}
