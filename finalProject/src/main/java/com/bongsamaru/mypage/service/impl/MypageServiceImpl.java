package com.bongsamaru.mypage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.mypage.mapper.MypageMapper;
import com.bongsamaru.mypage.service.DonledgerVO;
import com.bongsamaru.mypage.service.HeartVO;
import com.bongsamaru.mypage.service.MypageService;

@Service
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	MypageMapper mypageMapper;
	
	@Override
	public int updateProFile(UserVO userVO) {
		// TODO Auto-generated method stub
		return mypageMapper.updateProFile(userVO);
	}
	
	@Override
	public Double getHeart(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getHeart(memId);
	}
	
	@Override
	public Integer getSumAmt(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getSumAmt(memId);
	}
	
	@Override
	public Integer getGibuCount(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getGibuCount(memId);
	}
	
	@Override
	public List<DonledgerVO> getGibuList(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getGibuList(memId);
	}
	
	@Override
	public List<UserVO> getProfile(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getProfile(memId);
	}
	@Override
	public boolean insertHeart(HeartVO vo) {
		if(mypageMapper.insertHeart(vo)==1) {
			return true;
		}
		return false; 
	}	
	
	@Override
	public int deleteMember(UserVO userVO) {
		// TODO Auto-generated method stub
		return mypageMapper.deleteMember(userVO);
	}
	
	@Override
	public List<VolMemVO> getDayList(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getDayList(memId);
	}
	
	@Override
	public List<VolMemVO> getClubList(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getClubList(memId);
	}
	
	@Override
	public List<VolMemVO> getFacList(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getFacList(memId);
	}
}
