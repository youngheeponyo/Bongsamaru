package com.bongsamaru.mypage.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HeartVO {
	private Integer heartTempId;
	private String memId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tempDate;
	private String tempCode;
	private Double tempChange;
	private Integer heartNo;
	
	
}
