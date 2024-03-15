package com.bongsamaru.common.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VolunteerVO {
	private String volActId; 
	private String revTitle;
	private String content;
	private Date writeDate; 
	private String facId; 
	private String category; 
	private String title; 
	private String location;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date volDate;
	private Integer cap;
	private String facName; 
	private String filePath; 
	//영희
	private Integer vol;
	private Integer capCnt;
	private Integer fac;
	private String memId;
	private Integer volId;
	private String meetName;
	private String meetType;
	private String meetPurp;
	private String meetDesc;
	private String region;
	private String oneLiner;
	private Integer roomStat;
	private Integer meetingCnt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date recPeriod;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endPeriod;
	private Date appDate;
	private String path;
	private String mainCategory;
}
