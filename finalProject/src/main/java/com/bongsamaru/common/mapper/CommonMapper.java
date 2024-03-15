package com.bongsamaru.common.mapper;

import java.util.List;

import com.bongsamaru.common.VO.SubCodeVO;

public interface CommonMapper {
	public List<SubCodeVO> subCodeList(String mainCode);
}
