package com.bongsamaru.common.VO;

import lombok.Data;

@Data
public class FundingVO {
	
	private String  title;
	private Integer goalAmt;
	private Integer recState;
	private Integer donationAmt;
	private Integer donors;
	private String donId;
	private Integer donationRatio;
	private String donRegApp;
	private String facId;
	private String filePath;
}
