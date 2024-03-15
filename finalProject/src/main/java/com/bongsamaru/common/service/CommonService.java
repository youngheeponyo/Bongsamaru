package com.bongsamaru.common.service;

import java.util.List;

import com.bongsamaru.common.VO.SubCodeVO;



public interface CommonService {
	public List<SubCodeVO> selectSubCode(String mainCode);
}
