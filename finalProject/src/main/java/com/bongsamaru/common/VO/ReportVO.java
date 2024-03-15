package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class ReportVO {
	private Integer repId;
	private String category;
	private Date repDate;//신고 카테고리
	private String memId;
	private Integer categoryNo;		//신고 상세카테고리
	private String repReason;
	private Integer reqCode;	//신고 처리상태
}
