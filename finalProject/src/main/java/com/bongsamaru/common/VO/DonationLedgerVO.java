package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class DonationLedgerVO {
	private Integer DON_LED_ID;
	private Integer DON_ID;
	private Integer DON_AMT;
	private String PAY_METHOD;
	private Date PAY_DATE;
	private String PAY_STAT;
	private Integer PAY_ID;
	private String ANON_CHECK;
	private Integer MEM_ID;
	private Integer cnt;
	private Integer totals;
}
