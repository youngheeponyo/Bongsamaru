package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class DonationVO {
	private Integer donId;
	private String facId;
	private String title;
	private Date recPeriod;
	private Date endPeriod;
	private Date extPeriod;
	private Integer goalAmt;
	private String projTarget;
	private String intro;
	private String recStat;
	private String donRegApp;
	private String expEffect;
	
	
	 public Date getEndPeriod() {
	    	if(extPeriod != null) {
	    		return extPeriod;
	    	}else {
	    		return endPeriod;
	    	}
	    }
}
