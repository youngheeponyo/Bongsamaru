package com.bongsamaru.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.mypage.service.HeartVO;

public interface FacilityMapper {
	
	public List<FacilityVO> getFacilityList(PageVO pageVO,@Param("facZip2") String facZip2, @Param("facType") String facType);
	public List<FacilityVO> allFacilityList();
	public FacilityVO getFacilityInfo(String facId);
	public List<FundingVO> getfundingList(PageVO pageVO,String facId);
	public List<FundingVO> getfundedList(PageVO pageVO,String facId);
	public List<VolunteerVO> getVolFList(PageVO pageVO,String facId);
	public List<VolunteerVO> getVolIList(PageVO pageVO,String facId);
	
	
	public int insertJoinVolunteer(VolMemVO volMemVO);//회원이 시설 봉사 참가하기위한 등록폼
	public VolActVO getFacVolInfo(Integer volActId);
	
	//시설 마이페이지(기부)
	public List<DonaVO> getDonaList(@Param("pageVO")PageVO pageVO,@Param("facId")String facId, @Param("donRegApp")String donRegApp,@Param("recStat")String recStat);
	
	//시설 마이페이지(봉사)
	public  int InsertFacVol(VolActVO volActVO);//시설이 봉사 신청하기위한 등록폼
	public List<VolMemVO> getVolunteerAppList(Integer volActId);//봉사참여하겠다고 신청한 참여자리스트
	public int volAppUpdate(Integer volActId, String memId);//참여 승인되서 업데이트
	public int volAppInsert(VolMemVO volMemVO);//참여 승인되서 인서트
	public VolMemVO getJoinApp(Integer volActId);//시설이 승인하려고 보는 신청서
	public int getJoinAppCheck(Integer volActId, String memId);
	public int getFacRevCheck(Integer detailCate);//시설봉사후기 존재여부
	public List<VolActVO> getVolunteerJoinList(@Param("pageVO") PageVO pageVO, @Param("facId")String facId); //봉사하기 전인 봉사리스트(신청 수락 기다리는 리스트)
	public List<VolActVO> getVolunteerFinishList(@Param("pageVO") PageVO pageVO, @Param("facId")String facId); //봉사 완료 후 리스트
	public VolunteerVO volunteerFinishInfo(); //봉사 완료 된 후 신청자 리스트
	
	//페이지 카운터
	public int getCategoryCount( @Param("facZip2") String facZip2, @Param("facType") String facType);
	public int getFVolCategoryCount(String facId);
	public int getFinishVolCategoryCount(String facId);
	public int getFacDonCount(@Param("recStat")Integer recStat, @Param("facId")String facId);
	public int getVolFCount(String facId);
	public int getVolICount(String facId);
	public int getVolDonCount(@Param("facId")String facId, @Param("donRegApp")String donRegApp,@Param("recStat") String recStat);
	//마음온도
	public int insertVolHeart(HeartVO heartVO);
	//봉사후기
	public void insertVolReview(BoardVO boardVO);
	public int updateVolReview(BoardVO boardVO);
	public List<BoardVO> getVolReviewList(String memId);
	public BoardVO getVolReviewInfo(Integer detailCate);
	public int findBoardNo();
}
