package com.bongsamaru.user.service;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bongsamaru.common.VO.UserFacilityVO;
import com.bongsamaru.common.VO.UserVO;

import groovy.transform.ToString;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ToString
public class UserDetailVO implements UserDetails,AuthenticationSuccessHandler {
	
	final UserFacilityVO userVO;
	/*
	 * public void setMemberVO(MemberVO memberVO) { this.memberVO = memberVO; }
	 * 
	 * public UserDetailVO() {} public UserDetailVO(MemberVO memberVO) {
	 * this.memberVO = memberVO; }
	 */
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		if(userVO.getMemStat()==null){
			userVO.setMemStat("m03");
		}
		list.add(new SimpleGrantedAuthority("ROLE_" +  userVO.getMemStat().toUpperCase()));
		System.out.println(list);
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userVO.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userVO.getId();
	}
	
	public String getUserNickname() {
		return userVO.getMemName();
	}
	
	public UserFacilityVO getUserVO() {
		return userVO;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	}
	
}
