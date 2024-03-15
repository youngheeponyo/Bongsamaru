package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Integer boardId;
	private String category;
	private String title;
	private String content;
	private Date writeDate;
	private Integer views;
	private Integer priority;
	private String memId;
	private Date modDate;
	private Integer detailCate;
	private Integer RN;
	
	private String commContent;

}
