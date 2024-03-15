package com.bongsamaru.dona.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.dona.mapper.DonaMapper;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DonaServiceImpl implements DonaService {
	

	@Autowired
	DonaMapper donaMapper;
	
	
	@Override
	public List<DonaVO> getDonaList() {
		return donaMapper.getDonaList();
	}
	
	 @Override
	public List<DonaVO> randomlyShow() {
		
		 return donaMapper.randomlyShow();
	}
	 
	 @Override
	public List<DonaVO> selectCompletedItems() {
		 return donaMapper.selectCompletedItems();
	}
	@Override
	public List<DonaVO> getDonaListByCategory(DonaVO donaVO) {
		  return donaMapper.getDonaListByCategory(donaVO);
	}
	
//상세페이지	
	@Override
	public DonaVO getDonaDetail(Integer donId, String facId) {
		return donaMapper.getDonaDetail(donId, facId);
	}
	
//기부자목록	
	@Override
	public List<DonaVO> getDonerList(Integer donId) {
		return donaMapper.getDonerList(donId);
	}
//카테고리	
	@Override
	public List<DonaVO> getCategoryList(String h) {
		return donaMapper.getCategoryList(h);
	}
	
//댓글 리스트
	@Override
	public List<DonaVO> getCommentList(Integer detailCode) {
		return donaMapper.getCommentsList(detailCode);
	}
	
//댓글달기
	@Override
	public int insertComment(DonaVO donaVO) {
		int result = donaMapper.insertComment(donaVO);
		if(result == 1) {
			return donaVO.getCommId();
		}else {
			return -1; 
		}
	}
	
//연장대상자
	@Override
	public List<DonaVO> selectExtensionTargetList() {
		 return donaMapper.selectExtensionTargetList();
	}

//모금종료 업뎃
	@Override
	public void updateRecStat(DonaVO donaVO) {
		 donaMapper.updateRecStat(donaVO);
		
	}
	
// 모금기한 연장하기
	@Override
	public void extendDonationPeriod(DonaVO donaVO) {
		donaMapper.extendDonationPeriod(donaVO);
		
	}
	
	//기부신청 관리자 알람
	@Override
	public int applyAlertDona(DonaVO donaVO) {
		int result = donaMapper.applyAlertDona(donaVO);
		if(result == 1) {
			return donaVO.getAlertId();
		}else {
			return -1; 
		}
	}
	
	//기부등록폼
	@Transactional
	@Override
	public int insertDonation(DonaVO donaVO) {
		DonaVO a = new DonaVO();
		int b = donaMapper.insertDonation(donaVO);
		log.info(b);
		b = donaMapper.insertDonDetail(donaVO);
		log.info(b);
		b =	donaMapper.insertAlertDona(donaVO);
		log.info(b);
		
		return 1; 
	}
	
	
	//기부후기등록폼
	@Transactional
	@Override
	public int insertReview(DonaVO donaVO) {
		donaMapper.insertReview(donaVO);
		donaMapper.insertReviewDetail(donaVO);
		return donaMapper.updateRevStat(donaVO);
	}

	
	//후기 조회
	@Override
	public DonaVO getDonaReview(Integer donId) {
		return donaMapper.getDonaReview(donId);
	}
	
	//결제프로세스	
		@Override
		public int paymentProcess(DonaVO donaVO) {
			int result = donaMapper.paymentProcess(donaVO);
			
			if(result == 1 ) {
				return donaVO.getDonLedId();
			}else {
			return -1;
			}
		}

	@Override
	public int insertDonDetail(DonaVO donaVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReviewDetail(DonaVO donaVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int insertAlertDona(DonaVO donaVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
@Override
	public int receiptAlertDona(DonaVO donaVO) {
	 int result = donaMapper.receiptAlertDona(donaVO);
	 if(result == 1 ) {
			return donaVO.getAlertId();
		}else {
		return -1;
		}
	}

@Override
public int updateRevStat(DonaVO donaVO) {
	// TODO Auto-generated method stub
	return 0;
}
	
}

