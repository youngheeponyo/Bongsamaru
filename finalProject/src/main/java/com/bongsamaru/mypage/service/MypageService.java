package com.bongsamaru.mypage.service;


import java.util.List;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolMemVO;

public interface MypageService {
	
	public Double getHeart(String memId);
	public int updateProFile(UserVO userVO);
	public int deleteMember(UserVO userVO);
	public Integer getSumAmt(String memId);
	public Integer getGibuCount(String memId);
	public List<DonledgerVO> getGibuList(String memId);
	public List<UserVO> getProfile(String memId);
	public boolean insertHeart(HeartVO vo);
	public List<VolMemVO> getDayList(String memId);
	public List<VolMemVO> getClubList(String memId);
	public List<VolMemVO> getFacList(String memId);

}
