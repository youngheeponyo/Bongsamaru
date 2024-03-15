package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class VolActReviewVO {
	private Integer reviewId;
	private Integer volId;
	private Integer cnt;
	private Integer volActId;
	private String title;
	private String content;
	private String writer;
	private Date writeDate;
}
