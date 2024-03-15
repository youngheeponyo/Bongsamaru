package com.bongsamaru.facility.ServiceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.mapper.FacilityMapper;
import com.bongsamaru.mypage.service.HeartVO;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityMapper mapper;

	@Override
	public List<FacilityVO> getFacilityList(PageVO pageVO,String facZip2, String facType) {
		return mapper.getFacilityList(pageVO,facZip2,facType);
	}
	
	@Override
	public List<FacilityVO> allFacilityList() {
		return mapper.allFacilityList();
	}
	@Override
	public FacilityVO getFacilityInfo(String facId) {
		return mapper.getFacilityInfo(facId);
	}

	@Override
	public List<FundingVO> getFundingList(PageVO pageVO,String facId) {
		return mapper.getfundingList(pageVO,facId);
	}

	@Override
	public List<FundingVO> getFundedList(PageVO pageVO,String facId) {
		return mapper.getfundedList(pageVO,facId);
	}

	@Override
	public List<VolunteerVO> getVolFList(PageVO pageVO,String facId) {
		
		return mapper.getVolFList(pageVO,facId);
	}
	@Override
	public List<VolunteerVO> getVolIList(PageVO pageVO,String facId) {
		
		return mapper.getVolIList(pageVO,facId);
	}
	
	@Override
	public int insertJoinVolunteer(VolMemVO volMemVO) {
		return mapper.insertJoinVolunteer(volMemVO);
		
	}
	
	@Override
	public VolActVO getFacVolInfo(Integer volActId) {
		return mapper.getFacVolInfo(volActId);
	}
	
	@Override
	public List<DonaVO> getDonaList(PageVO pageVO,String facId,String donRegApp, String recStat) {
		log.info(facId);
		log.info(donRegApp);
		log.info(recStat);
		return mapper.getDonaList(pageVO,facId,donRegApp,recStat);
	}
	
	
	@Override
	public int InsertFacVol(VolActVO volActVO) {
		//boolean check =mapper.checkList(String memID, Integer VolActId);
		//if(check){
		//
		int result = mapper.InsertFacVol(volActVO);
		if(result ==1) {
			return volActVO.getVolActId();
		}else {
			return -1;
		}	
	}

	@Override
	public List<VolActVO> getVolunteerJoinList(PageVO pageVO,String facId) {
		return mapper.getVolunteerJoinList(pageVO,facId);
	}
	@Override
	public List<VolMemVO> getVolunteerAppList(Integer volActId) {
		return mapper.getVolunteerAppList(volActId);
	}
	
	@Override
	public List<VolActVO> getVolunteerFinishList(PageVO pageVO, String facId) {
		return mapper.getVolunteerFinishList(pageVO,facId);
	}
	//시설이 회원봉사 승인하면 업데이트
	@Override
	public int volAppUpdate(Integer volActId, String memId) {
		return mapper.volAppUpdate(volActId, memId);
	}
	//시설이 회원봉사 승인하면 인서트
	@Override
	public int volAppInsert(VolMemVO volMemVO) {
		return mapper.volAppInsert(volMemVO);
	}
	@Override
	public VolMemVO getJoinApp(Integer volActId) {
		return mapper.getJoinApp(volActId);
	}

	@Override
	public int getJoinAppCheck(Integer volActId, String memId) {
		return mapper.getJoinAppCheck(volActId, memId);
	}

	@Override
	public int getFacRevCheck(Integer detailCate) {
		return mapper.getFacRevCheck(detailCate);
	}
	
		//마음온도
		@Override
		public int insertVolHeart(HeartVO heartVO) {
			return mapper.insertVolHeart(heartVO);
		}

		@Override
		public void insertVolReview(BoardVO boardVO) {
			mapper.insertVolReview(boardVO);
			
		}
		@Override
		public int updateVolReview(BoardVO boardVO) {
			return mapper.updateVolReview(boardVO);
		}
		@Override
		public BoardVO getVolReviewInfo(Integer detailCate) {
			return mapper.getVolReviewInfo(detailCate);
		}


		@Override
		public int findBoardNo() {
			return mapper.findBoardNo();
		}

		//페이지네이션하기 위한거
		@Override
		public int getCategoryCount(@Param("facZip2") String facZip2, @Param("facType") String facType) {
			
			return mapper.getCategoryCount(facZip2, facType);
		}


		@Override
		public int getFVolCategoryCount(String facId) {
			
			return mapper.getFVolCategoryCount(facId);
		}

		@Override
		public int getFinishVolCategoryCount(String facId) {
			return mapper.getFinishVolCategoryCount(facId);
		}

		@Override
		public int getFacDonCount(Integer recStat, String facId) {
			return mapper.getFacDonCount(recStat, facId);
		}
		@Override
		public int getVolFCount(String facId) {
			return mapper.getVolFCount(facId);
		}
		@Override
		public int getVolICount(String facId) {
			return mapper.getVolICount(facId);
		}

		@Override
		public int getVolDonCount(String facId, String donRegApp, String recStat) {
			return mapper.getVolDonCount(facId, donRegApp, recStat);
		}

		@Override
		public List<BoardVO> getVolReviewList(String memId) {
			return mapper.getVolReviewList(memId);
		}
		


		


		


		
	

	
}


