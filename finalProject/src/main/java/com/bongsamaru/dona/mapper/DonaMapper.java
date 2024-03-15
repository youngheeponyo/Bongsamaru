package com.bongsamaru.dona.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.dona.service.DonaVO;


public interface DonaMapper {
	
	// 메인페이지 전체리스트
		List<DonaVO> getDonaList();
		//모금중
		List<DonaVO> randomlyShow();
		//모금완료
		List<DonaVO> selectCompletedItems();
		//카테고리별
		List<DonaVO> getDonaListByCategory(DonaVO donaVO);
	
	// 모금중.모금완료
	//	List<DonaVO> getRecStat(String recStat);
		
	// 상세페이지1 (don_id)
		public DonaVO getDonaDetail(Integer donId, String facId);
	
	// 기부자목록 in 상세페이지
		public List<DonaVO> getDonerList(Integer donId);
		
	//댓글 - 전체리스트 
		public List<DonaVO> getCommentsList(Integer detailCode);
		
	//댓글 insert
		public int insertComment(DonaVO donaVO);
		
	//카테고리
		List<DonaVO> getCategoryList(String h);

	
	// 연장여부이메일대상자(시설)
		List<DonaVO> selectExtensionTargetList();
		
	//모금종료시 update	
		 void updateRecStat(DonaVO donaVO);
	
	//기한 연장 update	 
		 void extendDonationPeriod(DonaVO donaVO);
		 
	//기부등록폼 - donation
		public int insertDonation(DonaVO donaVO);
		
	//기부등록폼2  - donation 상세
		public int insertDonDetail(DonaVO donaVO);
		
	//기부글등록 관리자 알람
		public int insertAlertDona(DonaVO donaVO);
		
	// 기부신청 관리자알람
		public int applyAlertDona(DonaVO donaVO);
		
	//기부후기등록
		public int insertReview(DonaVO donaVO);
		
	//기부후기상태 업데이트
	public int updateRevStat(DonaVO donaVO);
		
	//기부후기등록2
		public int insertReviewDetail(DonaVO donaVO);
	//후기등록 관리자 알람
		public int receiptAlertDona(DonaVO donaVO);
	//기부후기 개별조회
		public DonaVO getDonaReview(Integer donId);
	
	//결제프로세스
	 public int paymentProcess(DonaVO donaVO);
		
		
}