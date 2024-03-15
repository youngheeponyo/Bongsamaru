package com.bongsamaru.challenges.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.challenges.service.ChallengesService;
import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.file.service.FilesVO;

import lombok.extern.log4j.Log4j2;
/**
 * 챌린지 관련, crud
 * @author 예빈
 * 
 */
@Log4j2
@Controller
public class challengesController {
	
	@Autowired
	ChallengesService challengeService;
	
	@Autowired
    private FileService fileService;
	
	/**
	 * 챌린지 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("/challengeList")
	public String getChallengeList(Model model) {
		List<ChallengesVO> list = challengeService.getChallengeList();
		model.addAttribute("challengeList", list);
		log.info("목록에서 count가 나오느냐" +list);
		return "challenge/challengeList";
	}
	
	/**
	 * 챌린지 정보
	 * @param chalId
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/challenge/challengeInfo")
	public String getChallengeInfo(@RequestParam(name="chalId") Integer chalId, 
			                       Model model,
			                       HttpServletRequest request) {
		//챌린지정보세션저장
		ChallengesVO vo = challengeService.getChallengeInfo(chalId);
		request.getSession().setAttribute("challengeInfo",vo);
		
		//파일리스트
		List<FilesVO> file = challengeService.getFileList(vo.getChalId(),"p03");
		model.addAttribute("FileList", file); 
		request.getSession().setAttribute("FileList",file);
		return "challenge/challengeInfo";
		 
	}
	
	/**
	 * 한챌린지에 대한 참여자 리스트
	 * @param chalId
	 * @param model
	 * @return
	 */
	@GetMapping("/challenge/challengesList")
	public String getChallengesList(@RequestParam(name="chalId") Integer chalId, Model model) {
		List<ChallengesVO> dList = challengeService.getChallengesList(chalId);
		log.info(dList);
	    model.addAttribute("challengesList", dList);
		 return "challenge/challengesList";
	}
	
	/**
	 * 등록페이지 열기
	 * @return
	 */
	@GetMapping("/challengeInsert")
	public String insertChallengeForm() {
		return "challenge/challengeInsert";
	}
	
	
	/**
	 * 게시글등록
	 * @param uploadFiles
	 * @param challengeVO
	 * @return
	 */
	@PostMapping("/challengeInsert")
	@ResponseBody
	
	public int getChallengeInsert(@RequestPart MultipartFile[] uploadFiles, ChallengesVO challengeVO,Principal principal) {
		challengeVO.setMemId(principal.getName());
		challengeService.getChallengeInsert(challengeVO);
		log.info(challengeVO);
		int chalId = challengeVO.getChalId();
		try {
			fileService.uploadFiles(uploadFiles,"p03", chalId,challengeVO.getMemId(), 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return chalId;
		
	}
	@GetMapping("/getChallCheck")
	  @ResponseBody
	  public int getChallCheck(Model model,
			  				  @RequestParam(name="chalId") Integer chalId, 
			  				  @RequestParam(name="memId") String memId,  
			  				  @RequestParam(name="partDate") Date partDate) { 
		  int info = challengeService.getChallCheck(memId,partDate,chalId);
		  model.addAttribute("info", info);
		  return info;
	  }
	
	/**
	 * 게시글 참여
	 * @param uploadFiles
	 * @param challengeVO
	 * @return
	 */
	@PostMapping("/challenge/challengeDeInsert")
	@ResponseBody	
	public Integer getChallengesInsert (@RequestPart MultipartFile[] uploadFiles, 
										ChallengesVO challengeVO,
										Principal principal) {
		//챌린지참여등록
		challengeVO.setMemId(principal.getName());
		challengeService.getChallengesInsert(challengeVO);
		
		//파일등록
		Integer chalDetId = challengeVO.getChalDetId();
		try {
			fileService.uploadFiles(uploadFiles, "p04", chalDetId, challengeVO.getMemId(),1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return chalDetId;
	}

	@PostMapping("/reportInsert")
	@ResponseBody
	public int reportInsert(Model model, @RequestBody ReportVO reportVO, Principal principal) {
		reportVO.setMemId(principal.getName());
		reportVO.setCategory("r01");
		log.info(reportVO);
		int report = challengeService.reportInsert(reportVO); 
		
		return report; 
	}
	
}
