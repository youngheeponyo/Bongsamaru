package com.bongsamaru.bongsa.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.bongsa.service.BongsaDTO;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.service.CommonService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BongsaController {
	@Autowired
	AdminService userService;
	
	@Autowired
	BongsaService bongsaService;
	
	@Autowired
	CommonService commonService;
	
	
	@GetMapping("/daily")
	public String goToDaily(PageVO vo, Model model,
			  @RequestParam(value="searchKeyword", required = false) String searchKeyword,
		        @RequestParam(value="category", required = false) String category,
		        @RequestParam(value="location", required = false) String zip,
		        @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
		        @RequestParam(value="end", required = false, defaultValue = "8") Integer end,
		        @RequestParam(value="startDate", required = false, defaultValue="2000-01-01") 
				@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
		        @RequestParam(value="endDate", required = false, defaultValue="2050-12-31") 
				@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
		    // 필터링 조건 설정
		    if (category != null && !category.isEmpty()) {
		        vo.setCategory(category);
		    }
		    if (zip != null && !zip.isEmpty()) {
		        vo.setVolZip2(zip);
		    }
		    Integer total = bongsaService.cntVol(vo).getDailyVol();
		    vo = new PageVO(total, start, end, category, searchKeyword, 8 ,zip);
		    model.addAttribute("cate", commonService.selectSubCode("f"));
		    List<BongsaDTO> list = bongsaService.getVolTagDTO(vo, startDate, endDate, "e01");
		    log.info(list);
		    model.addAttribute("list", list);
		    model.addAttribute("vo", vo);
		    model.addAttribute("location", commonService.selectSubCode("z"));
		return "bongsa/DailyVol";
	}

	@GetMapping("/Group")
	public String goToGroup(PageVO vo, Model model,
	        @RequestParam(value="searchKeyword", required = false) String searchKeyword,
	        @RequestParam(value="category", required = false) String category,
	        @RequestParam(value="location", required = false) String zip,
	        @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
	        @RequestParam(value="end", required = false, defaultValue = "8") Integer end,
	        @RequestParam(value="startDate", required = false, defaultValue="2000-01-01") 
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
	        @RequestParam(value="endDate", required = false, defaultValue="2050-12-31") 
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
	    // 필터링 조건 설정
	    if (category != null && !category.isEmpty()) {
	        vo.setCategory(category);
	    }
	    if (zip != null && !zip.isEmpty()) {
	        vo.setVolZip2(zip);
	    }
	    int total = bongsaService.cntVol(vo).getGroupVol();
	    vo = new PageVO(total, start, end, category, searchKeyword, 8 ,zip);
	    model.addAttribute("cate", commonService.selectSubCode("f"));
	    List<BongsaDTO> list = bongsaService.getVolTagDTO(vo, startDate, endDate, "e02");
	    log.info(list);
	    model.addAttribute("list", list);
	    model.addAttribute("vo", vo);
	    model.addAttribute("location", commonService.selectSubCode("z"));
		return "bongsa/GroupVol";
	}
	@GetMapping("/FacilityVol")
	public String goToFacility(PageVO vo, Model model,
	        @RequestParam(value="searchKeyword", required = false) String searchKeyword,
	        @RequestParam(value="category", required = false) String category,
	        @RequestParam(value="location", required = false) String zip,
	        @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
	        @RequestParam(value="end", required = false, defaultValue = "8") Integer end,
	        @RequestParam(value="startDate", required = false, defaultValue="2000-01-01") 
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
	        @RequestParam(value="endDate", required = false, defaultValue="2099-12-31") 
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate
			) {

	    // 필터링 조건 설정
	    if (category != null && !category.isEmpty()) {
	        vo.setCategory(category);
	    }
	    if (zip != null && !zip.isEmpty()) {
	        vo.setVolZip2(zip);
	    }
	    
	    // 전체 개수 조회
	    int total = bongsaService.cntFacilityList(vo);
	   
	    vo = new PageVO(total, start, end, category, searchKeyword, 8 ,zip);
	    List<VolActVO> list = bongsaService.facilityList(vo,startDate,endDate);
	    log.info(total);
	    log.info("list"+list);
	    model.addAttribute("cate", commonService.selectSubCode("f"));
	    model.addAttribute("fac", list);
	    model.addAttribute("vo", vo);
	    model.addAttribute("location", commonService.selectSubCode("z"));
	    
	    
	    return "bongsa/FacilityVol";
	}
	
}
