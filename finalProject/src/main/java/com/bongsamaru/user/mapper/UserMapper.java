package com.bongsamaru.user.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.CountVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserFacilityVO;
import com.bongsamaru.common.VO.UserVO;

public interface UserMapper {
	public UserVO userList(String mem);
	public int countMemId(String id);
	public int countMemNick(String memNick);
	public int userSignUp(UserVO vo);
	public List<UserCategoryVO> categoryList();
	public int insertCate(@Param("id") String id, @Param("name") String name);
	public UserFacilityVO login(String id);
	public int checkBizNum(String num);
	public int facilitySignUp(FacilityVO vo);
	public List<AlertVO> listAlert(String memId);
	public List<CountVO> volKing();
	public int CountAlarm(String mem);
	public int updateAlarm(AlertVO vo);
	public UserFacilityVO findId(String phoen);
	public String findInfo(String id);
	public String findProfile(String id);
	public int updatePwd(String id, String type, String pass);
}
