package com.bongsamaru.common.VO;

import lombok.Data;

@Data
public class CountVO {
	private Integer groupVol;
	private Integer facVol;
	private Integer dailyVol;
	private Integer count;
	private String mNick;
	private String memId;
}
