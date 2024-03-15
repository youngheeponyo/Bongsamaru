package com.bongsamaru.dona.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.common.service.MailUtil;
import com.bongsamaru.common.service.MailVO;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FileService;

import lombok.extern.log4j.Log4j2;

/**
 * '기부' 페이지에서 담당하는 모든 기능(게시글 crud, 결제, 신청폼)
 * 
 * @author 박현아
 * @since 2023.12.xx
 * @version 1.0
 * @see
 *
 * 
 */
@Log4j2
@Controller
public class DonaController {

	@Autowired
	DonaService donaService;

	@Autowired
	FileService fileService;

	@Autowired
	MailUtil mail;
	
	@GetMapping("/test")
	public String test(@RequestParam("id") Integer donId,
			@RequestParam(name = "facId", required = true) String facId, Model model) {
		// 상세페이지
		DonaVO dona = donaService.getDonaDetail(donId, facId);
		model.addAttribute("dona", dona);

		// 기부자목록
		List<DonaVO> donaList = donaService.getDonerList(donId);
		model.addAttribute("list", donaList);

		// 댓글리스트
		List<DonaVO> commentList = donaService.getCommentList(donId);
		model.addAttribute("comment", commentList);
		
		//후기글
		DonaVO rev = donaService.getDonaReview(donId);
		System.out.println("======================");
		System.out.println(rev);
		model.addAttribute("rlist", rev);
		
		// 랜덤
		List<DonaVO> random = donaService.randomlyShow();
		model.addAttribute("randomlist", random);

		return "donation/donaDetail";
	}
	
	

	/**
	 * 전체 기부 리스트(모집중, 모집완료), 카테고리 리스트
	 * 
	 * @param model
	 * @return 기부 메인페이지
	 */

	// 기부 메인페이지 - 전체리스트
	@GetMapping("/donaMain")
	public String openDonaMainPage(DonaVO donaVO , Model model) {
		
		List<DonaVO> donaList = donaService.getDonaListByCategory(donaVO);
		log.info(donaVO);
		model.addAttribute("list", donaList);
		for(DonaVO a : donaList) {
			log.info(a);
		}
		
//		model.addAttribute("recruitList", recruitList);
//		model.addAttribute("completedList", completedList);

		String h = "f"; //** 변수이름 기부유형으로 정해놓기
		List<DonaVO> categoryList = donaService.getCategoryList(h);
		model.addAttribute("categoryList", categoryList);

		return "donation/donaMain";
	}

	/**
	 * 
	 * @param 기부    관련 category 정보
	 * @param model 카테고리에 따른 리스트 분류
	 * @return 메인페이지
	 */
	// 카테고리에 따른 리스트 가져오기
	@GetMapping("/donaMain/category/{category}")
	public String openDonaMainPageByCategory(@PathVariable String category, DonaVO donaVO, Model model) {
		donaVO.setCategory(category);
		
		List<DonaVO> donaListByCategory = donaService.getDonaListByCategory(donaVO);
		System.out.println("카테고리출력" + donaListByCategory);
		model.addAttribute("clist", donaListByCategory);

		return "donation/donaMain";
	}

	/**
	 * 
	 * @param donId 게시글 번호
	 * @param facId 로그인한 시설 아이디
	 * @param model donId 상세게시글, 기부자목록, 댓글리스트, 랜덤게시글 리스트
	 * @return 상세페이지
	 */

	// 기부상세 페이지
	@GetMapping("/donaDetail")
	public String donaDetailPage2(@RequestParam("id") Integer donId,
			@RequestParam(name = "facId", required = true) String facId, Model model) {
		
		//날자 
		DonaVO donaVO = new DonaVO();
		 Date endPeriod = donaVO.getEndPeriod();
		 
		// 상세페이지
		DonaVO dona = donaService.getDonaDetail(donId, facId);
		model.addAttribute("dona", dona);

		// 기부자목록
		List<DonaVO> donaList = donaService.getDonerList(donId);
		model.addAttribute("list", donaList);

		// 댓글리스트
		List<DonaVO> commentList = donaService.getCommentList(donId);
		model.addAttribute("comment", commentList);
		
		//후기글
		DonaVO rev = donaService.getDonaReview(donId);
	
		model.addAttribute("rlist", rev);
		
		// 랜덤
		List<DonaVO> random = donaService.randomlyShow();
		model.addAttribute("randomlist", random);

		return "donation/donaDetail";
	}

	/**
	 * 
	 * @param donaVO 댓글정보가 담긴 VO
	 * @param model  댓글 리스트 정보
	 * @return 상세페이지
	 */
	// 댓글 삽입1
	@PostMapping("/donaDetail")
	@ResponseBody
	public String insertComment(@RequestBody DonaVO donaVO, Model model) {
		System.out.println(donaVO);
		donaService.insertComment(donaVO);
		return "댓글 등록 완료!";
	}

	
	/**
	 * 
	 * @param id    게시글 번호
	 * @param model 기부자 목록 리스트
	 * @return 기부자리스트
	 */
	// 기부상세 - 기부자목록
	@GetMapping("/donaDetail/{donId}")
	@ResponseBody
	public List<DonaVO> donerList(@PathVariable Integer donId) {
		System.out.println("여기여기여기다!! " + donaService.getDonerList(donId));
		return donaService.getDonerList(donId);
	}

	// 연장 이메일보내기
	// @Scheduled(cron = "0 0 0 * * *") // 매일 0시 0분에 실행
	public void sendExtensionEmails() {
		List<DonaVO> extensionTargetList = donaService.selectExtensionTargetList();
		for (DonaVO vo : extensionTargetList) {
			MailVO mailvo = new MailVO();
			// 이메일 내용 설정
			String emailContent = String.format(
					"<div style=\"background-color: lightgray; text-align: center; font-weight: bold; font-size: 17px;\">"
							+ "<h1 style=\"padding: 50px;\">행복마루에서 보내드리는 이메일입니다.</h1>"
							+ "<p style=\"padding: 50px;\">안녕하세요. %s님,<br>" + "%s 기부 게시글의 마감기한이 1일 남았습니다.<br>"
							+ "현재 목표금액 미달로 1회 한정 2주 이내로 기간 연장이 가능합니다.<br>" + "연장은 홈페이지의 마이페이지 안에서 가능합니다. 감사합니다.</p>"
							+ "</div>",
					vo.getFacId(), vo.getDonId());

			mailvo.setEmailContent(emailContent);
			mailvo.setRecipientEmail(vo.getFacEmail());
			mail.sendMail(mailvo);
		}
	}

	/**
	 * 스케쥴러실행 : 기부 마감시 모금종료
	 */

	// 모금종료 업데이트
	@Scheduled(cron = "0 0 0 * * *") // 매일 0시 0분에 실행
	public void updateRecStat() {
	    try {
	        DonaVO donaVO = new DonaVO();
	        donaService.updateRecStat(donaVO);
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	    }
	}

	/**
	 * 
	 * @param donaVO 모금기한 - 기간 연장.
	 * @return 시설 마이페이지로 이동 예정. 
	 */
	// 기한연장하기
//	@PutMapping("/facilityMyPage/donaInfo/extension")
//	@ResponseBody
//	public String extendDonationPeriod(@RequestBody DonaVO donaVO) {
//		log.info(donaVO);		
//		
//		donaService.extendDonationPeriod(donaVO);
//		return "facility/myPageDona"; 
//	}
	
	@PutMapping("/facilityMyPage/donaInfo/extension")
	@ResponseBody
	public ResponseEntity<String> extendDonationPeriod(@RequestBody DonaVO donaVO) {
	    log.info(donaVO);
	    
	    donaService.extendDonationPeriod(donaVO);
	    return ResponseEntity.ok("연장 성공!");
	}

	/**
	 * 
	 * @param 결제를 할 기부 상세페이지
	 * @return 결제페이지로 이동
	 */
	// 결제창
	@GetMapping("/payment")
	public String openPaymentPage(@RequestParam Integer donId, HttpSession session) {
		
		String stat = (String) session.getAttribute("Role");
//		if(stat !="M02") {
//			return "ng";			
//		} 
		
		return "donation/payment";
	}

	/**
	 * 
	 * @param donaVO 결제정보 담긴 곳
	 * @param model  결제후 결제정보 insert
	 * @return 결제완료창으로 이동
	 */
	// 찐 결제하기\
	@PostMapping("/paymentProcess")
	@ResponseBody
	public String payProcess(@RequestBody DonaVO donaVO,  Model model, Principal prin) {

		donaService.paymentProcess(donaVO);
		return "ok";
	}

	/**
	 * 
	 * @return 결제완료창으로 이동
	 */
	// 결제완료
	@GetMapping("/paymentComplete")
	public String openPaymentCompletePage() {
		return "donation/paymentComplete";
	}

	/**
	 * 
	 * @param model
	 * @return 기부신청폼으로 이동
	 */
	// 기부신청폼
	@GetMapping("/applyform")
	public String openapplyform(Model model) {
		return "donation/forDonaForm";
	}
	
	@PostMapping("/applydona")
	@ResponseBody
	public String applyDona(DonaVO donaVO, Model model) {
		donaService.applyAlertDona(donaVO);
		return "신청완료";
	}

	/**
	 * 
	 * @param model 카테고리 리스트, 입력값 담을 VO
	 * @return 기부 게시글 등록폼
	 */
	// 등록폼 이동
	@GetMapping("/form")
	public String openRegitform(Model model) {
		String h = "f";
		List<DonaVO> categoryList = donaService.getCategoryList(h);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("donaVO", new DonaVO()); // 빈개체 추가
		return "donation/form";
	}

	/**
	 * 
	 * @param donaVOㅎ     ㅐ당 게시글, 시설회원 아이디 정보 있음
	 * @param uploadfiles 파일업로드
	 * @param model       데이터
	 * @return 마이페이지로 이동
	 * @throws IOException
	 */

	// 등록폼 INSERT
	@PostMapping(value = "regitForm", consumes = "multipart/form-data")
	@ResponseBody
	public String registerDona(DonaVO donaVO,
			                   @RequestPart("uploadfiles") MultipartFile[] uploadfiles, 
			                   Model model) throws IOException {
		//기부등록
		log.info(donaVO);
		donaService.insertDonation(donaVO);
		
		
		//첨부파일등록
		int codeNo = donaVO.getDonId();
		
		String code = "p08";
		fileService.uploadFiles(uploadfiles, code, codeNo, donaVO.getFacId(),1);

		return "ok";
	}


	
	/**
	 * 
	 * @param model
	 * @return 후기작성 폼으로 이동
	 */
	@GetMapping("/reviewform")
	public String openRevform( Model model) {

		
		return "donation/reviewForm";
	}

	/**
	 * 
	 * @param donaVO      donId, facId 정보 담긴 곳
	 * @param uploadfiles 파일업로드
	 * @param model       데이터 담아서
	 * @return 마이페이지로 이동
	 * @throws IOException
	 */
	// 후기폼 Insert
	@PostMapping(value = "reviewForm3", consumes = "multipart/form-data")
	@ResponseBody
	public String registerReview(DonaVO donaVO,
			@RequestParam("uploadfiles") MultipartFile[] uploadfiles, Model model) throws IOException {
		donaService.insertReview(donaVO);

		int codeNo = donaVO.getDonId();
		String code = "p07";
		fileService.uploadFiles(uploadfiles, code, codeNo, donaVO.getFacId(),1);

		return "후기등록성공!";
	}
	
	// 후기폼 Insert
	@PostMapping("/reviewForm")
	@ResponseBody
	public int registerReview(DonaVO donaVO,
			 Model model) {
		donaService.insertReview(donaVO);	


		return donaVO.getDonRevId();
	}
	

	@PostMapping("/receiptAlert")
	@ResponseBody
	public String alertRec(DonaVO donaVO, Model model) {
		
		donaService.receiptAlertDona(donaVO);
		return "영수증등록완료";
	}
	
					
}