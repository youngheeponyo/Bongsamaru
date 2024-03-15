package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class RemittanceVO {
	private Integer remNo;
	private Integer donId;
	private Integer remTotal;
	private Date remDate;
	private String remBank;
	private String remAcct;
	private String checking;
	private String facName;
	private String title;
	private String email;
	private Date deadlineDate;
}
