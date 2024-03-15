package com.bongsamaru.challenges.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.challenges.mapper.ChallengeMapper;
import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.file.service.FilesVO;
@Service
public class ChallengesServiceImpl implements ChallengesService{
	
	@Autowired
	ChallengeMapper challengeMapper;
	
	@Override
	public List<ChallengesVO> getChallengeList() {
		return challengeMapper.getChallengeList();
	}
	
	@Override
	public ChallengesVO getChallengeInfo(Integer chalId) {
		return challengeMapper.getChallengeInfo(chalId);
	}

	@Override
	public List<FilesVO> getFileList(Integer codeNo, String code) {
		return challengeMapper.getFileList(codeNo, code);
	}

	@Override
	public List<ChallengesVO> getChallengesList(Integer chalId) {
		return challengeMapper.getChallengesList(chalId);
	}
	

	@Override
	public int getChallengeInsert(ChallengesVO challengeVO) {
		int result = challengeMapper.getChallengeInsert(challengeVO);
		if(result ==1) {
			return challengeVO.getChalId();
			
		}else {
			return -1;
		}	
	}

	@Override
	@Transactional
	public int getChallengesInsert(ChallengesVO challengeVO) {
		int result = challengeMapper.getChallengesInsert(challengeVO);
		if(result ==1) {
			return challengeVO.getChalId();
		}else {
			return -1;
		}	
	}

	@Override
	public boolean getChallengesDel(Integer chalDetId) {
		
		int result = challengeMapper.getChallengesDel(chalDetId);
		return result == 1 ? true : false;
		
	}

	@Override
	public List<LikeVO> getChallengeLike(LikeVO likeVO) {
		return challengeMapper.getChallengeLike( likeVO);
	}

	

	@Override
	public int reportInsert(ReportVO reportVO) {
		int result = challengeMapper.reportInsert(reportVO);
		if(result ==1) {
			return result;
		}else {
			return -1;
		}	
	}

	@Override
	public int getChallCheck(String memId, Date partDate, Integer chalId) {
		return challengeMapper.getChallCheck(memId, partDate, chalId);
	}


	
}
