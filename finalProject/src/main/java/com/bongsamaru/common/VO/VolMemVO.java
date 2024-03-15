package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class VolMemVO {
	private Integer volMemId;
	private Integer volId;
	private String facId;
	private String appCode;
	private Date appDate;
	private Date volDate;
	private String appReason;
	private String memId;
	private Integer volActId;
	private Integer cnt;
	private String path;
	private String volActMemId;
	private String title;
	private String bigo;
	
	private String meetName;
	private String checking;
}
