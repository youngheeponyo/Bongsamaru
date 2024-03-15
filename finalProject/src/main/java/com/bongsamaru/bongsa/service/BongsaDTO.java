package com.bongsamaru.bongsa.service;

import java.util.List;

import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolunteerVO;

import lombok.Data;

@Data
public class BongsaDTO {
	private VolunteerVO vol;
	private List<TagVO> tag;
	
    public BongsaDTO(VolunteerVO volunteer, List<TagVO> tags) {
        this.vol = volunteer;
        this.tag = tags;
    }
	
	
}
