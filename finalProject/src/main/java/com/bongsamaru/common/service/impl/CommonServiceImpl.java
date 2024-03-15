package com.bongsamaru.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.SubCodeVO;
import com.bongsamaru.common.mapper.CommonMapper;
import com.bongsamaru.common.service.CommonService;


@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	CommonMapper commonMapper;
	
	
		@Override
		public List<SubCodeVO> selectSubCode(String mainCode) {
			
			return commonMapper.subCodeList(mainCode);
	}
}
