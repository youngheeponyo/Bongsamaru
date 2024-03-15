package com.bongsamaru.bongsa.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.admin.mapper.AdminMapper;
import com.bongsamaru.bongsa.mapper.BongsaMapper;
import com.bongsamaru.bongsa.service.BongsaDTO;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.CountVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;

@Service
public class BonsaServiceImpl implements BongsaService {
	@Autowired
	BongsaMapper bongsaMapper;
	
	@Autowired
	AdminMapper adminMapper;
	@Override
	public List<VolActVO> facilityList(PageVO vo, Date start, Date end) {
	    if (start == null) {
	        Calendar startCal = Calendar.getInstance();
	        startCal.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
	        startCal.set(Calendar.MILLISECOND, 0);
	        start = startCal.getTime();
	    }

	    // end가 null인 경우 2050년 12월 31일로 설정
	    if (end == null) {
	        Calendar endCal = Calendar.getInstance();
	        endCal.set(2050, Calendar.DECEMBER, 31, 23, 59, 59);
	        endCal.set(Calendar.MILLISECOND, 999);
	        end = endCal.getTime();
	    }
		return bongsaMapper.facilityList(vo,start,end);
	}
	
	@Override
	public int cntFacilityList(PageVO vo) {
		return bongsaMapper.cntFacilityList(vo);
	}
	
	@Override
	public CountVO cntVol(PageVO vo) {
		if(vo == null) {
			CountVO countVO = new CountVO();
			countVO.setDailyVol(0);
			countVO.setCount(0);
			countVO.setFacVol(0);
			countVO.setGroupVol(0);
			return countVO;
		}
	    CountVO result = bongsaMapper.countVol(vo);
	    if (result == null) {
	        // 쿼리 결과가 null인 경우, 기본값을 갖는 CountVO 객체를 생성하여 반환
	        result = new CountVO();
	        result.setDailyVol(0);
	        result.setCount(0);
	        result.setFacVol(0);
	        result.setGroupVol(0);
	    }
	    return result;
	}
	
	@Override
	public List<VolunteerVO> volList(PageVO vo, Date start, Date end, String type) {
		return bongsaMapper.volList(vo, start, end, type);
	}
	@Override
	public List<BongsaDTO> getVolTagDTO(PageVO vo, Date startDate, Date endDate , String cate) {
		List<VolunteerVO> volunteers = bongsaMapper.volList(vo, startDate, endDate, cate);			
	    List<TagVO> allTags = adminMapper.tagList(null);
	    Map<Integer, List<TagVO>> tagsByVolunteer = allTags.stream()
	            .collect(Collectors.groupingBy(TagVO::getVolId));

	    List<BongsaDTO> result = volunteers.stream()
	            .map(volunteer -> 
	            new BongsaDTO(volunteer, tagsByVolunteer.getOrDefault(volunteer.getVolId(),
	            		Collections.emptyList())))
	            .collect(Collectors.toList());
	    return result;
	}
	
	public List<BongsaDTO> getVolTagDTO(String type){
		List<VolunteerVO> volunteers = bongsaMapper.allVol(type);			
	    List<TagVO> allTags = adminMapper.tagList(null);
	    Map<Integer, List<TagVO>> tagsByVolunteer = allTags.stream()
	            .collect(Collectors.groupingBy(TagVO::getVolId));

	    List<BongsaDTO> result = volunteers.stream()
	            .map(volunteer -> new BongsaDTO(volunteer, tagsByVolunteer.getOrDefault(volunteer.getVolId(), Collections.emptyList())))
	            .collect(Collectors.toList());
	    return result;
	}
	
	@Override
	public List<VolunteerVO> allVol(String type) {
		return bongsaMapper.allVol(type);
	}
}
