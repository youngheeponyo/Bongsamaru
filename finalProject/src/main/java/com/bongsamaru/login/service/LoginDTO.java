package com.bongsamaru.login.service;

import java.util.List;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.file.service.FilesVO;



public class LoginDTO {
	private UserVO userVO;
	private List<String> category;
	private FilesVO filesVO;
	
	public UserVO getUserVO() {
		return userVO;
	}
	
	
	public FilesVO getFilesVO() {
		return filesVO;
	}


	public void setFilesVO(FilesVO filesVO) {
		this.filesVO = filesVO;
	}


	public void setUserVO(UserVO userVO) {
		System.out.println(userVO+ "이게 뭔값임?");
		this.userVO = userVO;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	
	
	
}
