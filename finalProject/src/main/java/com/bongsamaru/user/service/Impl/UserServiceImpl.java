package com.bongsamaru.user.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.CountVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserFacilityVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.service.TokenService;
import com.bongsamaru.mypage.mapper.MypageMapper;
import com.bongsamaru.mypage.service.HeartVO;
import com.bongsamaru.user.mapper.UserMapper;
import com.bongsamaru.user.service.UserDetailVO;
import com.bongsamaru.user.service.UserService;
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	MypageMapper mypageMapper;
	
	@Autowired
	TokenService tokenService;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserFacilityVO vo = userMapper.login(username);
	    System.out.println(vo);
	    System.out.println("이값이 뭔데!!!!!!!!!!");
	    
	    if(vo == null) {
	        throw new UsernameNotFoundException("로그인에 실패하였습니다. 아이디/비밀번호를 확인해주세요." );
	    } else if (("0".equals(vo.getMemApp()) && vo.getMemStat()==null )) {
	        throw new UsernameNotFoundException("회원 가입 심사 중인 아이디 입니다.");
	    }
	    
	    if(vo.getMemStat()==null) {
	    	vo.setMemStat("M03");
	    }
	    
	    return new UserDetailVO(vo);
	}
	
	@Override
	public Boolean insertCate(String cate, String name) {
		if(userMapper.insertCate(cate , name)==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<UserCategoryVO> userCategoty() {
		return userMapper.categoryList();
	}

	@Override
	public Boolean countMemId(String memId) {
		if(userMapper.countMemId(memId) > 0){
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean countMemNick(String memNick) {
		System.out.println(userMapper.countMemNick(memNick) + "!@!@");
		if(userMapper.countMemNick(memNick) > 0){
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean insertFac(FacilityVO vo) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String result = encoder.encode(vo.getFacPwd());
		vo.setFacPwd(result);
		
		if(userMapper.facilitySignUp(vo)==1) {
			
			return true;
		}
		return false;	
	}
	
	@Override
	public Boolean insertUser(UserVO vo) {
		if(userMapper.userSignUp(vo) == 1){
			return true;
		}
		return false;
	}
	
	@Override
	public UserVO userList(String mem) {
		return userMapper.userList(mem);
	}
	
	@Override
	@Transactional
	public Boolean userInsert(UserVO vo, List<String> cate) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String result = encoder.encode(vo.getMemPwd());
		vo.setMemPwd(result);
		if(insertUser(vo)) {
			HeartVO heart = new HeartVO();
			heart.setMemId(vo.getMemId());
			heart.setTempCode("j01");
			heart.setTempChange(36.5);
			mypageMapper.insertHeart(heart);
			 if (cate != null && !cate.isEmpty()) {
				 for(String one : cate) {
					 insertCate(vo.getMemId(),one);
				 }
			 } 
			 return true;
		};
		return false;
	}
	
	@Override
	public Boolean countBizNum(String num) {
		if(userMapper.checkBizNum(num) == 1) {
			return false;
		}
		return true;
	}
	
	@Override
	public List<AlertVO> listAlert(String memId) {
		return userMapper.listAlert(memId);
	}
	
	@Override
	public List<CountVO> volKing() {
		return userMapper.volKing();
	}
	
	@Override
	public int countAlarm(String memid) {
		return userMapper.CountAlarm(memid);
	}
	
	@Override
	public int updateAlarm(AlertVO vo) {
		return userMapper.updateAlarm(vo);
	}
	
	@Override
	public UserFacilityVO findId(String phone) {
		return userMapper.findId(phone);
	}
	
	@Override
	public boolean changeUserPassword(String token , String pass) {
		 String userId = tokenService.getUserIdFromToken(token);
		  if (userId == null) {
		        return false;
		    }
		String type = userMapper.findInfo(userId);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String result = encoder.encode(pass);
		userMapper.updatePwd(userId, type, result);
		return true;
	}
	
	@Override
	public String findInfo(String id) {
		return userMapper.findInfo(id);
	}
	
	@Override
	public String findProfile(String id) {
		return userMapper.findProfile(id);
	}
}
