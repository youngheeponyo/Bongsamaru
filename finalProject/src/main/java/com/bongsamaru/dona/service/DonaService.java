package com.bongsamaru.dona.service;

import java.util.List;


public interface DonaService {
	//메인페이지 전체리스트
	List<DonaVO> getDonaList();
	//모금중
	List<DonaVO> randomlyShow();
	//모금완료
	List<DonaVO> selectCompletedItems();
	//카테고리별리스트
	List<DonaVO> getDonaListByCategory(DonaVO donaVO);
	
	
	//상세페이지 조회 1
	public DonaVO getDonaDetail(Integer donId, String facId);
	
	//기부-카테고리 list
	public List<DonaVO> getCategoryList(String h);

	
	// 결제 완료자 리스트
	public List<DonaVO> getDonerList(Integer donId);
	
	
	//댓글 리스트
	public List<DonaVO> getCommentList(Integer detailCode);
	
	//댓글 등록하기
	public int insertComment(DonaVO donaVO);
	
	//연장 이메일 대상자
	List<DonaVO> selectExtensionTargetList();
	//모금종료 update
	public void updateRecStat(DonaVO donaVO);
	
	//모금기한연장-수정
	public void extendDonationPeriod(DonaVO donaVO);
	
	// 기부신청 관리자 알람 insert
	public int applyAlertDona(DonaVO donaVO);
	
	//등록폼 insert
	public int insertDonation(DonaVO donaVO);
	public int insertDonDetail(DonaVO donaVO);
	public int insertAlertDona(DonaVO donaVO);
	
	//등록 후기 insert
	public int insertReview(DonaVO donaVO);
	public int insertReviewDetail(DonaVO donaVO);
	public int receiptAlertDona(DonaVO donaVO);
	public int updateRevStat(DonaVO donaVO);
	
	//후기 개별
	public DonaVO getDonaReview(Integer donId);
	
	
	//결제하기
	public int paymentProcess(DonaVO donaVO);
}