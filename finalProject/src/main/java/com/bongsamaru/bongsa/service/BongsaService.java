package com.bongsamaru.bongsa.service;

import java.util.Date;
import java.util.List;

import com.bongsamaru.common.VO.CountVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface BongsaService {
	public List<VolActVO> facilityList(PageVO vo, Date start, Date end);
	public List<VolunteerVO> volList(PageVO vo, Date start, Date end, String type);
	public int cntFacilityList(PageVO vo);
	public CountVO cntVol(PageVO vo);
	public List<BongsaDTO> getVolTagDTO(PageVO vo, Date startDate, Date endDate , String cate);	
	public List<BongsaDTO> getVolTagDTO(String type);	
	public List<VolunteerVO> allVol(String type);
}
