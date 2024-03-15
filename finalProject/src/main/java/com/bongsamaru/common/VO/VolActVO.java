package com.bongsamaru.common.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VolActVO {
	private Integer volActId;
	private Integer volId;
	private String facId;
	private String facName;
	private String category;
	private String cate;
	private String title;
	private String content;
	private String location;
	private Integer cap;
	private Integer cnt;
	private Integer state;
	private Integer reviewId;
	private String writer;
	private String filePath;
	private Date writeDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date volDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date expireDate;
	private Integer count;
	private Integer volZip;
	private String path;
	private Integer rn;
	
	private String volZip2;
	private Integer capCount;
	

}
