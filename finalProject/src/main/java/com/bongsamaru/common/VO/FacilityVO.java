package com.bongsamaru.common.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class FacilityVO {
	
	private String facId;
	private String bizNum;
	private String facPwd;
	private String facName;
	private String facType;
	private Integer facZip;
	private String facAddr;
	private String facAddrDetail;
	private String region;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date facEstDate;
	private String facEmail;
	private String facWeb;
	private String facIntro;
	private String repName;
	private String repPhone;
	private String facBank;
	private String donAcct;
	private String donApp;
	private String memApp;
	private String facZip2;  //지역(우편번호 앞 2)
	private Integer RN;
	private String category;
	
	//join문으로 생긴 별칭
	 private String filePath;
	 private Integer donaitonAmt; 
	 private Integer donors;
	 
	 
}
